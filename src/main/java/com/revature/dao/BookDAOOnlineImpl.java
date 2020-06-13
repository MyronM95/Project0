package com.revature.dao;

import com.revature.models.Book;
import com.revature.services.ConnectionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOOnlineImpl implements BookDAO{
    ConnectionService connectionService = ConnectionService.getInstance();

    public BookDAOOnlineImpl(){}

    @Override
    public ArrayList<Book> getAllBooks() {
        int indexNum = 1;

        //Instantiate a new ArrayLists of Medications
        ArrayList<Book> bookArrayList = new ArrayList<Book>();

        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM books;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //Gets the data from the specified columns and uses them as parameters to create a new Medication
                Book book = new Book(rs.getString("book_name"), rs.getString("ISBN"), rs.getInt("author_id"));
                //Adds the new medication to the medicationList Array
                bookArrayList.add(book);
            }

            System.out.println("Currently are: " + bookArrayList.size() + " books.");



            for (Book book : bookArrayList) {

                System.out.println("["+indexNum+ "] " + book.toString());
                ++indexNum;
            }
            return bookArrayList;
        }catch(SQLException e){
            e.printStackTrace();

        } catch (Exception e){

        }

        return null;
    }


    private ArrayList<Book> getAllBooksNoPrint() {
        int indexNum = 1;

        //Instantiate a new ArrayLists of Medications
        ArrayList<Book> bookArrayList = new ArrayList<Book>();

        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM books;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                //Gets the data from the specified columns and uses them as parameters to create a new Medication
                Book book = new Book(rs.getString("book_name"), rs.getString("ISBN"), rs.getInt("author_id"));
                //Adds the new medication to the medicationList Array
                bookArrayList.add(book);
            }

            return bookArrayList;
        }catch(SQLException e){
            e.printStackTrace();

        } catch (Exception e){

        }

        return null;
    }



    @Override
    public boolean addNewBook(Book book) {
        book.toString();
        try {

            ArrayList<Book> bookArrayList = this.getAllBooksNoPrint();

            bookArrayList.add(book);

            try{

                PreparedStatement ps = connectionService.getConnection().prepareStatement("INSERT INTO books (book_name, author_id, ISBN) VALUES (?,?,?);");
                ps.setString(1, book.getBookName());
                ps.setInt(2, book.getAuthorId());
                ps.setString(3, book.getIsbn());
                boolean checking = ps.execute();

                return checking;


            }catch (SQLException e){
                e.printStackTrace();
            }


            return true;

        } catch(Exception e){
            System.out.println(e.getStackTrace());
            System.out.println("Error Adding Book. Please Check your inputs.");
            return false;
        }

    }
}
