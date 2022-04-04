package com.capstone.cos_aiko.ui.home;

import android.app.Activity;
import android.content.Intent;
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

public class CalendarFragment extends Fragment {
    private FragmentDashboardBinding binding;
    private CalendarView calendar;
    private TextView set_event;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
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
                        = dayOfMonth + "-"
                        + (month + 1) + "-" + year;

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
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }
}