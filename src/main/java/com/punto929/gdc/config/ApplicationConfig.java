package com.punto929.gdc.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.punto929.gdc.entity.GDCUser;
import com.punto929.gdc.entity.Product;
import com.punto929.gdc.repository.ProductRepository;
import com.punto929.gdc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
  private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner commandLineRunner(UserRepository repository) {
    return args -> {
      logger.info("Creating demo user ...");
      userRepository.save(GDCUser.builder()
              .email("admin@punto929.mx")
              .username("admin@punto929.mx")
              .password(this.passwordEncoder().encode("changeit$"))
              .firstName("Demo")
              .lastName("Admin")
          .build());

      logger.info("Loading initial product data ...");
      try (InputStream inputStream = TypeReference.class.getResourceAsStream("/static/products.json")) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        List<Product> products = mapper.readValue(inputStream, new TypeReference<List<Product>>() {})
            .stream()
            .toList();
        productRepository.saveAll(products);
      } catch (IOException e) {
        logger.error("Failed to load initial data", e);
      }
    };
  }
}
