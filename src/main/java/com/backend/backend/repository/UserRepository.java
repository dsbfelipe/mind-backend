package com.backend.backend.repository;

import com.backend.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
  //User findByName(String name);

  UserDetails findByEmail(String username);


}
