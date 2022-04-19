package com.capstone.cos_aiko.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

//import com.bumptech.glide.Glide;
import com.capstone.cos_aiko.R;
import com.capstone.cos_aiko.TabPage;
import com.capstone.cos_aiko.databinding.FragmentDashboardBinding;
import com.capstone.cos_aiko.databinding.FragmentNotificationsBinding;
import com.capstone.cos_aiko.model.User;
import com.capstone.cos_aiko.model.UserResponse;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.UserService;
import com.capstone.cos_aiko.ui.dashboard.SwipePageViewModel;
import com.capstone.cos_aiko.ui.notifications.MessagesViewModel;
import com.capstone.cos_aiko.util.ImageCardAdapter;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

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

public class CalendarFragment extends Fragment {

    private FragmentHomeBinding binding;
    private CalendarView calendar;
    private TextView set_event;

    private Button event_button;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        calendar = (CalendarView) root.findViewById(R.id.calendar);
        set_event = (TextView) root.findViewById(R.id.set_event);
        CalendarViewModel calendarViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(
                    @NonNull CalendarView view,
                    int year,
                    int month,
                    int dayOfMonth)
            {

                String Date
                        = (month + 1) + "/"
                        + dayOfMonth + "/" + year;

                // set this date in TextView for Display
                set_event.setText(Date);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                //swipeFlingAdapterView.getTopCardListener().selectLeft();

                Log.d("calendar date", "changed");
            }
        });
/*
        event_button = (Button) root.findViewById(R.id.event_button);
        String start = "2022-04-1T01:00:00";
        String end = "2022-04-1T22:00:00";
        //startTime = "04/1T09:00:00/2022";
        //endTime = "04/1T12:00:00/2022";
        String date_format = DateFormat.getDateInstance().format(start);

        //String date_format = new SimpleDateFormat("MM-dd'T'HH:mm:ss-yyyy");
        //String start_date = SimpleDateFormat.parse(start);
        //String end_date = SimpleDateFormat.parse(end);

        event_button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent event_intent = new Intent(Intent.ACTION_EDIT);
                event_intent.setType("vnd.android.cursor.item/event");
                //event_intent.putExtra("beginTime", start_d.time);
                event_intent.putExtra("time", true);
                event_intent.putExtra("rule", "FREQ=YEARLY");
                event_intent.putExtra("title", "Event");
                startActivity(event_intent);
                //Log.d("calendar date", "changed");
            }
        });

 */
        /*
        For something happening when new date is selected
         */
        //calendarView.onSelectedDayChange(CalendarView view,
        //int year,
        //int month,
        //int dayOfMonth)*/

        /*
        For when event system binding is ready
         */
        //binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();
        //return root;

        //final TextView textView = binding.textNotifications;
        //messagesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}