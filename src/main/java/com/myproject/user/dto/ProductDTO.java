package com.myproject.user.dto;

public class ProductDTO {
    Integer prodId;
    
    String brand;
    String category;
     String description;
    String image;
    double price;
    String productname;
    Integer rating;
     Integer sellerid;
     Integer stock;
     String subcategory;
     public Integer getProdId() {
            return prodId;
        }
        public void setProdId(Integer prodId) {
            this.prodId = prodId;
        }
    
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getProductname() {
        return productname;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public Integer getSellerid() {
        return sellerid;
    }
    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public String getSubcategory() {
        return subcategory;
    }

public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

        
    }

