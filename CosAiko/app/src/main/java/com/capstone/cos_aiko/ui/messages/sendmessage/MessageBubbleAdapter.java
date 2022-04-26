package com.capstone.cos_aiko.ui.messages.sendmessage;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.cos_aiko.R;
import com.capstone.cos_aiko.storage.SharedPrefManager;
import com.capstone.cos_aiko.ui.messages.MessageSquare;


import java.util.List;

public class MessageBubbleAdapter extends
        RecyclerView.Adapter<MessageBubbleAdapter.ViewHolder> {

    private List<MessageBubble> messageBubbleList;
    private SharedPrefManager prefManager;
    private int currId;

    public MessageBubbleAdapter(List<MessageBubble> messageBubbleList, int currId) {
        this.messageBubbleList = messageBubbleList;
        this.currId = currId;
        prefManager = new SharedPrefManager();
    }

    @NonNull
    @Override
    public MessageBubbleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.message_bubble_item, parent, false);

        // Return a new holder instance
        MessageBubbleAdapter.ViewHolder viewHolder = new MessageBubbleAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageBubbleAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        MessageBubble message = messageBubbleList.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(String.valueOf(message.getMessage()));
        if (message.getSender() == currId) {
            //textView.setBackgroundColor(Color.parseColor("#FFBB86FC"));
            textView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.sent_rounded_button));
            textView.setWidth(45);
        }
        else {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            textView.setLayoutParams(params);
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.message_bubble_tv);


        }


    }
    @Override
    public int getItemCount() {
        return messageBubbleList.size();
    }
}
