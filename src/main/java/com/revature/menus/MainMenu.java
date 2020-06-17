package com.revature.menus;

import com.revature.dao.IPersonRepo;
import com.revature.dao.PersonRepoDB;
import com.revature.dao.PersonRepoFile;
import com.revature.models.Person;
import com.revature.services.LoginService;
import com.revature.services.PersonService;
import com.revature.services.ValidationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Scanner;

public class MainMenu {
    Scanner input = new Scanner(System.in);
    private PersonService service = new PersonService(new PersonRepoFile());
    //IPersonRepo iPersonRepo = null;
    ValidationService inputValidation = new ValidationService();
    public static final Logger logger = (Logger) LogManager.getLogger(MainMenu.class.getName());

    public void start() {
        String userInput = "";
        do {
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------------");

            System.out.println("Welcome to the GoodReads Console Application!");
            System.out.println("Please choose from the following options");
            System.out.println("[1] Log in");
            System.out.println("[2] Create account");
            System.out.println("[3] Exit application");

            //switch for user input to point them to appropriate places in the code

            userInput = inputValidation.getValidStringInput("Please choose from the above options: ");
            //userInput = input.nextLine();
                switch (userInput) {
                    case "1":
                        //login menu
                        logger.info("Creating a new LoginService object");
                        LoginService loginService = new LoginService();
                        loginService.LoginService();
                        break;
                    case "2":
                        logger.info("Creating new PersonService object");
                        service.createNewPerson();
//                        try {
//                            iPersonRepo = new PersonRepoDB();
//                            iPersonRepo.addPerson();
//                        } catch (Exception e) {
//                            System.out.println("Error : " + e.getMessage());
//                            e.printStackTrace();
//                        }
                        break;
                    case "3":
                        System.out.println("Exiting....");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid input please try again!");
                }
        }
            while (input.hasNextInt()) ;
            input.close();
    }
}
