package com.backend.backend.dto;

import com.backend.backend.model.User;
import org.springframework.beans.BeanUtils;

public class UserDTO {

  private Long id;

  private String name;

  private String email;

  private String password;

  public UserDTO(User user) {
    BeanUtils.copyProperties(user, this);
  }

  public UserDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
