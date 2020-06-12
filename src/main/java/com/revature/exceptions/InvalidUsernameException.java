package com.revature.exceptions;

public class InvalidUsernameException extends RuntimeException{

    @Override
    public String getMessage(){
        return "Invalid username, please choose another one.";
    }
}
