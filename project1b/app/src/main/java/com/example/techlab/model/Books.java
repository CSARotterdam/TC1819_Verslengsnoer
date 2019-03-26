package com.example.techlab.model;

public class Books extends Products {

    public String Publisher;
    public String ISBN;
    public String Writers;

    public Books(String productName, int productStock, String productPublisher, String productISBN, String productWriters, String productDescription) {
        this.ISBN = productISBN;
        this.Publisher = productPublisher;
        this.Writers = productWriters;
        super.category = "books";
        super.description = productDescription;
        super.name = productName;
        super.stock = productStock;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getWriters() {
        return Writers;
    }

    public void setWriters(String writers) {
        Writers = writers;
    }
}
