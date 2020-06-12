package com.revature.models;

public class Book {

    private String bookName;
    private String isbn;
    private String author;

    public Book(String bookName, String isbn, String author) {
        this.bookName = bookName;
        this.isbn = isbn;
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
