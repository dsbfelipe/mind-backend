package com.backend.backend.dto;

import com.backend.backend.model.Product;
import jakarta.persistence.Column;
import org.springframework.beans.BeanUtils;

public class ProductDTO {

  private Long id;

  private String name;

  private String description;

  private double value;

  private int amount;

  public ProductDTO(Product product) {
    BeanUtils.copyProperties(product, this);
  }

  public ProductDTO() {
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

}
