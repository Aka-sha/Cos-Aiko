package com.capstone.cos_aiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    Button addPicturesButton, logoutBtn;
    private String FNAME, LNAME, NAME, EMAIL, BIO, PHONE;
    TextView mName, mEmail, mBio, mPhone;
    UserService userService;
    ImageView profileImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        mName = findViewById(R.id.profile_name);
        mEmail = findViewById(R.id.profile_email);
        mPhone = findViewById(R.id.profile_phone);
        mBio = findViewById(R.id.profile_bio);
        userService = ApiUtils.getUserService();
        profileImg = findViewById(R.id.profile_pic);

        // get email of current user for API call
        SharedPrefManager prefManager = new SharedPrefManager();
        EMAIL = prefManager.getEmail(getApplicationContext());

        // get user information to display on user profile
        getUserDetail();

        addPicturesButton = (Button) findViewById(R.id.add_pic);
        addPicturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pictureSelectPage = new Intent(getApplicationContext(), PictureSelect.class);
                startActivity(pictureSelectPage);
            }
        });

        // set listener for logout button
        logoutBtn = findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // call endSession function which clears preferences (logout)
                prefManager.endSession(getApplicationContext());
                String string = prefManager.getEmail(getApplicationContext());
                Toast.makeText(getApplicationContext(), "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                // redirect to login page
                Intent login = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(login);
            }
        });

    }

    /**
     * This function populates the user's profile with their information
     */
    private void getUserDetail() {
        // API call to get user by email key
        Call<UserResponse> call = userService.findByEmail(EMAIL);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) { // successfully obtained user information
                    UserResponse user = response.body();

                    // set fields for user profile
                    FNAME = user.getFName();
                    LNAME = user.getLName();
                    NAME = FNAME + " " + LNAME;
                    EMAIL = user.getEmail();
                    PHONE = user.getPhone_number();
                    BIO = user.getBio();

                    // inject fields onto profile page for viewing
                    mName.setText(NAME);
                    mEmail.setText(EMAIL);
                    mPhone.setText(PHONE);
                    mBio.setText(BIO);

                    // check if user has a profile picture - if not, profile picture will remain as default image
                    if (user.getImage() != null) {
                        // decode string
                        byte[] imageBytes = Base64.decode(user.getImage(), Base64.DEFAULT);
                        // create bitmap for image
                        Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        // set profile image
                        profileImg.setImageBitmap(bmp);
                    }

                } else { // unable to upload image
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