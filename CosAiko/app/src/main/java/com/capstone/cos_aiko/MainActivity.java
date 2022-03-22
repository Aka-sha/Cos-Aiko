package com.capstone.cos_aiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.capstone.cos_aiko.model.UserResponse;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.UserService;
import com.capstone.cos_aiko.storage.SharedPrefManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    EditText username;
    EditText password;
    String USERNAME, PASSWORD;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        userService = ApiUtils.getUserService();

    }

    public void loginButton(View button) {
        USERNAME = username.getText().toString();
        PASSWORD = password.getText().toString();

        if (validateLogin(USERNAME, PASSWORD)) {
            // attempt login
            login(USERNAME, PASSWORD);
        }
    }

    /**
     * This function check whether or not the input on the login screen is empty
     *
     * @param username email field
     * @param password password field
     * @return true if either field is empty, false if both fields contain text
     */
    private boolean validateLogin(String username, String password) {
        // check if username field is empty
        if (username == null || username.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        // check if password field is empty
        if (password == null || password.trim().length() == 0) {
            Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * This function allows the user to attempt a login
     *
     * @param username given email
     * @param password given password
     */
    private void login(String username, String password) {
        // Make API call with parameter email and password
        Call<UserResponse> call = userService.login(username, password);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // check if credentials matched (response code of 200 = success)
                if (response.isSuccessful()) {
                    String email = response.body().getEmail();
                    // login successful
                    Toast.makeText(getApplicationContext(), "Hello Javatpoint", Toast.LENGTH_SHORT).show();
                    Intent tabPage = new Intent(getApplicationContext(), TabPage.class);

                    // save email to shared preferences to manage user session
                    SharedPrefManager prefManager = new SharedPrefManager();
                    prefManager.saveEmail(getApplicationContext(), email);

                    Log.d(TAG, "email and saved" + email);

                    startActivity(tabPage);
                } else { // response code 404 (no matching credentials)
                    Toast.makeText(getApplicationContext(), "The username or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void goToRegister(View v){
        Intent tabPage = new Intent(getApplicationContext(), Register.class);
        startActivity(tabPage);
    }
}