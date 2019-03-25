package com.example.techlab.model;

public class Electronics extends Products {

    public String productId;
    public String productManufacturer;
    public int amountBroken;
    public String productCategory;

    public Electronics(String productId, String productManufacturer, String productName,
                       int productStock, int amountBroken, String productCategory, String description) {
        this.productId = productId;
        this.productManufacturer = productManufacturer;
        super.name = productName;
        super.stock = productStock;
        this.amountBroken = amountBroken;
        this.productCategory = productCategory;
        super.description = description;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public int getAmountBroken() {
        return amountBroken;
    }

    public void setAmountBroken(int amountBroken) {
        this.amountBroken = amountBroken;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}