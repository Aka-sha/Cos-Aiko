package com.capstone.cos_aiko.ui.notifications;

import java.util.ArrayList;

public class MessageSquare {
    private int userId;
    private boolean firstMessageReceived;
    private byte[]  userImage;

    public MessageSquare(int userId, boolean firstMessageReceived, byte[] userImage) {
        this.userId = userId;
        this.firstMessageReceived = firstMessageReceived;
        this.userImage = userImage;
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

    public byte[] getUserImage() {
        return userImage;
    }

    public void setUserImage(byte[] userImage) {
        this.userImage = userImage;
    }

    public static ArrayList<MessageSquare> getMessageSquareList(int numSquares) {
        ArrayList<MessageSquare> messageSquares = new ArrayList<MessageSquare>();

        for (int i = 1; i <= numSquares; i++) {
            messageSquares.add(new MessageSquare(i, false, null));
        }

        return messageSquares;
    }
}
