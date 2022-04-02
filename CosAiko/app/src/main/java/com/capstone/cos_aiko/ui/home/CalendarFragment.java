package com.capstone.cos_aiko.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.cos_aiko.PictureSelect;
import com.capstone.cos_aiko.R;
import com.capstone.cos_aiko.databinding.FragmentHomeBinding;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// The following imports are from ProfilePage.java

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

public class CalendarFragment extends Fragment {
    private FragmentHomeBinding binding;

    Button addPicturesButton, logoutBtn;
    private String FNAME, LNAME, NAME, EMAIL, BIO, PHONE;
    TextView mName, mEmail, mBio, mPhone;
    UserService userService;
    ImageView profileImg;

    //Input code
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private View root;

    // Creates and returns the view hierarchy associated with the fragment.
    //Called to have the fragment instantiate its user interface view.
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //CalendarViewModel calendarViewModel = new ViewModelProvider(this).get(CalendarViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        //final TextView textView = binding.textHome;
        //calendarViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    /*
        addPicturesButton = (Button) findViewById(R.id.add_pic);
        addPicturesButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent pictureSelectPage = new Intent(getApplicationContext(), PictureSelect.class);
            startActivity(pictureSelectPage);
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newact);
        CalendarView calendarView=(CalendarView) findViewById(R.id.calendarView1);
        calendarView.setOnDateChangeListener(new OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                Toast.makeText(getApplicationContext(), ""+dayOfMonth, 0).show();// TODO Auto-generated method stub

            }
        });*/
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}