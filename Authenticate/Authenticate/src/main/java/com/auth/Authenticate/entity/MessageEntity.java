package com.auth.Authenticate.entity;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class MessageEntity  {
    @Id
    @Column(name = "mid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    @Access(AccessType.FIELD)
    UserEntity senderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id")
    @Access(AccessType.FIELD)
    UserEntity receiverId;

    @Column(name = "message")
    private String message;


    @Column(name = "time")
    private int time;

    public MessageEntity(UserEntity requesterId, UserEntity receiverId, String message, int time) {
        this.mid = mid;
        this.senderId = requesterId;
        this.receiverId = receiverId;
        this.message = message;
        this.time = time;
    }

    public MessageEntity() {

    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public UserEntity getSenderId() {
        return senderId;
    }

    public void setSenderId(UserEntity senderId) {
        this.senderId = senderId;
    }

    public UserEntity getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UserEntity receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


}
