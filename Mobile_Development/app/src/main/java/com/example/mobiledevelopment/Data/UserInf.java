package com.example.mobiledevelopment.Data;

import java.util.ArrayList;

public class UserInf {
    private String email;
    private String username;
    private ArrayList<Property> properties;

    public UserInf(){}
    public UserInf(String email, String username){
        this.email = email;
        this.username = username;
    }

    public void setEmail(String email){this.email = email;}
    public void setUsername(String username){this.username = username;}

    public String getUsername(){return this.username;}
    public String getEmail(){return this.email;}

    public void insertProperty(Property property){
        properties.add(property);
    }
    public void displayProperties(){
        for(Property K : properties){
            K.display();
        }
    }
    public Property getProperty(int index){
        return properties.get(index);
    }

    public String display(){return "Username: " + this.username + "/nEmail: " + this.email;}
}
