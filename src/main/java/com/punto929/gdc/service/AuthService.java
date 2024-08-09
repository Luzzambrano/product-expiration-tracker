package com.punto929.gdc.service;

import com.punto929.gdc.entity.GDCUser;
import com.punto929.gdc.entity.dto.AuthToken;
import com.punto929.gdc.entity.dto.SignupRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthService {
  GDCUser signup(SignupRequest dto);
  AuthToken getAuthToken(String username, String password);
  void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
  void signOut();
}
