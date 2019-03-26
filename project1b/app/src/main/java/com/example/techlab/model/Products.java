package com.example.techlab.model;

public class Products {
    protected String name;
    protected int stock;
    protected String description;

    //Needs to be changed: move data into Electronics and Books.java and have them be child of Products.java by extending.
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
