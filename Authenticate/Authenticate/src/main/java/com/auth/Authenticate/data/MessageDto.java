package com.auth.Authenticate.data;

public class MessageDto {

    private int mid;

    private int senderId;

    private int receiverId;

    private int time;

    private String message;

    public MessageDto(int mid, int senderId, int receiverId, int time, String message) {
        this.mid = mid;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.time = time;
        this.message = message;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
