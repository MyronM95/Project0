package com.revature.menus;

import com.revature.dao.BookDAOOnlineImpl;

import java.util.Scanner;

public class UserMenu extends MainMenu{
    private Scanner input = new Scanner(System.in);

    public void start() {
    String userInput;
    do {
        System.out.println("Welcome to the GoodReads Console Application!");
        System.out.println("Please choose from the following options");
        System.out.println("[1] Rate a book");
        System.out.println("[2] See authors");
        System.out.println("[3] See my books");
        System.out.println("[4] See users");
        System.out.println("[5] Exit application");

        //switch for user input to point them to appropriate places in the code

        userInput = input.nextLine();
        switch (userInput) {
            case "1":
                //books menu
                BookDAOOnlineImpl bookDAOOnline = new BookDAOOnlineImpl();
                bookDAOOnline.getAllBooks();
                break;
            case "2":
               //authors list
                break;
            case "3":
                //myBooks menu
                break;
            case "4":
                //users list
                break;
            case "5":
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

