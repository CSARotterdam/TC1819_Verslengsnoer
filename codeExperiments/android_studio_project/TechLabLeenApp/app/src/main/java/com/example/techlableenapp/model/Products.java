package com.example.techlableenapp.model;

public class Products {

    public String productId;
    public String productManufacturer;
    public String productName;
    public int productStock;
    public int amountBroken;
    public String productCategory;

    public Products(String productId, String productManufacturer, String productName,
                    int productStock, int amountBroken, String productCategory) {
        this.productId = productId;
        this.productManufacturer = productManufacturer;
        this.productName = productName;
        this.productStock = productStock;
        this.amountBroken = amountBroken;
        this.productCategory = productCategory;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
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
