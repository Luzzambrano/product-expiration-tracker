package com.punto929.gdc.repository;

import com.punto929.gdc.entity.GDCUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<GDCUser, Long> {
  Optional<GDCUser> findByUsername(String username);

  Boolean existsByUsername(String username);

}
