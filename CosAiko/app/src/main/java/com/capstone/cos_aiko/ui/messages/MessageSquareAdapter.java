package com.capstone.cos_aiko.ui.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.capstone.cos_aiko.R;

import java.util.List;

public class MessageSquareAdapter extends
        RecyclerView.Adapter<MessageSquareAdapter.ViewHolder> {
    private List<MessageSquare> mContacts;
    private OnSquareClickListener squareClickListener;

    public MessageSquareAdapter(List<MessageSquare> mContacts , OnSquareClickListener squareClickListener) {
        this.squareClickListener = squareClickListener;
        this.mContacts = mContacts;
    }

    @Override
    public MessageSquareAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.message_square_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView, squareClickListener);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MessageSquareAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        MessageSquare contact = mContacts.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(String.valueOf(contact.getUserId()));
        if (contact.getUserImage() != null) holder.profileImage.setImageBitmap(contact.getUserImage());
    }
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nameTextView;
        public Button messageButton;
        public ImageView profileImage;
        public OnSquareClickListener squareClickListener;

        public ViewHolder(View itemView, OnSquareClickListener squareClickListener) {
            super(itemView);

            profileImage = (ImageView) itemView.findViewById(R.id.message_profile_image);
            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);

            this.squareClickListener = squareClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            squareClickListener.onSquareClick(getAdapterPosition());
        }
    }

    public interface OnSquareClickListener {
        void onSquareClick(int position);
    }
}