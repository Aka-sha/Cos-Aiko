package com.capstone.cos_aiko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.capstone.cos_aiko.databinding.ActivityTabPageBinding;

public class TabPage extends AppCompatActivity {

    private ActivityTabPageBinding binding;
    ImageView topNavBack;
    ImageView topNavProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTabPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        topNavBack = (ImageView) findViewById(R.id.topbar_back);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Back button in topbar returns to login page
        topNavBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginPage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(loginPage);
            }
        });
        // Profile button in topbar moves you to profile page
        topNavProfile = (ImageView) findViewById(R.id.topbar_profile);
        topNavProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profilePage = new Intent(getApplicationContext(), ProfilePage.class);
                startActivity(profilePage);
            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_tab_page);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}