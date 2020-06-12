package com.revature.menus;

import com.revature.dao.PersonRepoFile;
import com.revature.services.PersonService;

import java.util.Scanner;

public class MainMenu {
    private Scanner input = new Scanner(System.in);
    private PersonService service = new PersonService(new PersonRepoFile());

    public void start() {
        String userInput;
        do {
            System.out.println("Welcome to the GoodReads Console Application!");
            System.out.println("Please choose from the following options");
            System.out.println("[1] Log in");
            System.out.println("[2] Create account");
            System.out.println("[3] Exit application");

            //switch for user input to point them to appropriate places in the code

            userInput = input.nextLine();
            switch (userInput) {
                case "1":
                    //login menu
                    break;
                case "2":
                    service.createNewPerson();
                    break;
                case "3":
                    System.out.println("Exiting....");
                    break;
                default:
                    System.out.println("Invalid input please try again!");

            }
        } while(!userInput.equals("2"));
    }
}
