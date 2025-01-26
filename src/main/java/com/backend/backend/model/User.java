package com.backend.backend.model;

import com.backend.backend.dto.UserDTO;
import com.backend.backend.imageData.ImageData;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @OneToOne
  @JoinColumn
  private ImageData userProfilePicture;

  public User(UserDTO userDTO) {
    BeanUtils.copyProperties(userDTO, this);
  }

  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public User() {
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

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ImageData getUserProfilePicture() {
    return userProfilePicture;
  }

  public void setUserProfilePicture(ImageData userProfilePicture) {
    this.userProfilePicture = userProfilePicture;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    //return UserDetails.super.isAccountNonExpired();
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    //return UserDetails.super.isAccountNonLocked();
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    //return UserDetails.super.isCredentialsNonExpired();
    return true;
  }

  @Override
  public boolean isEnabled() {
    //return UserDetails.super.isEnabled();
    return true;
  }

}
