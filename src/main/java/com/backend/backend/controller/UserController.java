package com.backend.backend.controller;

import com.backend.backend.dto.UserDTO;
import com.backend.backend.model.User;
import com.backend.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<UserDTO> showUsers() {
    return userService.showUsers();
  }

  @GetMapping("/{userName}")
  public UserDTO showUser(@PathVariable("userName") String userName) {
    return userService.showUser(userName);
  }

  @PutMapping("/{userName}")
  public UserDTO update(@PathVariable("userName") String userName, @RequestBody UserDTO userDTO) {
    return userService.update(userName, userDTO);
  }

  @DeleteMapping("/{userName}")
  public void delete(@PathVariable("userName") String userName) {
    userService.delete(userName);
  }

}
