package com.capstone.cos_aiko.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

//import com.bumptech.glide.Glide;
import com.capstone.cos_aiko.R;
import com.capstone.cos_aiko.databinding.FragmentHomeBinding;

//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

// The following imports are from ProfilePage.java


public class CalendarFragment extends Fragment {

    private FragmentHomeBinding binding;
    private CalendarView calendar;
    private TextView set_event;

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
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}