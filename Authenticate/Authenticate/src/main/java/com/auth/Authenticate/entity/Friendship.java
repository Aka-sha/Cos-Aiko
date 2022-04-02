package com.auth.Authenticate.entity;

/**
 * This Friendship class performs the Object Relational Mapping (ORM)
 * between two user that form a friendship on the CosAiko application
 */

import javax.persistence.*;

@Entity
@Table(name = "friends")
public class Friendship {
    @Column(name = "fid", nullable = false, unique = true)
    private Integer fid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id")
    @Access(AccessType.FIELD)
    UserEntity requesterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    @Access(AccessType.FIELD)
    UserEntity receiverId;

    /// CONSTRUCTOR \\\
    public Friendship() {

    }

    //////// GETTERS \\\\\\\\
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getFid(){
        return fid;
    }

    public UserEntity getRequesterId(){
        return requesterId;
    }

    public UserEntity getReceiverId(){
        return receiverId;
    }

    //////// SETTERS \\\\\\\\
    public void setFid(Integer fid){
        this.fid = fid;
    }

    public void setRequesterId(UserEntity requesterId){
        this.requesterId = requesterId;
    }

    public void setReceiverId(UserEntity receiverId){
        this.receiverId = receiverId;
    }
}
