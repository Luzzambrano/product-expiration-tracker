package com.punto929.gdc.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.punto929.gdc.config.JwtService;
import com.punto929.gdc.entity.GDCUser;
import com.punto929.gdc.entity.Token;
import com.punto929.gdc.entity.dto.AuthToken;
import com.punto929.gdc.entity.dto.SignupRequest;
import com.punto929.gdc.repository.TokenRepository;
import com.punto929.gdc.repository.UserRepository;
import com.punto929.gdc.service.AuthService;
import com.punto929.gdc.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.io.IOException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.punto929.gdc.utils.TokenType.BEARER_TOKEN;

@Service
@RequiredArgsConstructor
public class GDCUserService implements UserService, AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final TokenRepository tokenRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public GDCUser signup(SignupRequest dto) {
    Assert.state(Boolean.FALSE.equals(userRepository.existsByUsername(dto.username())), "User is registered");
    return userRepository.save(GDCUser
        .builder()
            .username(dto.username())
            .email(dto.email())
        .password(passwordEncoder.encode(dto.password()))
            .firstName(dto.firstName())
        .lastName(dto.lastName())
        .build());
  }

  @Override
  public AuthToken getAuthToken(String username, String password) {
    this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    var user = this.findByUserName(username);
    var accessToken = jwtService.generateAccessToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, accessToken);

    return AuthToken.builder()
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .expires(jwtService.getJwtExpirationDate(accessToken).getTime())
        .build();
  }

  @Override
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    String username = null;
    String refreshToken = null;

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      refreshToken = authorizationHeader.substring(7);
      username = jwtService.extractUsername(refreshToken);
    }
    Assert.state(StringUtils.isNotEmpty(username), "Invalid refresh token");
    GDCUser user = this.findByUserName(username);
    Assert.notNull(user, "Invalid refresh token");
    Assert.state(jwtService.validateToken(refreshToken, user), "Invalid refresh token");
    var accessToken = jwtService.generateAccessToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, accessToken);

    response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    new ObjectMapper().writeValue(
        response.getOutputStream(),
        AuthToken.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .expires(jwtService.getJwtExpirationDate(accessToken).getTime())
            .build()
    );
  }

  @Override
  public void signOut() {

  }

  @Override
  public GDCUser findByUserName(String userName) {
    return userRepository.findByUsername(userName).orElseThrow(
        () -> new IllegalArgumentException("User name %s not found".formatted(userName)));
  }

  @Override
  public List<GDCUser> getAllUsers() {
    return this.userRepository.findAll();
  }

  @Override
  public GDCUser findUserById(Long id) {
    GDCUser user = userRepository.findById(id).orElse(null);
    Assert.notNull(user, "User with id: %s not found".formatted(id));
    return user;
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.delete(this.findUserById(id));
  }

  private void saveUserToken(GDCUser user, String token) {
    this.tokenRepository.save(
        Token.builder()
            .user(user)
            .token(token)
            .tokenType(BEARER_TOKEN)
            .expired(false)
            .revoked(false)
            .build()
    );
  }

  private void revokeAllUserTokens(GDCUser user) {
    var validUserTokens = this.tokenRepository.findAllValidTokensByUser(user.getId());
    if (validUserTokens.isEmpty()) {
      return;
    }
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    this.tokenRepository.saveAll(validUserTokens);
  }
}
