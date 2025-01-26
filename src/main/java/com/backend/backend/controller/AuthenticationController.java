package com.backend.backend.controller;

import com.backend.backend.dto.AuthDTO;
import com.backend.backend.dto.LoginResponseDTO;
import com.backend.backend.dto.UserDTO;
import com.backend.backend.infra.security.TokenService;
import com.backend.backend.model.User;
import com.backend.backend.repository.UserRepository;
import com.backend.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid AuthDTO authDTO) {

    var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);

    var token = tokenService.generateToken((User) auth.getPrincipal());

    return ResponseEntity.ok(new LoginResponseDTO(token));
  }

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody @Valid UserDTO userDTO) {
    if(this.userRepository.findByEmail(userDTO.getEmail()) != null) return ResponseEntity.badRequest().build();

    String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
    User newUser = new User(userDTO.getName(), userDTO.getEmail(), encryptedPassword);

    this.userRepository.save(newUser);

    return ResponseEntity.ok().build();

  }

}
