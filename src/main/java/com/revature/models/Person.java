package com.revature.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {
    private static final Logger logger = LogManager.getLogger(Person.class.getName());

    private int id;
    private String name;
    private String username;
    private String password;
    private boolean isAdmin;


    public Person(){

    }

    public Person(String username, String password){
        logger.info("New Person Created: " + username + " " + password );
        this.username = username;
        this.password = password;
    }

    public Person(int id, String name, String username, String password, boolean isAdmin) {
        logger.info("New Person Created: "+ id + " "+ username + " " +name +" "+ password + " "+ isAdmin);
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Person(String name, String userName, String password) {
        logger.info("New Person Created: "+ username + " " +name +" "+ password );
        this.name = name;
        this.username = userName;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                isAdmin == person.isAdmin &&
                Objects.equals(name, person.name) &&
                Objects.equals(username, person.username) &&
                Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, password, isAdmin);
    }
}