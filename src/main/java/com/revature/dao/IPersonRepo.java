package com.revature.dao;

import com.revature.models.Person;

import java.util.List;

public interface IPersonRepo {
    public Person addPerson(Person person);
    public List<Person> getAllUsers();
}
