package com.example.techlab.model;

public class Electronics extends Products {

    public String productId;
    public String productManufacturer;
    public int amountBroken;

    public Electronics(String productId, String productManufacturer, String productCategory, String productName,
            int productStock, int amountBroken, String description, int id_) {
        this.productId = productId;
        this.productManufacturer = productManufacturer;
        super.name = productName;
        super.stock = productStock;
        this.amountBroken = amountBroken;
        super.category = productCategory;
        super.description = description;
        super.id_ = id_;
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
}
