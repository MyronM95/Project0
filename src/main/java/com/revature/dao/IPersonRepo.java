package com.revature.dao;

import com.revature.models.Person;

import java.util.ArrayList;

public interface IPersonRepo {
   //boolean addPerson(Person person);
   ArrayList<Person> getAllUsers();
   boolean deleteUser();


}
