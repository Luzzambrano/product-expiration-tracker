package com.punto929.gdc.service;

import com.punto929.gdc.entity.GDCUser;
import com.punto929.gdc.entity.dto.SignupRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
  GDCUser findByUserName(String userName);
  List<GDCUser> getAllUsers();
  GDCUser findUserById(Long id);
  void deleteUser(Long id);
}
