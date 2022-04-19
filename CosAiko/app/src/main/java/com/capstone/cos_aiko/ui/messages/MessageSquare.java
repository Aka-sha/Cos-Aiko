package com.capstone.cos_aiko.ui.messages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.ArrayList;

public class MessageSquare {
    private int userId;
    private boolean firstMessageReceived;
    private String  userImage;
    private Bitmap bmpImage;

    public MessageSquare(int userId, boolean firstMessageReceived, String userImage) {
        this.userId = userId;
        this.firstMessageReceived = firstMessageReceived;
        this.userImage = userImage;

        byte[] imageBytes = Base64.decode(userImage, Base64.DEFAULT);
        // create bitmap for image
        this.bmpImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isFirstMessageReceived() {
        return firstMessageReceived;
    }

    public void setFirstMessageReceived(boolean firstMessageReceived) {
        this.firstMessageReceived = firstMessageReceived;
    }

    public Bitmap getUserImage() {
        return this.bmpImage;
    }


    public static ArrayList<MessageSquare> getMessageSquareList(int numSquares) {
        ArrayList<MessageSquare> messageSquares = new ArrayList<MessageSquare>();

        for (int i = 1; i <= numSquares; i++) {
            messageSquares.add(new MessageSquare(i, false, null));
        }

        return messageSquares;
    }
}
