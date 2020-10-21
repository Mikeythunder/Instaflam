package com.example.instafalm;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

//Need to include the @ParseClassName to match the name of the class in the Parse website
@ParseClassName("Post")
public class Post extends ParseObject {

    //Each parsed class needs to have a key for each of the values defined on the parse website
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";

    //Each of these keys needs to have a getter and setter
    public String getDescription(){
        //Get string is a method defined in the parseObject calss that is implemented
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description){
        //put is a method that puts the data into the class
        put(KEY_DESCRIPTION, description);
    }

    //This one returns a special data type defined in Parse because it is an image
    public ParseFile getImage(){
        //Get parse file is a method defined in the parseObject class that is implemented
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image){
        put(KEY_IMAGE, image);
    }

    //This one returns a special data type defined in Parse because it is a user
        //Parse user is the class for a user in parse
    public ParseUser getUser(){
        //Get parse user is a method defined in the parseObject class that is implemented
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }



}
