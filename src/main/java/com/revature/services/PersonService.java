package com.revature.services;

import com.revature.dao.IPersonRepo;
import com.revature.exceptions.InvalidUsernameException;
import com.revature.models.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonService {
    ValidationService inputValidation = new ValidationService();
    IPersonRepo repo;

    public PersonService(IPersonRepo repo) {
        this.repo = repo;
    }
    public void createNewPerson() {
        boolean success = false;
        //get user input
        do {
            String name = inputValidation.getValidStringInput("Enter your name: ");

            String userName = inputValidation.getValidStringInput("Enter your username: ");

            String password = inputValidation.getValidStringInput("Enter your password");

            String reenterPassword = inputValidation.getValidStringInput("Re-enter your password");

            if (!password.equals(reenterPassword)){
                System.out.println("Please make sure the password is the same.");
//                createNewPerson();
            }else{
                try {
                    final Person newPerson = new Person(name, userName, password);
                    System.out.println("Creating User");
                    //Note that there's a thread constructor that takes in a runnable
                    // Note that Runnable is a functional interface, it has one and only one method run()
                    // lambda expressions are used to represent a method interface
                    // we're using a lambda expression to represent a runnable
                    Thread addPersonThread = new Thread(() -> {
                        repo.addPerson(newPerson);
                        System.out.println("New User Added!");
                    });
                    addPersonThread.start();
                    success = true;
                } catch (InvalidUsernameException e) {
                    System.out.println("Invalid username, please try another one!");
                }
            }
        } while (!success);


    }
    public void getPersons() {
        ArrayList<Person> retrievedPersons = (ArrayList<Person>) repo.getAllUsers();
        for(Person person: retrievedPersons) {
            System.out.println(person);
        }
    }
}
