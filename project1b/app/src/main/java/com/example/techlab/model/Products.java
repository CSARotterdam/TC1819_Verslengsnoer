package com.example.techlab.model;

public class Products {

    public String productId;
    public String productManufacturer;
    public String productName;
    public int productStock;
    public int amountBroken;
    public String productCategory;

    public String productPublisher;
    public String productISBN;
    public String productWriter1;
    public String productWriter2;
    public String productWriter3;
    public String productWriter4;


    public Products(String productId, String productManufacturer, String productName, int productStock, int amountBroken, String productCategory,
                    String productPublisher, String productISBN, String productWriter1, String productWriter2, String productWriter3, String productWriter4) {
        this.productId = productId;
        this.productManufacturer = productManufacturer;
        this.productName = productName;
        this.productStock = productStock;
        this.amountBroken = amountBroken;
        this.productCategory = productCategory;
        this.productPublisher = productPublisher;
        this.productISBN = productISBN;
        this.productWriter1 = productWriter1;
        this.productWriter2 = productWriter2;
        this.productWriter3 = productWriter3;
        this.productWriter4 = productWriter4;
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

    public String getProductISBN() {
        return productISBN;
    }

    public void setProductISBN(String productISBN) {
        this.productISBN = productISBN;
    }

    public String getProductPublisher() {
        return productPublisher;
    }

    public void setProductPublisher(String productPublisher) {
        this.productPublisher = productPublisher;
    }

    public String getProductWriter1() {
        return productWriter1;
    }

    public void setProductWriter1(String productWriter1) {
        this.productWriter1 = productWriter1;
    }

    public String getProductWriter2() {
        return productWriter2;
    }

    public void setProductWriter2(String productWriter2) {
        this.productWriter2 = productWriter2;
    }

    public String getProductWriter3() {
        return productWriter3;
    }

    public void setProductWriter3(String productWriter3) {
        this.productWriter3 = productWriter3;
    }

    public String getProductWriter4() {
        return productWriter4;
    }

    public void setProductWriter4(String productWriter4) {
        this.productWriter4 = productWriter4;
    }
}
