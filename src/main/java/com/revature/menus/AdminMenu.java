package com.revature.menus;

import com.revature.dao.*;

import java.util.Scanner;

public class AdminMenu extends MainMenu{
    private Scanner input = new Scanner(System.in);
    BookDAOOnlineImpl bookDAOOnline = new BookDAOOnlineImpl();
    AuthorDAOOnlineImpl authorDAOOnline = new AuthorDAOOnlineImpl();
    AddBookMenu addBookMenu = new AddBookMenu();
    IPersonRepo iPersonRepo = null;




    public void start() {
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
                    bookDAOOnline.getAllBooksRatings();
                    break;
                case "2":
                    //authors list
                    authorDAOOnline.getAllAuthors();
                    break;
                case "3":
                    //myBooks menu
                    bookDAOOnline.getProfiles();
                    break;
                case "4":
                    //users lists and profiles
                    try {
                        iPersonRepo = new PersonRepoDB();
                        iPersonRepo.getAllUsers();
                    } catch (Exception e) {
                        System.out.println("Error : " + e.getMessage());
                        e.printStackTrace();
                    }

                    break;
                case "5":
                    //Add a book
                    //bookDAOOnline.addNewBook();
                    addBookMenu.start();
                    break;
                case "6":
                    //delete a user's profile
                    try {
                        iPersonRepo = new PersonRepoDB();
                        iPersonRepo.deleteUser();
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
