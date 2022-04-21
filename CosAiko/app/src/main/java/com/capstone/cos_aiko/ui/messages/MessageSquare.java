package com.capstone.cos_aiko.ui.messages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.ArrayList;

public class MessageSquare {
    private String userId;
    private boolean firstMessageReceived;
    private String  userImage;
    private Bitmap bmpImage;

    public MessageSquare(String userId, boolean firstMessageReceived, String userImage) {
        this.userId = userId;
        this.firstMessageReceived = firstMessageReceived;
        this.userImage = userImage;

        if (userImage != null){
            byte[] imageBytes = Base64.decode(userImage, Base64.DEFAULT);
            // create bitmap for image
            this.bmpImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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



}
