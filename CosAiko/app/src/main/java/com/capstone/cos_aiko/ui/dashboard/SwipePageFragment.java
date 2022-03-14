package com.capstone.cos_aiko.ui.dashboard;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.capstone.cos_aiko.R;
import com.capstone.cos_aiko.TabPage;
import com.capstone.cos_aiko.databinding.FragmentDashboardBinding;
import com.capstone.cos_aiko.model.User;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.UserService;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SwipePageFragment extends Fragment {
    ArrayList<String> userNameList;
    ArrayAdapter arrayAdapter;
    int n=0;
    private FragmentDashboardBinding binding;
    private SwipeFlingAdapterView swipeFlingAdapterView;
    private UserService userService;
    private List<User> userDataList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SwipePageViewModel swipePageViewModel =
                new ViewModelProvider(this).get(SwipePageViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userService = ApiUtils.getUserService();
        userDataList = new ArrayList<>();
        final TextView textView = binding.textDashboard;
        swipePageViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        Call<List<User>> userData = userService.getAllUsers();

        userNameList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(root.getContext(), R.layout.item, R.id.helloText, userNameList);
        Activity activity = getActivity();
        Button leftButton = (Button) root.findViewById(R.id.left);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                swipeFlingAdapterView.getTopCardListener().selectLeft();
                Log.d("left button", "clicked");
            }
        });
        Button rightButton = (Button) root.findViewById(R.id.right);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                swipeFlingAdapterView.getTopCardListener().selectRight();
                Log.d("left button", "clicked");
            }
        });
        userData.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    // User data request successful
                    Log.d("getAllUsers" , "Response success");

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            userDataList.clear();
                            for (User user : response.body()){
                                userDataList.add(user);
                                userNameList.add(user.getfName());
                            }

                            arrayAdapter.notifyDataSetChanged();
                            createSwipeView();
                        }
                    });

                } else { // response code 404
                    Log.d("getAllUsers" , "Response failure");
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("getAllUsers" , "Response failure");
            }
        });
        createSwipeView();



        return root;
    }
    public void createSwipeView(){
        View root = binding.getRoot();
        swipeFlingAdapterView = (SwipeFlingAdapterView) root.findViewById(R.id.frame);


        swipeFlingAdapterView.setAdapter(arrayAdapter);

        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                userNameList.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}