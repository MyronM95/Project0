package com.revature.services;

import com.revature.dao.IPersonRepo;
import com.revature.dao.PersonRepoFile;
import com.revature.menus.AdminMenu;
import com.revature.menus.MainMenu;
import com.revature.menus.UserMenu;
import com.revature.models.Person;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LoginService {
    ValidationService inputValidation = new ValidationService();
    IPersonRepo repo = new PersonRepoFile();
    Scanner input = new Scanner(System.in);


    ConnectionService connectionService = ConnectionService.getInstance();

    public LoginService(){}

    public void LoginService() {
        int userId;
        String userNameDB;
        String passwordDB;
        Boolean is_admin;
        try {
            //System.out.print("Username: ");
            //String userName = input.next();
            String userName = inputValidation.getValidStringInput("Username: ");
//            System.out.print("Password: ");
//            String userPassword = input.next();
            String userPassword = inputValidation.getValidStringInput("Password: ");

            PreparedStatement ps = connectionService.getConnection().prepareStatement("SELECT * FROM users WHERE username = ? and userpassword = ?");
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
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
                        adminMenu.start();

                    } else {
                        System.out.println("-------------------------------------------------------------------------------------------");
                        System.out.println("-------------------------------------------------------------------------------------------");
                        System.out.println("Your userId: " + userId + " and your username is : " + userNameDB);
                        System.out.println("-------------------------------------------------------------------------------------------");
                        UserMenu userMenu = new UserMenu();
                        userMenu.start();

                    }
                } else {
                    System.out.println("Username not found");
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.start();
                }

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    }
