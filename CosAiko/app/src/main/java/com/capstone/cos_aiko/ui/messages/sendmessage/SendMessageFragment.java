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
import com.capstone.cos_aiko.model.Message;
import com.capstone.cos_aiko.model.MessageResponse;
import com.capstone.cos_aiko.model.UserResponse;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.MessageService;
import com.capstone.cos_aiko.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
    private MessageService messageService;


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
        SharedPrefManager prefManager = new SharedPrefManager();
        currentEmail = prefManager.getEmail(getActivity().getApplicationContext());
        int currentId = prefManager.getId(getActivity().getApplicationContext());
        writeMessageBubble = currentView.findViewById(R.id.write_message);
        messageBubbleAdapter = new MessageBubbleAdapter(messageList, currentId);
        rvMessages.setAdapter(messageBubbleAdapter);
        messageService = ApiUtils.getMessageService();



        activity = this.getActivity();
        Call<List<MessageResponse>> prevMessages = messageService.getPreviousMessagesBetweenUsers(currentEmail, currRecipient);
        prevMessages.enqueue(new Callback<List<MessageResponse>>() {
            @Override
            public void onResponse(Call<List<MessageResponse>> call, Response<List<MessageResponse>> response) {
                if (response.isSuccessful()) {
                    // User data request successful
                    Log.d("getAllUsers", "Response success");
                    for (MessageResponse mess : response.body()) {
                        MessageBubble message = new MessageBubble(mess.getMessage(), mess.getSenderId(), mess.getReceiverId());
                        messageList.add(message);
                    }
//                    activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            for (MessageResponse mess : response.body()) {
//                                MessageBubble message = new MessageBubble(mess.getMessage(), mess.getSenderId(), mess.getReceiverId());
//                                messageList.add(message);
//                            }
//                        }
//                    });
                }
            }

            @Override
            public void onFailure(Call<List<MessageResponse>> call, Throwable t) {
                Log.d("sendMessage", "Unable to fetch previous messages");
            }
        });


        LinearLayoutManager linearMan = new LinearLayoutManager(getActivity());
        rvMessages.setLayoutManager(linearMan);
        messageBubbleAdapter.notifyDataSetChanged();
        createSocketConnection();
        // Inflate the layout for this fragment
        return root;
    }


    private void createSocketConnection(){
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://" + Constants.address + ":8080/chat");
        mStompClient.connect();



        Button sendButton = (Button) currentView.findViewById(R.id.send_button);
        sendButton.setVisibility(View.VISIBLE);
        sendButton.setBackgroundColor(Color.TRANSPARENT);

        mStompClient.topic("/topic/chatbroker").subscribe(topicMessage -> {
            String[] receivedMessageData = topicMessage.getPayload().split(":");

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                        if (currentEmail.equals(receivedMessageData[0]) && currRecipient.equals(receivedMessageData[1]) ){
                            messageList.add(new MessageBubble(receivedMessageData[2], 0,0));
                            messageBubbleAdapter.notifyDataSetChanged();
                        }
                        else if (currentEmail.equals(receivedMessageData[1]) && currRecipient.equals(receivedMessageData[0])) {
                        messageList.add(new MessageBubble(receivedMessageData[2], 0,0));
                        messageBubbleAdapter.notifyDataSetChanged();
                    }

                }
            });

            Log.d("mStompClient", "SendMessage mStompClient - " + topicMessage.getPayload());
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