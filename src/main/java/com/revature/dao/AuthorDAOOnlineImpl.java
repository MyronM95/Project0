package com.revature.dao;

import com.revature.models.Author;
import com.revature.models.Book;
import com.revature.services.ConnectionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorDAOOnlineImpl implements AuthorDao{
    ConnectionService connectionService = ConnectionService.getInstance();
    public AuthorDAOOnlineImpl(){}

    @Override
    public ArrayList<Author> getAllAuthors() {
        int indexNum = 1;

        //Instantiate a new ArrayLists of Authors
        ArrayList<Author> authorArrayList = new ArrayList<Author>();

        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM authors;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                Author author = new Author(rs.getString("author_name"));
                authorArrayList.add(author);
            }

            System.out.println("Currently are: " + authorArrayList.size() + " authors.");



            for (Author author : authorArrayList) {

                System.out.println("["+indexNum+ "] " + author.toString());
                ++indexNum;
            }
            return authorArrayList;
        }catch(SQLException e){
            e.printStackTrace();

        } catch (Exception e){

        }

        return null;
    }

    private ArrayList<Author> getAllAuthorsNoPrint() {
        int indexNum = 1;


        ArrayList<Author> authorArrayList = new ArrayList<Author>();

        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM authors;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                Author author = new Author(rs.getString("name"));
                authorArrayList.add(author);
            }

            return authorArrayList;
        }catch(SQLException e){
            e.printStackTrace();

        } catch (Exception e){

        }

        return null;
    }


    @Override
    public boolean addNewAuthor(Author author) {
        author.toString();
        try {

            ArrayList<Author> authorArrayList = this.getAllAuthorsNoPrint();

            authorArrayList.add(author);

            try{

                PreparedStatement ps = connectionService.getConnection().prepareStatement("INSERT INTO authors (name) VALUES (?);");
                ps.setString(1, author.getName());
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

