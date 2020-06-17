package com.revature.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Author {
    private static final Logger logger = LogManager.getLogger(Author.class.getName());

    private String name;

    public Author(){}

    public Author(String name) {
        logger.info("New Author Created: " + name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }
}
