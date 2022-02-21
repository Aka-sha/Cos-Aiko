package com.capstone.cos_aiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.cos_aiko.model.User;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.UserService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText fname;
    EditText lname;
    String USERNAME, PASSWORD;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userService = ApiUtils.getUserService();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
    }
    public void registerButton(View view){
        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();
        String fnameText = fname.getText().toString();
        String lnameText = lname.getText().toString();

        User user = new User(1, fnameText, lnameText, usernameText, passwordText);
        register(user);
    }
    private void register(User user) {
        // Make API call with parameter email and password
        Call<ResponseBody> call = userService.createUser(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    // login successful
                    Toast.makeText(getApplicationContext(), "Hello Javatpoint", Toast.LENGTH_SHORT).show();
                    Intent tabPage = new Intent(getApplicationContext(), TabPage.class);
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
}