package com.example.nikhil.tabbed;

/**
 * Created by nikhil on 22/3/17.
 */

import com.google.firebase.database.IgnoreExtraProperties;


public class User {

    public  String link;
    public  String id;
    public String email;

    public User()
    {

    }

    public User(String link,String id,String email)
    {
        this.link = link;
        this.id = id;
        this.email = email;

    }

}
