package com.backend.backend.service;

import com.backend.backend.dto.ProductDTO;
import com.backend.backend.model.Product;
import com.backend.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  public List<ProductDTO> showProducts() {
    List<Product> products = productRepository.findAll();
    return products.stream().map(ProductDTO::new).toList();
  }

  public ProductDTO showProduct(String name) {
    Product product = productRepository.findByName(name);
    return new ProductDTO(product);
  }

  public void create(ProductDTO productDTO) {
    Product product = new Product(productDTO);
    productRepository.save(product);
  }

  public ProductDTO update(String name, ProductDTO productDTO) {
    Product product = productRepository.findByName(name);

    product.setName(productDTO.getName());
    product.setDescription(productDTO.getDescription());
    product.setValue(productDTO.getValue());
    product.setAmount(productDTO.getAmount());

    return new ProductDTO(productRepository.save(product));

  }

  public void delete(String name) {
    Product product = productRepository.findByName(name);
    productRepository.delete(product);
  }

}
