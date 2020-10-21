package com.example.instafalm;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    //Italizes SDK for pasrse
    @Override
    public void onCreate() {
        super.onCreate();
        // Register your parse models
            //this is required for parse and can be found in the guide
        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("9lgVVh7LdwSd052xWrVH6RPxPUK8FflCnXB3J5mI")
                .clientKey("RI5hh3CPiiHvpPQxyVaB7feop0ID63bpEGtp7VXt")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
