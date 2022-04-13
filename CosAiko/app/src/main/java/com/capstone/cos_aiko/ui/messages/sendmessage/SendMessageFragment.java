package com.capstone.cos_aiko.ui.messages.sendmessage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.capstone.cos_aiko.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SendMessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SendMessageFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<MessageBubble> messageList;
    private MessageBubbleAdapter messageBubbleAdapter;
    private String mParam1;
    private String mParam2;

    public SendMessageFragment() {
        // Required empty public constructor
    }


    public static SendMessageFragment newInstance(String param1, String param2) {
        SendMessageFragment fragment = new SendMessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_send_message, container, false);
        RecyclerView rvMessages = (RecyclerView) root.findViewById(R.id.messagebubblerecycler);


        messageList = new ArrayList<>();

        messageBubbleAdapter = new MessageBubbleAdapter(messageList);
        rvMessages.setAdapter(messageBubbleAdapter);
        LinearLayoutManager linearMan = new LinearLayoutManager(getActivity());
        rvMessages.setLayoutManager(linearMan);

        messageList.add(new MessageBubble("Test1", 123, 123));
        messageList.add(new MessageBubble("Test2", 123, 123));
        messageBubbleAdapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        return root;
    }
}