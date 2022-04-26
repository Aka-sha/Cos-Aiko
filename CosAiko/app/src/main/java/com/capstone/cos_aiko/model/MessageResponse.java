package com.capstone.cos_aiko.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenrator")
public class MessageResponse implements Serializable {

    @SerializedName("mid")
    private int mid;

    @SerializedName("senderId")
    private int senderId;

    @SerializedName("receiverId")
    private int receiverId;

    @SerializedName("time")
    private int time;

    @SerializedName("message")
    private String message;

    public MessageResponse(int mid, int senderId, int receiverId, int time, String message) {
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
