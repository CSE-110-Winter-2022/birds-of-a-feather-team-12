package com.example.team12bof;

import android.app.Activity;
import android.app.AlertDialog;

import java.util.Optional;

public class Utilities {
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
