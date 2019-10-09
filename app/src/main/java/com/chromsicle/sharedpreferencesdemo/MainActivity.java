package com.chromsicle.sharedpreferencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

//SharedPreferences are used for saving small amounts of data such as:
//the background color the user chose
//the date format the user chose
//stuff like Strings, arrays, ints, doubles, basic types of information
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.chromsicle.sharedpreferencesdemo", Context.MODE_PRIVATE);

        //put info into the SharedPreferences
        //after running once, this line can be commented out and the username will still appear because it was saved
        sharedPreferences.edit().putString("username", "Sherry").apply();

        //get info back out of the SharedPreference
        //provide a default string for when nothing is found under "username"
        String username = sharedPreferences.getString("username", "");

        Log.i("this is the usermane", username);

        //you can also save an array of stuff!
        ArrayList<String> hobbies = new ArrayList<>();
        hobbies.add("LEGO");
        hobbies.add("origami");
        hobbies.add("geocaching");
        hobbies.add("Pokemon GO");

        //serialize the arraylist with the ObjectSerializer class and turn it into a string
        try {
            sharedPreferences.edit().putString("hobbies", ObjectSerializer.serialize(hobbies)).apply();
            //this log will look crazy but Java knows what it is and it will deserialize to stuff we can read
            Log.i("hobbies", ObjectSerializer.serialize(hobbies));

        } catch (Exception e) {
            e.printStackTrace();
        }

        //take the hobbies back out
        ArrayList<String> newHobbies = new ArrayList<>();
        //the default value needs to be a new serialized empty ArrayList, essentially gives back an empty string
        try {
            newHobbies = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("hobbies", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("new hobbies", newHobbies.toString());


    }
}
