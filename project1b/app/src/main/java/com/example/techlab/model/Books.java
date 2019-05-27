package com.example.techlab.model;

public class Books extends Products {

    public String Publisher;
    public String ISBN;
    public String Writers;

    public Books(int ID_, String bookTitle, String productWriters, String productISBN, String productPublisher, int productStock, String productDescription, String category, byte[] image,int productOnLoan) {
        this.ISBN = productISBN;
        this.Publisher = productPublisher;
        this.Writers = productWriters;
        super.category = "books";
        super.description = productDescription;
        super.name = bookTitle;
        super.stock = productStock;
        super.id_ = ID_;
        super.category = category;
        super.image = image;
        super.productOnLoan = productOnLoan;
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
