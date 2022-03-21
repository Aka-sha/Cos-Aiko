package com.capstone.cos_aiko;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.capstone.cos_aiko.model.UserResponse;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.RetrofitClient;
import com.capstone.cos_aiko.remote.UserService;
import com.capstone.cos_aiko.storage.SharedPrefManager;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PictureSelect extends AppCompatActivity {
    private Button addImageButton, saveImgBtn;
    private FloatingActionButton fab;
    private ImageView firstImage;
    private ImageView secondImage;
    private ImageView thirdImage;
    private ImageView fourthImage;

    private ImageView xButtonOne;
    private ImageView xButtonTwo;
    private ImageView xButtonThree;
    private ImageView xButtonFour;

    private List<ImageView> imageList;
    private List<ImageView> xButtonList;

    List<PictureListContainer> listContainer;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_select);
        firstImage = (ImageView) findViewById(R.id.profile_image_1);
        secondImage = (ImageView) findViewById(R.id.profile_image_2);
        thirdImage = (ImageView) findViewById(R.id.profile_image_3);
        fourthImage = (ImageView) findViewById(R.id.profile_image_4);
        saveImgBtn = findViewById(R.id.saveImg);

        // initialize userService for API calls
        userService = ApiUtils.getUserService();

        imageList = new ArrayList<ImageView>();
        imageList.add(firstImage);
        imageList.add(secondImage);
        imageList.add(thirdImage);
        imageList.add(fourthImage);

        xButtonOne = (ImageView) findViewById(R.id.x_button_image1);
        xButtonTwo = (ImageView) findViewById(R.id.x_button_image2);
        xButtonThree = (ImageView) findViewById(R.id.x_button_image3);
        xButtonFour = (ImageView) findViewById(R.id.x_button_image4);

        xButtonList = new ArrayList<>();
        xButtonList.add(xButtonOne);
        xButtonList.add(xButtonTwo);
        xButtonList.add(xButtonThree);
        xButtonList.add(xButtonFour);

        listContainer = new ArrayList<>();
        for (int i = 0; i < imageList.size(); i++) {

            PictureListContainer listCont = new
                    PictureListContainer(imageList.get(i), xButtonList.get(i), i);
            listContainer.add(listCont);
        }

        for (int i = 0; i < xButtonList.size(); i++) {
            xButtonList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view = (ImageView) view;
                    int imagListIndex = Integer.parseInt(view.getTag().toString());
                    listContainer.get(imagListIndex).getImage().setVisibility(View.INVISIBLE);
                    listContainer.get(imagListIndex).getxButton().setVisibility(View.INVISIBLE);
                }
            });
        }
        fab = (FloatingActionButton) findViewById(R.id.add_pic_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(PictureSelect.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();

        for (PictureListContainer curr : listContainer) {
            if (curr.getImage().getVisibility() == View.INVISIBLE) {
                curr.getxButton().setVisibility(View.VISIBLE);
                curr.getImage().setVisibility(View.VISIBLE);
                curr.getImage().setImageURI(uri);
                break;
            }
        }

        // set listener for save image button
        saveImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get image file path
                File imageFile = new File(uri.getPath());
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
                MultipartBody.Part parts = MultipartBody.Part.createFormData("img", imageFile.getName(), requestBody);

                // get email key of current user
                SharedPrefManager pref = new SharedPrefManager();
                String email = pref.getEmail(getApplicationContext());

                // upload image to API
                Call<UserResponse> call = userService.updateImage(parts, email);
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) { // user profile image successfully updated
                            UserResponse user = response.body();
                            Toast.makeText(getApplicationContext(), "Profile successfully updated", Toast.LENGTH_SHORT).show();
                            // go to profile page to view changes
                            Intent profile = new Intent(getApplicationContext(), ProfilePage.class);
                            startActivity(profile);
                        } else { // unable to upload image
                            Toast.makeText(getApplicationContext(), "Unable to process request", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}