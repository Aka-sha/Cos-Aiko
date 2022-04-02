package com.capstone.cos_aiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.cos_aiko.model.User;
import com.capstone.cos_aiko.model.UserResponse;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.UserService;

import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    EditText username;
    EditText password, confirmPwd;
    EditText fname;
    EditText lname;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userService = ApiUtils.getUserService();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmPwd = (EditText) findViewById(R.id.confirmpassword);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
    }

    public void registerButton(View view) {
        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();
        String confirmPwdText = confirmPwd.getText().toString();
        String fnameText = fname.getText().toString();
        String lnameText = lname.getText().toString();

        // check fields before creating user
        if (validateSignUp(fnameText, lnameText, usernameText, passwordText, confirmPwdText)) {
            // capitalize the first letter of first and last name
            fnameText = fnameText.substring(0, 1).toUpperCase() + fnameText.substring(1);
            lnameText = lnameText.substring(0, 1).toUpperCase() + lnameText.substring(1);

            // attempt user registration
            User user = new User(fnameText, lnameText, usernameText, passwordText);
            register(user);
        }
    }

    /**
     * This function handles the asynchronous call to the REST Api to create a new user account
     *
     * @param user user attempting registration
     */
    private void register(User user) {
        // Make API call with parameter email and password
        Call<UserResponse> call = userService.createUser(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) { // user successfully registered
                    // login successful
                    Toast.makeText(getApplicationContext(), "Account successfully created. You may now login", Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(login);
                } else { // not successful because email already exists - 400 error code (BAD REQUEST)
                    Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This function checks whether or not all fields are empty
     *
     * @param fName given first name
     * @param lName given last name
     * @param username given email
     * @param password chosen password
     * @param confirmPwd
     * @return true if all field contain text, false otherwise
     */
    private boolean validateSignUp(String fName, String lName, String username, String password, String confirmPwd) {
        if (fName == null || fName.trim().length() == 0) { // validate first name
            Toast.makeText(getApplicationContext(), "First Name is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (lName == null || lName.trim().length() == 0) { // validate last name
            Toast.makeText(getApplicationContext(), "Last Name is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (username == null || username.trim().length() == 0) { // validate email
            Toast.makeText(getApplicationContext(), "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!checkValidFormat(username)) { // check email format
            Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password == null || password.trim().length() == 0) { // validate password
            Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.trim().length() < 8 || password.trim().length() > 32) { // force password length
            Toast.makeText(getApplicationContext(), "Password must be between 8 and 32 characters", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(confirmPwd)) {
            Toast.makeText(getApplicationContext(), "Password and Confirm Password MUST match!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    /**
     * This function makes sure that the given email is valid by
     * comparing it with a regular expression
     *
     * @param email email to check
     * @return false if email does not meet standards or if email is null, true otherwise
     */
    private boolean checkValidFormat(String email) { // check email format
        String emailRegex = "^(?=.{1,50}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,7})$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        // return true if email matches pattern - false otherwise
        return pattern.matcher(email.trim()).matches();
    }

}