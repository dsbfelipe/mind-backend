package com.backend.backend.service;

import com.backend.backend.dto.UserDTO;
import com.backend.backend.model.User;
import com.backend.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<UserDTO> showUsers() {
    List<User> users = userRepository.findAll();
    return users.stream().map(UserDTO::new).toList();
  }

  public UserDTO showUser(String userName) {
    User user = (User) userRepository.findByEmail(userName);
    return new UserDTO(user);
  }

  //___________________________________________________________

  public void create(UserDTO userDTO) {

  }

  //___________________________________________________________

  public UserDTO update(String userName, UserDTO userDTO) {
    User user = (User) userRepository.findByEmail(userName);

    user.setPassword(userDTO.getPassword());
    user.setEmail(userDTO.getEmail());

    return new UserDTO(userRepository.save(user));

  }

  public void delete(String userName) {
    User user = (User) userRepository.findByEmail(userName);
    userRepository.delete(user);
  }

}
