package com.capstone.cos_aiko.ui.messages;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.cos_aiko.R;
import com.capstone.cos_aiko.databinding.FragmentNotificationsBinding;
import com.capstone.cos_aiko.ui.messages.sendmessage.SendMessageFragment;
import com.capstone.cos_aiko.model.UserResponse;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.UserService;

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

        messageSquareAdapter = new MessageSquareAdapter(messageList, new MessageSquareAdapter.OnSquareClickListener() {
            @Override
            public void onSquareClick(int position) {
                Log.d("Interface", "Message_button clicked");
                replaceFragment(new SendMessageFragment());
            }
        });
        rvMessages.setAdapter(messageSquareAdapter);

        rvMessages.setLayoutManager(new LinearLayoutManager(fragmentActivity));
        fetchUserDetails(root);
        //Button messageButton = fragmentActivity.findViewById(R.id.message_button);
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
        Call<List<UserResponse>> userData = userService.getAllUsersReponse();
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

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_tab_page,fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


}