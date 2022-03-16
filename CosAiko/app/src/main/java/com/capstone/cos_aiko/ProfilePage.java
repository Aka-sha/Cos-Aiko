package com.capstone.cos_aiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.cos_aiko.model.UserResponse;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.UserService;
import com.capstone.cos_aiko.storage.SharedPrefManager;
import com.github.dhaval2404.imagepicker.ImagePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePage extends AppCompatActivity {
    Button addPicturesButton, loginBtn;
    private String FNAME, LNAME, NAME, EMAIL, BIO, PHONE;
    TextView mName, mEmail, mBio, mPhone;
    UserService userService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        mName = findViewById(R.id.profile_name);
        mEmail = findViewById(R.id.profile_email);
        mPhone = findViewById(R.id.profile_phone);
        mBio = findViewById(R.id.profile_bio);
        userService = ApiUtils.getUserService();

        SharedPrefManager prefManager = new SharedPrefManager();
        EMAIL = prefManager.getEmail(getApplicationContext());

        getUserDetail();

        addPicturesButton = (Button) findViewById(R.id.add_pic);
        addPicturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pictureSelectPage = new Intent(getApplicationContext(), PictureSelect.class);
                startActivity(pictureSelectPage);
            }
        });

        loginBtn = findViewById(R.id.logout_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.endSession(getApplicationContext());
                String string = prefManager.getEmail(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                Intent login = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(login);
            }
        });

    }

    private void getUserDetail() {
        Call<UserResponse> call = userService.findByEmail(EMAIL);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // check if credentials matched (response code of 200 = success)
                if (response.isSuccessful()) {
                    UserResponse user = response.body();
                    FNAME = user.getFName();
                    LNAME = user.getLName();
                    NAME = FNAME + " " + LNAME;
                    EMAIL = user.getEmail();
                    PHONE = user.getPhone_number();
                    BIO = user.getBio();

                    mName.setText(NAME);
                    mEmail.setText(EMAIL);
                    mPhone.setText(PHONE);
                    mBio.setText(BIO);

                } else { // response code 404 (no matching credentials)
                    Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}