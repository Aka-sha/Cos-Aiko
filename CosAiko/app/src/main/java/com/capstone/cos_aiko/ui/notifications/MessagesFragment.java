package com.capstone.cos_aiko.ui.notifications;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.cos_aiko.R;
import com.capstone.cos_aiko.databinding.FragmentNotificationsBinding;
import com.capstone.cos_aiko.model.UserResponse;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.UserService;
import com.capstone.cos_aiko.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesFragment extends Fragment {
    private UserService userService;
    private ArrayList<UserResponse> userDataList;
    private FragmentNotificationsBinding binding;
    private ArrayList<MessageSquare> messageList;
    private MessageSquareAdapter messageSquareAdapter;
    private Activity fragmentActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MessagesViewModel messagesViewModel =
                new ViewModelProvider(this).get(MessagesViewModel.class);
        fragmentActivity = this.getActivity();
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView rvMessages = (RecyclerView) root.findViewById(R.id.messagerecycler);
        messageList = new ArrayList<>();
        //messageList = MessageSquare.getMessageSquareList(15);

        messageSquareAdapter = new MessageSquareAdapter(messageList);
        rvMessages.setAdapter(messageSquareAdapter);

        rvMessages.setLayoutManager(new LinearLayoutManager(fragmentActivity));
        fetchUserDetails(root);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void fetchUserDetails(View root){
        // Make call for user data
        userService = ApiUtils.getUserService();
        SharedPrefManager prefManager = new SharedPrefManager();
        String email = prefManager.getEmail(getActivity().getApplicationContext());
        Call<List<UserResponse>> userData = userService.getFriends(email);
        userData.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                if (response.isSuccessful()) {
                    // User data request successful
                    Log.d("getAllUsers" , "Response success");

                    fragmentActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (UserResponse user : response.body()){
                                if (user.getImage() != null){
                                    messageList.add(new MessageSquare(user.getId(), false, user.getImage()));
                                }
                                messageSquareAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                } else { // response code 404
                    Log.d("getAllUsers" , "Response failure");
                }
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                Log.d("getAllUsers" , "Response failure");
            }
        });



    }
}