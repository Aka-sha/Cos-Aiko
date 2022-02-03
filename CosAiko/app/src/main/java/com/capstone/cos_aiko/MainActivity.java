package com.capstone.cos_aiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

    }

    public void loginButton(View button){
        Toast.makeText(getApplicationContext(), "whatever",Toast.LENGTH_SHORT).show();
        if (username.getText().toString().equals("admin")  && password.getText().toString().equals("password") ){
            Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
            Intent swipePage = new Intent(getApplicationContext(), SwipeActivity.class);
            startActivity(swipePage);
        }
    }
}