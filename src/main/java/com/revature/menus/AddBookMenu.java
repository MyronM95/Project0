package com.revature.menus;

import com.revature.dao.BookDAOOnlineImpl;
import com.revature.models.Book;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AddBookMenu extends MainMenu{

    String bookName;
    int author_id;
    String ISBN;

    @Override
    public void start() {
        BookDAOOnlineImpl bookDAOOnline = null;

        try {
            bookDAOOnline = new BookDAOOnlineImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
//       if(input.hasNextLine()) {
           try {
               System.out.println("Please Enter book name: ");
               bookName = input.nextLine();

               System.out.println("What is the book's ISBN?");
               ISBN = input.nextLine();

               System.out.println("What is the author ID?");
               author_id = input.nextInt();


               Book book = new Book(bookName, ISBN, author_id);

               System.out.println(book.toString());
               bookDAOOnline.addNewBook(book);

           } catch (NoSuchElementException e) {
               System.out.println("Error: " + e.getMessage());
           }
//       }
        input.close();
    }
}


