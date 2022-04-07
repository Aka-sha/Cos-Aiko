package com.capstone.cos_aiko.ui.notifications;

import android.os.Bundle;
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

import java.util.ArrayList;

public class MessagesFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private ArrayList<MessageSquare> messageList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MessagesViewModel messagesViewModel =
                new ViewModelProvider(this).get(MessagesViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView rvMessages = (RecyclerView) root.findViewById(R.id.messagerecycler);
        messageList = MessageSquare.getMessageSquareList(15);

        MessageSquareAdapter messageSquareAdapter = new MessageSquareAdapter(messageList);
        rvMessages.setAdapter(messageSquareAdapter);

        rvMessages.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}