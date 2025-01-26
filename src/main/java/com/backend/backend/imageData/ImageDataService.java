package com.backend.backend.imageData;

import com.backend.backend.model.Product;
import com.backend.backend.model.User;
import com.backend.backend.repository.ProductRepository;
import com.backend.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

  @Autowired
  private ImageDataRepository imageDataRepository;

  @Autowired
  private UserRepository personRepository;

  @Autowired
  private ProductRepository productRepository;

  public String uploadUserImage(MultipartFile file, String username) throws IOException {

    User person = (User) personRepository.findByEmail(username);

    imageDataRepository.save(ImageData.builder().name(person.getUsername())
        .type(file.getContentType()).imageData(ImageUtil.compressImage(file.getBytes())).build());


    person.setUserProfilePicture(imageDataRepository.findByName(username).get());
    return "Imagem adicionada! : " + person.getUsername();

  }

  public String uploadProductImage(MultipartFile file, String name) throws IOException {

    Product product = (Product) productRepository.findByName(name);

    imageDataRepository.save(ImageData.builder().name(product.getName())
        .type(file.getContentType()).imageData(ImageUtil.compressImage(file.getBytes())).buildProduct());


    product.setProductProfilePicture(imageDataRepository.findByName(name).get());
    return "Imagem adicionada! : " + product.getName();

  }

  @Transactional
  public ImageData getInfoByImageByName(String name) {
    Optional<ImageData> dbImage = imageDataRepository.findByName(name);

    return ImageData.builder()
        .name(dbImage.get().getName())
        .type(dbImage.get().getType())
        .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();
  }

  @Transactional
  public byte[] getImage(String name) {
    Optional<ImageData> dbImage = imageDataRepository.findByName(name);
    byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
    return image;
  }

}
