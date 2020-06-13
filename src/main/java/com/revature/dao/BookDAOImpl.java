package com.revature.dao;

import com.revature.models.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    ArrayList<Book> bookArrayList = new ArrayList<>();
    String filePath = "C:\\Users\\myron\\IdeaProjects\\Project0\\src\\main\\java\\com\\revature\\resources\\Book.txt";

    @Override
    public ArrayList<Book> getAllBooks() {


        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filePath)));
            ArrayList<Book> arrayBookList = (ArrayList<Book>) objectInputStream.readObject();
            System.out.println("Got Array List");
            objectInputStream.close();

            System.out.println("Currently are: " + bookArrayList.size() + " Books.");
            for (Book book : bookArrayList) {

                System.out.println(book.toString());
            }

            return bookArrayList;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Not found");
        }


        return null;
    }

    private ArrayList<Book> getAllBooksNoPrint() {


        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filePath)));
            ArrayList<Book> bookList = (ArrayList<Book>) objectInputStream.readObject();
            objectInputStream.close();

            return bookList;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Not found");
        } catch (Exception e) {

        }


        return null;
    }


    @Override
    public boolean addNewBook(Book book) {

        book.toString();

        try {
            try {
                bookArrayList = this.getAllBooksNoPrint();
            } catch (Exception e) {

            }

            bookArrayList.add(book);

            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
                objectOutputStream.writeObject(bookArrayList);
                System.out.println("Added Medication Successfully.");
                objectOutputStream.close();

            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }


            return true;

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println("Error Adding Medication. Please Check your inputs.");
            return false;
        }
    }

    public ArrayList<Book> getBookArrayList() {
        return bookArrayList;
    }

    public void setBookArrayList(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

}