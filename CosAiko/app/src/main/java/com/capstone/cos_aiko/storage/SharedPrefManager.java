package com.capstone.cos_aiko.storage;

/*
    This class is used to handle the current users session on CosAiko
 */

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

    /**
     * save email to shared preferences
     *
     * @param context application context
     * @param data data being stored as SharedPreference
     */
    public void saveEmail(Context context, String data) {
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString(KEY_EMAIL, data);
        editor.apply(); // save email to shared preferences
    }

    /**
     * retrieve email value
     *
     * @param context application context
     * @return the email of the current user
     */
    public String getEmail(Context context) {
        String data;
        SharedPreferences preferences;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        data = preferences.getString(KEY_EMAIL, ""); // defValue = "" --> return empty string if preference DNE
        return data;
    }

    /**
     * clear preferences - logout current user
     *
     * @param context application context
     */
    public void endSession(Context context){
        SharedPreferences preferences;
        SharedPreferences.Editor editor;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

}
