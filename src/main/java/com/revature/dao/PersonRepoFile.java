package com.revature.dao;

import com.revature.models.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonRepoFile implements IPersonRepo{
    private String filepath = "C:\\Users\\Myron.000\\IdeaProjects\\trainer-code\\Week1Java\\TourOfHeros\\Project0\\src\\main\\java\\com\\revature\\resources\\Person.txt";

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
    public List<Person> getAllUsers() {
        // TODO Auto-generated method stub
        try {
            ObjectInputStream inputStream =
                    new ObjectInputStream(new FileInputStream(filepath));
            List<Person> retrievedPersons = (ArrayList<Person>) inputStream.readObject();
            inputStream.close();
            return retrievedPersons;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            //Just in class Hero class is not found
            e.printStackTrace();
        }

        return new ArrayList<Person>();
    }
}
