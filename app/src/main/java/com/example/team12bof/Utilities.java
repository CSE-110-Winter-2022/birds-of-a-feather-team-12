package com.example.team12bof;

import android.app.Activity;
import android.app.AlertDialog;

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
}
