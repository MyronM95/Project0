package com.revature.dao;

import com.revature.models.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepoFile implements IPersonRepo{
    private String filepath = "C:\\Users\\myron\\IdeaProjects\\Project0\\src\\main\\java\\com\\revature\\resources\\Person.txt";

    @Override
    public synchronized Person addPerson(Person person) {
        ArrayList<Person> currentPersons = (ArrayList<Person>) this.getAllUsers();
        try {
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream(filepath));
            currentPersons.add(person);
            objectOutputStream.writeObject(currentPersons);
            objectOutputStream.close();
            return person;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Person> getAllUsers() {
        try {
            ObjectInputStream inputStream =
                    new ObjectInputStream(new FileInputStream(filepath));
            List<Person> retrievedPersons = (ArrayList<Person>) inputStream.readObject();
            inputStream.close();
            return (ArrayList<Person>) retrievedPersons;
        } catch (IOException e) {
            // TODO Auto-generated catch block
           e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new ArrayList<Person>();
    }

    @Override
    public boolean deleteUser() {
        return false;
    }



}
