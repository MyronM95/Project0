package com.revature.services;

import com.revature.dao.IPersonRepo;
import com.revature.dao.PersonRepoDB;
import com.revature.exceptions.InvalidUsernameException;
import com.revature.models.Person;

import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;


public class PersonService {
    ValidationService inputValidation = new ValidationService();
    IPersonRepo repo;
    PersonRepoDB repo2 = new PersonRepoDB();

    ConnectionService connectionService = ConnectionService.getInstance();


    public PersonService(IPersonRepo repo) {
        this.repo = repo;
    }

    public void createNewPerson() {
        boolean success = false;
        do {
            String name = inputValidation.getValidStringInput("Enter your name: ");

            String userName = inputValidation.getValidStringInput("Enter your username: ");

            String password = inputValidation.getValidStringInput("Enter your password");

            String reenterPassword = inputValidation.getValidStringInput("Re-enter your password");

            if (!password.equals(reenterPassword)) {
                System.out.println("Please make sure the password is the same.");
                createNewPerson();
            } else {
                try {


                    System.out.println("Creating User");
                    PreparedStatement ps = connectionService.getConnection().prepareStatement("insert into users (username, userpassword, name, is_admin) values (?,?,?,?)");
                    ps.setString(1, userName);
                    ps.setString(2, password);
                    ps.setString(3, name);
                    ps.setBoolean(4, false);
                    System.out.println("New User Added!");

                    success = true;
                    System.exit(0);
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());

                }
            }

        } while (!success);


//    public Person checkForUser(String userName) {
//        ArrayList<Person> array = repo2.getAllUsersNoPrint();
//        for (Person person: array) {
//            if(person.getUsername().equals(userName) && person.getPassword().equals(userPassword)) {
//                return person;
//            }
//        }
//        return null;
//    }

    }
}
