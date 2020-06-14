package com.revature.dao;

import com.revature.models.Person;

import java.util.ArrayList;

public interface IPersonRepo {
    public Person addPerson(Person person);
    public ArrayList<Person> getAllUsers();

}
