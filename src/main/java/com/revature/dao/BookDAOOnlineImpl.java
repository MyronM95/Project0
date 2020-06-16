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



    public ArrayList<Book> getAllBooksRatings() {
        int indexNum = 1;

        ArrayList<Book> bookArrayList = new ArrayList<>();

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");
        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("select books.book_name, books.author_id, ROUND(AVG(ratings.rating),2) as avg_rating, books.ISBN from public.books left join public.ratings on ratings.ISBN = books.ISBN group by books.ISBN, books.book_name, books.author_id;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                Book book = new Book(rs.getString("book_name"), rs.getInt("author_id"), rs.getInt("avg_rating"), rs.getString("ISBN") );
                bookArrayList.add(book);
            }


            System.out.println("Currently are: " + bookArrayList.size() + " books.");



            for (Book book : bookArrayList) {

                System.out.println("["+indexNum+ "] " + book.ratingToString());
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



    @Override
    public ArrayList<Book> getAllBooks() {
        int indexNum = 1;

        ArrayList<Book> bookArrayList = new ArrayList<>();

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");
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



        ArrayList<Book> bookArrayList = new ArrayList<>();

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

    public ArrayList<Book> getProfiles() {
        int indexNum = 1;
        ArrayList<Book> bookRatingsArrayList = new ArrayList<>();

        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");
        try {
            PreparedStatement ps = connectionService.getConnection().prepareStatement("select books.book_name, ratings.rating from ratings left join users on ratings.userid = users.id inner join books on ratings.isbn = books.isbn where users.username = ?");
            System.out.print("Enter the username of the profile you would like to lookup : ");
            String userName = input.nextLine();
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Book book = new Book(rs.getString("book_name"), rs.getInt("rating"));
                bookRatingsArrayList.add(book);
                //System.out.println("HIII");
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
