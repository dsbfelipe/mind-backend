package com.backend.backend.imageData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageDataController {

  @Autowired
  private ImageDataService imageDataService;

  @PostMapping("/user/{username}")
  public ResponseEntity<?> uploadUserImage(@RequestParam("image") MultipartFile file, @PathVariable("username") String username) throws IOException {
    String response = imageDataService.uploadUserImage(file, username);

    return ResponseEntity.status(HttpStatus.OK)
        .body(response);
  }

  @PostMapping("/product/{username}")
  public ResponseEntity<?> uploadProductImage(@RequestParam("image") MultipartFile file, @PathVariable("username") String name) throws IOException {
    String response = imageDataService.uploadProductImage(file, name);

    return ResponseEntity.status(HttpStatus.OK)
        .body(response);
  }

  @GetMapping("/info/{name}")
  public ResponseEntity<?>  getImageInfoByName(@PathVariable("name") String name){
    ImageData image = imageDataService.getInfoByImageByName(name);

    return ResponseEntity.status(HttpStatus.OK)
        .body(image);
  }

  @GetMapping("/{name}")
  public ResponseEntity<?> getImageByName(@PathVariable("name") String name){
    byte[] image = imageDataService.getImage(name);

    return ResponseEntity.status(HttpStatus.OK)
        .contentType(MediaType.valueOf("image/png"))
        .body(image);
  }

}
