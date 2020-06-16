package com.revature.menus;

import com.revature.dao.PersonRepoDB;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

public class AdminMenuTest extends TestCase {
    @Before public void initialize(){}

    @Test
    public void teststart(){
        Scanner input = new Scanner(System.in);
        String userInput;
        do {
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------------");

            System.out.println("Welcome to the GoodReads Console Application!");
            System.out.println("Please choose from the following options");
            System.out.println("[1] Rate a book");
            System.out.println("[2] See authors");
            System.out.println("[3] Search users' profiles (including your own)");
            System.out.println("[4] See users");
            System.out.println("[5] Add a book");
            System.out.println("[6] Delete a user");
            System.out.println("[7] Exit application");

            userInput = input.nextLine();
            switch (userInput) {
                case "1":
                    //books menu
                    //bookDAOOnline.getAllBooksRatings();
                    System.out.println("Call bookDAOOnline getAllBookRatings() method");
                    break;
                case "2":
                    //authors list
                    //authorDAOOnline.getAllAuthors();
                    System.out.println("Call authorDAOOnline getAllAuthors() method");
                    break;
                case "3":
                    //myBooks menu
                    //bookDAOOnline.getProfiles();
                    System.out.println("Call bookDAOOnline getAllProfiles() method");
                    break;
                case "4":
                    //users lists and profiles
                    try {
                        //iPersonRepo = new PersonRepoDB();
                        //iPersonRepo.getAllUsers();
                        System.out.println("Instantiate a new iPersonRepo as a PersonRepoDB()");
                        System.out.println("Call iPersonRepo getAllUsers() method");
                    } catch (Exception e) {
                        System.out.println("Error : " + e.getMessage());
                        e.printStackTrace();
                    }

                    break;
                case "5":
                    //Add a book
                    //bookDAOOnline.addNewBook();
                    //addBookMenu.start();
                    System.out.println("Call addBookMenu start() method");
                    break;
                case "6":
                    //delete a user's profile
                    try {
//                        iPersonRepo = new PersonRepoDB();
//                        iPersonRepo.deleteUser();
                        System.out.println("Instantiate a new iPersonRepo as a PersonRepoDB()");
                        System.out.println("Call iPersonRepo deleteUsers() method");
                    } catch (Exception e) {
                        System.out.println("Error : " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                case "7":
                    System.out.println("Exiting....");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input please try again!");

            }
        } while(!userInput.equals("5"));
        input.close();
    }
    }

