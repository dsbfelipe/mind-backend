package com.backend.backend.controller;

import com.backend.backend.dto.ProductDTO;
import com.backend.backend.dto.UserDTO;
import com.backend.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<ProductDTO> showProducts() {
    return productService.showProducts();
  }

  @GetMapping("/{name}")
  public ProductDTO showProduct(@PathVariable("name") String name) {
    return productService.showProduct(name);
  }

  @PostMapping
  public void create(@RequestBody ProductDTO productDTO) {
    productService.create(productDTO);
  }

  @PutMapping("/{name}")
  public ProductDTO update(@PathVariable("name") String name, @RequestBody ProductDTO productDTO) {
    return productService.update(name, productDTO);
  }

  @DeleteMapping("/{name}")
  public void delete(@PathVariable("name") String name) {
    productService.delete(name);
  }

}
