package com.example.team12bof;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.widget.EditText;

import java.util.Optional;

/**
 * This class is for showing alert when an
 * input is invalid
 */
public class Utilities {


    /**
     * This method pops up a window
     * to show the alert message
     * @param activity
     * @param message
     */
    public static void showAlert(Activity activity,String message) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);

        alertBuilder
                .setTitle("Alert!")
                .setMessage(message)
                .setPositiveButton("Ok",(dialog, id) -> {
                    dialog.cancel();
                })
                .setCancelable(true);
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();

    }

    /**
     * This method is to parse integers to strings
     * @param str
     * @return
     */
    public static Optional<Integer> parseCount(String str) {
        try {
            int maxcount = Integer.parseInt(str);
            return Optional.of(maxcount);
        }
        catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /*public static void showAlert2(Activity activity,String message){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);


        alertBuilder

                .setMessage(message)
                .setPositiveButton("Yes",(dialog, id) -> {
                    dialog.cancel();
                    show3(activity);
                })
                .setNegativeButton("No",(dialog, id) ->{
                    dialog.cancel();
                })

                .setCancelable(true);

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    public static void show3(Activity activity){
       EditText input = new EditText(activity);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);

        alertBuilder
                .setTitle("Session name")
                .setMessage("Please enter the name of the session")
                .setView(input)
                .setPositiveButton("Save",(dialog, id) -> {
                    dialog.cancel();
                    String myuniq = input.getText().toString();


                })
         .setCancelable(true);


        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();

    }*/
}
