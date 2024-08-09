package com.punto929.gdc.controller;

import com.punto929.gdc.entity.dto.AuthToken;
import com.punto929.gdc.entity.dto.SignupRequest;
import com.punto929.gdc.service.impl.GDCUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.punto929.gdc.entity.GDCUser;

import java.io.IOException;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
  private final GDCUserService gdcUserService;

  public record LoginDto(
      String email,
      String password
  ){}

  @PostMapping("/register")
  public ResponseEntity<GDCUser> signup(@RequestBody SignupRequest request) {
    return ResponseEntity.ok(gdcUserService.signup(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthToken> signin(@RequestBody LoginDto request) {
    return ResponseEntity.ok(
        gdcUserService.getAuthToken(
            request.email(),
            request.password()
        ));
  }

  @PostMapping("/refresh-token")
  public void refreshAccessToken(
        HttpServletRequest request, HttpServletResponse response) throws IOException {
      gdcUserService.refreshToken(request, response);
  }
}
