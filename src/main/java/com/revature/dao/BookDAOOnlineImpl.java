package com.revature.dao;

import com.revature.models.Book;
import com.revature.services.ConnectionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookDAOOnlineImpl implements BookDAO{
    ConnectionService connectionService = ConnectionService.getInstance();

    Scanner input = new Scanner(System.in);

    public BookDAOOnlineImpl(){}

    @Override
    public ArrayList<Book> getAllBooks() {
        int indexNum = 1;

        ArrayList<Book> bookArrayList = new ArrayList<>();

        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM books;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                Book book = new Book(rs.getString("book_name"), rs.getString("ISBN"), rs.getInt("author_id"));
                bookArrayList.add(book);
            }

            System.out.println("Currently are: " + bookArrayList.size() + " books.");



            for (Book book : bookArrayList) {

                System.out.println("["+indexNum+ "] " + book.toString());
                ++indexNum;
            }
            return bookArrayList;
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e){
            System.out.println("Error : " + e.getMessage());
        }

        return null;
    }


    private ArrayList<Book> getAllBooksNoPrint() {
        int indexNum = 1;


        ArrayList<Book> bookArrayList = new ArrayList<Book>();

        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM books;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Book book = new Book(rs.getString("book_name"), rs.getString("ISBN"), rs.getInt("author_id"));
                bookArrayList.add(book);
            }

            return bookArrayList;
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e){
            System.out.println("Error : " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Book> getMyBooks() {
        int indexNum = 1;
        ArrayList<Book> bookRatingsArrayList = new ArrayList<>();

        try {
            PreparedStatement ps = connectionService.getConnection().prepareStatement("select books.book_name, ratings.rating from ratings left join users on ratings.userid = users.id inner join books on ratings.isbn = books.isbn where users.username = ?");
            System.out.print("Enter your username: ");
            String userName = input.nextLine();
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Book book = new Book(rs.getString("book_name"), rs.getInt("rating"));
                bookRatingsArrayList.add(book);
            }

            System.out.println("You have read and rated: " + bookRatingsArrayList.size() + " books.");



            for (Book book : bookRatingsArrayList) {

                System.out.println("["+indexNum+ "] " + book.ratingString());
                ++indexNum;
            }
            return bookRatingsArrayList;
        }catch(SQLException e){
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e){
            System.out.println("Error : " + e.getMessage());
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
                System.out.println("Error : " + e.getMessage());
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
