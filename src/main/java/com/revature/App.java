package com.revature;


import com.revature.menus.MainMenu;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;



public class App {
    private static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main( String[] args ){

        MainMenu menu = new MainMenu();
        menu.start();

    }
}
