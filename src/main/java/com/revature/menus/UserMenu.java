package com.revature.menus;

import com.revature.dao.AuthorDAOOnlineImpl;
import com.revature.dao.BookDAOOnlineImpl;
import com.revature.dao.IPersonRepo;
import com.revature.models.Book;
import com.revature.services.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class UserMenu extends MainMenu{
    //private Scanner input = new Scanner(System.in);
    BookDAOOnlineImpl bookDAOOnline = new BookDAOOnlineImpl();
    AuthorDAOOnlineImpl authorDAOOnline = new AuthorDAOOnlineImpl();
    //IPersonRepo iPersonRepo = null;
    ValidationService inputValidation = new ValidationService();
    public static final Logger logger = (Logger) LogManager.getLogger(UserMenu.class.getName());


    public void start() {
    String userInput;
    do {

        System.out.println("Welcome to the GoodReads Console Application!");
        System.out.println("Please choose from the following options");
        System.out.println("[1] Rate a book");
        System.out.println("[2] See authors");
        System.out.println("[3] Search users' profiles (including your own)");
        System.out.println("[4] Exit application");

        //switch for user input to point them to appropriate places in the code

        //userInput = input.nextLine();
        userInput = inputValidation.getValidStringInput("Please choose from the above options");
        switch (userInput) {
            case "1":
                //books menu
                logger.info("Creating a new bookDAOOnline object");
                bookDAOOnline.getAllBooksRatings();
                bookDAOOnline.addRating();
                break;
            case "2":
               //authors list
                logger.info("Creating a new authorDAOOnline object");
                authorDAOOnline.getAllAuthors();
                break;
            case "3":
                //users list including the user's
                logger.info("Creating a new bookDAOOnline object");
                bookDAOOnline.getProfiles();
                break;
            case "4":
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

