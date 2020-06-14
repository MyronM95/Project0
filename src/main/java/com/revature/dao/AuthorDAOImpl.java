package com.revature.dao;

import com.revature.models.Author;
import com.revature.models.Book;

import java.io.*;
import java.util.ArrayList;

public class AuthorDAOImpl implements AuthorDao{
    ArrayList<Author> authorArrayList = new ArrayList<>();
    String filePath = "C:\\Users\\myron\\IdeaProjects\\Project0\\src\\main\\java\\com\\revature\\resources\\Author.txt";


    @Override
    public ArrayList<Author> getAllAuthors() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filePath)));
            ArrayList<Author> authorArrayList = (ArrayList<Author>) objectInputStream.readObject();
            System.out.println("Got Array List");
            objectInputStream.close();

            System.out.println("Currently are: " + authorArrayList.size() + " Authors.");
            for (Author author : authorArrayList) {

                System.out.println(author.toString());
            }

            return authorArrayList;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class Not found");
        }


        return null;
    }
    private ArrayList<Author> getAllAuthorsNoPrint() {


        try {
            ObjectInputStream objectInputStream = new ObjectInputStream((new FileInputStream(filePath)));
            ArrayList<Author> authorArrayList = (ArrayList<Author>) objectInputStream.readObject();
            objectInputStream.close();

            return authorArrayList;

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
    public boolean addNewAuthor(Author author) {

        author.toString();

        try {
            try {
                authorArrayList = this.getAllAuthorsNoPrint();
            } catch (Exception e) {

            }

            authorArrayList.add(author);

            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
                objectOutputStream.writeObject(authorArrayList);
                System.out.println("Added Author Successfully.");
                objectOutputStream.close();

            } catch (IOException e) {
                System.out.println(e.getStackTrace());
            }


            return true;

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            System.out.println("Error Adding Author. Please Check your inputs.");
            return false;
        }
    }
}
