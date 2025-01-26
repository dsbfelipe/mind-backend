package com.backend.backend.imageData;

import com.backend.backend.model.Product;
import com.backend.backend.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "imageData")
public class ImageData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String type;

  @Lob
  @Column(name = "imageData", length = 1000)
  private byte[] imageData;

  @OneToOne
  @JoinColumn
  private User user;

  @OneToOne
  @JoinColumn
  private Product product;

  public ImageData() {}

  private ImageData(String name, String type, byte[] imageData, User user) {
    this.name = name;
    this.type = type;
    this.imageData = imageData;
    this.user = user;
  }

  private ImageData(String name, String type, byte[] imageData, Product product) {
    this.name = name;
    this.type = type;
    this.imageData = imageData;
    this.product = product;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public byte[] getImageData() {
    return imageData;
  }

  public User getUser() {
    return user;
  }

  public Product getProduct() {
    return product;
  }

  public static class Builder {
    private String name;
    private String type;
    private byte[] imageData;
    private User user;
    private Product product;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder imageData(byte[] imageData) {
      this.imageData = imageData;
      return this;
    }

    public Builder user(User user) {
      this.user = user;
      return this;
    }

    public Builder product(Product product) {
      this.product = product;
      return this;
    }

    public ImageData build() {
      return new ImageData(name, type, imageData, user);
    }

    public ImageData buildProduct() {
      return new ImageData(name, type, imageData, product);
    }
  }

  public static Builder builder() {
    return new Builder();
  }

}
