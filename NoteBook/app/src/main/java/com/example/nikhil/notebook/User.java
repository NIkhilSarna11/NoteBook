package com.example.nikhil.notebook;

/**
 * Created by nikhil on 27/7/17.
 */

public class User {
    int id ;
    String Name , Description ;

    public User(int id, String name, String description) {
        this.id = id;
        Name = name;
        Description = description;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
