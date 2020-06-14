package com.revature.dao;

import com.revature.models.Author;
import com.revature.models.Book;

import java.util.ArrayList;

public interface AuthorDao {
    public ArrayList<Author> getAllAuthors();
    public boolean addNewAuthor(Author author);
}
