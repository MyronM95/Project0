package com.revature.services;

import com.revature.dao.IPersonRepo;
import com.revature.dao.PersonRepoDB;
import com.revature.dao.PersonRepoFile;
import com.revature.menus.AdminMenu;
import com.revature.menus.MainMenu;
import com.revature.menus.UserMenu;
import com.revature.models.Author;
import com.revature.models.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginService {
    ValidationService inputValidation = new ValidationService();
    IPersonRepo repo = new PersonRepoFile();
    Scanner input = new Scanner(System.in);
    PersonRepoDB repo2 = new PersonRepoDB();

    ConnectionService connectionService = ConnectionService.getInstance();
    MainMenu mainMenu = new MainMenu();
    private static final Logger logger = LogManager.getLogger(LoginService.class.getName());

//    public LoginService(){}

    public void LoginService() {

        int userId;
        String userNameDB;
        String passwordDB;
        boolean is_admin;
        try {
            //System.out.print("Username: ");
            //String userName = input.next();
            String userName = inputValidation.getValidStringInput("Username: ");
//            System.out.print("Password: ");
//            String userPassword = input.nextLine();
            String userPassword = inputValidation.getValidStringInput("Password: ");

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM users WHERE username = ? and userpassword = ?");
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                userId = rs.getInt(1);
                userNameDB = rs.getString(2);
                passwordDB = rs.getString(3);
                is_admin = rs.getBoolean(5);


                if (userName.equalsIgnoreCase(userNameDB) && userPassword.equals(passwordDB)) {

                    if (is_admin) {
                        System.out.println("-------------------------------------------------------------------------------------------");
                        System.out.println("-------------------------------------------------------------------------------------------");
                        System.out.println("Your userId: " + userId + " and your username is : " + userNameDB);
                        System.out.println("-------------------------------------------------------------------------------------------");
                        AdminMenu adminMenu = new AdminMenu();
                        logger.info("Logging an administrator in");
                        adminMenu.start();

                    } else {
                        System.out.println("-------------------------------------------------------------------------------------------");
                        System.out.println("-------------------------------------------------------------------------------------------");
                        System.out.println("Your userId: " + userId + " and your username is : " + userNameDB);
                        System.out.println("-------------------------------------------------------------------------------------------");
                        UserMenu userMenu = new UserMenu();
                        logger.info("Logging a normal user in");
                        userMenu.start();
                    }

                } else {
                    System.out.println("Username not found");
                    mainMenu.start();
                }

            }System.out.println("Username not found");
            input.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
//    public Person checkForUser(String userName, String userPassword) {
//        ArrayList<Person> array = repo2.getAllUsersNoPrint();
//        for (Person person: array) {
//            if(person.getUsername().equals(userName) && person.getPassword().equals(userPassword)) {
//                return person;
//            }
//        }
//        return null;
//    }


    }
