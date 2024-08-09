package com.punto929.gdc.utils;

public interface AppConstants {
  public static final String[] WHITE_LIST_URL = {
      "/v1/auth/**",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui/**",
      "/webapp/**",
      "/swagger-ui.html",
      "/actuator/**",
      "classpath:/public/",
  };

  public static final String[] PROTECTED_RESOURCES = {
      "/v1/products/**",
      "/swagger-resources",
      "/swagger-resources/**",
      "/swagger-ui/**",
      "/swagger-ui.html",
      "/actuator/**",
  };
}
