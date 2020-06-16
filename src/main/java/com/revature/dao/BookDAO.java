package com.revature.dao;

import com.revature.models.Book;

import java.util.ArrayList;

public interface BookDAO {
    public ArrayList<Book> getAllBooks();
    public ArrayList<Book> getProfiles();
    public ArrayList<Book> getAllBooksRatings();
    public boolean addNewBook(Book book);
}
