package com.revature.dao;

import com.revature.models.Book;
import com.revature.models.Person;
import com.revature.services.ConnectionService;
import com.revature.services.ValidationService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonRepoDB implements IPersonRepo{

    ConnectionService connectionService = ConnectionService.getInstance();
    ValidationService inputValidation = new ValidationService();
    //Scanner input = new Scanner(System.in);

    public PersonRepoDB() {
    }

    @Override
    public Person addPerson(Person person) {
        return null;
    }

    @Override
    public ArrayList<Person> getAllUsers() {
        int indexNum = 1;

        ArrayList<Person> personArrayList = new ArrayList<Person>();


        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");
        try {

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM public.users;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                Person person = new Person(rs.getInt("id"), rs.getString("username"),rs.getString("name"), rs.getString("userpassword"), rs.getBoolean("is_admin"));
                personArrayList.add(person);
            }


            System.out.println("Currently are: " + personArrayList.size() + " users.");



            for (Person person : personArrayList) {

                System.out.println("["+indexNum+ "] " + person.toString());
                ++indexNum;


            }
            return personArrayList;
        }catch(SQLException e){
            e.printStackTrace();

        } catch (Exception e){

        }

        return null;

    }

    public boolean deleteUser() {

        int indexNum = 1;
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("-------------------------------------------------------------------------------------------");
        try {
            ArrayList<Person> usersList = this.getAllUsers();
            Person person = usersList.get(indexNum -1);

            PreparedStatement ps = connectionService.getConnection().prepareStatement("DELETE from public.users where users.username = ?");
//            System.out.print("Enter the username of the profile you would like to delete : ");
            String userName = inputValidation.getValidStringInput("Enter the username of the profile you would like to delete : ");
            ps.setString(1, userName);

            boolean didWork = ps.execute();
            System.out.println("Deleted: " +userName);


            return didWork;


        } catch (Exception e) {
            System.out.println("No profile with that username exits.");
            return false;
        }

    }
    }
