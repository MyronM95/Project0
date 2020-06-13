package com.revature.models;

public class Book {

    private String bookName;
    private String isbn;
    private int author_id;

    public Book(String bookName, String isbn, int author_id) {
        this.bookName = bookName;
        this.isbn = isbn;
        this.author_id = author_id;
    }

    public Book(String book_name, String isbn) {
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

    public int getAuthorId() {
        return author_id;
    }

    public void setAuthor(int author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author_id='" + author_id + '\'' +
                '}';
    }
}
