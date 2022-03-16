package com.capstone.cos_aiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class ProfilePage extends AppCompatActivity {
    Button addPicturesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        addPicturesButton = (Button) findViewById(R.id.add_pic);
        addPicturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pictureSelectPage = new Intent(getApplicationContext(), PictureSelect.class);
                startActivity(pictureSelectPage);
            }
        });
    }
}