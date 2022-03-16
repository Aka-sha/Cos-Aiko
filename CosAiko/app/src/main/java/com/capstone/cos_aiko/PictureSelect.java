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

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PictureSelect extends AppCompatActivity {
    private Button addImageButton;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_select);
        firstImage = (ImageView) findViewById(R.id.profile_image_1);
        secondImage = (ImageView) findViewById(R.id.profile_image_2);
        thirdImage = (ImageView) findViewById(R.id.profile_image_3);
        fourthImage = (ImageView) findViewById(R.id.profile_image_4);

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
        for (int i =0; i < imageList.size(); i++){

            PictureListContainer listCont = new
                    PictureListContainer(imageList.get(i), xButtonList.get(i), i);
            listContainer.add(listCont);
        }

        for (int i=0; i < xButtonList.size(); i++){
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

        for ( PictureListContainer curr : listContainer ){
            if (curr.getImage().getVisibility() == View.INVISIBLE){
                curr.getxButton().setVisibility(View.VISIBLE);
                curr.getImage().setVisibility(View.VISIBLE);
                curr.getImage().setImageURI(uri);
                break;
            }
        }

    }
}