package com.example.nikhil.notebook;

import java.io.Serializable;

/**
 * Created by nikhil on 27/7/17.
 */

public class Note implements Serializable{
    int id ;
    String Name , Description ;

    public Note(int id, String name, String description) {
        this.id = id;
        Name = name;
        Description = description;
    }

    public Note() {

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

    @Override
    public String toString() {
        return "Note Details: " +
                "\n\nID : " + id +
                "\n\nName : " + Name +
                "\n\nDescription : " +Description  ;
    }
}
