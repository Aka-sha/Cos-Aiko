package com.capstone.cos_aiko.ui.messages.sendmessage;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.cos_aiko.R;
import com.capstone.cos_aiko.config.Constants;
import com.capstone.cos_aiko.model.UserResponse;
import com.capstone.cos_aiko.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

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
    private StompClient mStompClient;
    private View currentView;
    private EditText writeMessageBubble;
    private String currRecipient;
    private String currentEmail;
    private Activity activity;


    public SendMessageFragment() {
        // Required empty public constructor
    }
    public SendMessageFragment(String currRecipient){
        this.currRecipient = currRecipient;
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

        currentView =  root.getRootView();
        messageList = new ArrayList<>();
        writeMessageBubble = currentView.findViewById(R.id.write_message);
        messageBubbleAdapter = new MessageBubbleAdapter(messageList);
        rvMessages.setAdapter(messageBubbleAdapter);
        LinearLayoutManager linearMan = new LinearLayoutManager(getActivity());
        rvMessages.setLayoutManager(linearMan);
        activity = this.getActivity();
        messageList.add(new MessageBubble("Test1", 123, 123));
        messageList.add(new MessageBubble("Test2", 123, 123));
        messageBubbleAdapter.notifyDataSetChanged();
        createSocketConnection();
        // Inflate the layout for this fragment
        return root;
    }


    private void createSocketConnection(){
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://" + Constants.address + ":8080/chat");
        mStompClient.connect();
        SharedPrefManager prefManager = new SharedPrefManager();
        currentEmail = prefManager.getEmail(getActivity().getApplicationContext());


        Button sendButton = (Button) currentView.findViewById(R.id.send_button);
        sendButton.setVisibility(View.VISIBLE);
        sendButton.setBackgroundColor(Color.TRANSPARENT);

        mStompClient.topic("/topic/chatbroker").subscribe(topicMessage -> {
            String[] receivedMessageData = topicMessage.getPayload().split(":");

            if (!receivedMessageData[0].equals(currentEmail) ){
                messageList.add(new MessageBubble(receivedMessageData[2], 0,0));
                messageBubbleAdapter.notifyDataSetChanged();
            }
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageList.add(new MessageBubble(receivedMessageData[0], 0,0));
                    messageBubbleAdapter.notifyDataSetChanged();
                }
            });

            Log.d("Test", "TESTING THIS CALLBACK" + topicMessage.getPayload());
        }, err -> {
            Log.d("Error", "Error occured handling chatbroker message");
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textMessage = writeMessageBubble.getText().toString();
                mStompClient.send("/topic/chat.send",  currentEmail + ":" + currRecipient + ":" + textMessage).subscribe();
            }
        });
    }
}