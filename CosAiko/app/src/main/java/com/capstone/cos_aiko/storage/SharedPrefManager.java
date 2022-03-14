package com.capstone.cos_aiko.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager { // manage user session using SharedPreferences
    // shared preference name
    private static final String PREF_NAME = "EMAIL_PREF";
    // store preference data
    private String KEY_EMAIL = "pref_email";

    // CONSTRUCTOR \\
    public SharedPrefManager() {

    }

    // save email to shared preferences
    public void saveEmail(Context context, String data) {
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString(KEY_EMAIL, data);
        editor.apply(); // save email to shared preferences
    }

    public String getEmail(Context context) {
        String data;
        SharedPreferences preferences;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        data = preferences.getString(KEY_EMAIL, ""); // defValue = "" --> return empty string if preference DNE
        return data;
    }

}
