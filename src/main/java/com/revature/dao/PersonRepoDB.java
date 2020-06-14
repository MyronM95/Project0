package com.revature.dao;

import com.revature.models.Book;
import com.revature.models.Person;
import com.revature.services.ConnectionService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonRepoDB implements IPersonRepo{

    ConnectionService connectionService = ConnectionService.getInstance();


    @Override
    public Person addPerson(Person person) {
        return null;
    }

    @Override
    public ArrayList<Person> getAllUsers() {
        int indexNum = 1;

        ArrayList<Person> personArrayList = new ArrayList<Person>();

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
}
