package com.auth.Authenticate.entity;

/**
 * This UserEntity class performs the Object Relational Mapping (ORM)
 * to the users table in our MySQL database.
 */

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "f_name")
    private String fName;

    @Column(name = "l_name")
    private String lName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone")
    private String phone;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_events", joinColumns = @JoinColumn(name = "uid"),
                inverseJoinColumns = @JoinColumn(name = "eid"))
    @Access(AccessType.FIELD)
    private List<EventEntity> events;

    @OneToMany(mappedBy = "requesterId", cascade = CascadeType.ALL)
    @Access(AccessType.FIELD)
    private List<Friendship> requesterId;

    @OneToMany(mappedBy = "receiverId", cascade = CascadeType.ALL)
    @Access(AccessType.FIELD)
    private List<Friendship> receiverId;

    //////// CONSTRUCTORS \\\\\\\\
    public UserEntity() {

    }

    /*
    public UserEntity(Integer id, String fName, String lName, String email, String password) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }
    */

    //////// GETTERS \\\\\\\\
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public String getPhone() {
        return phone;
    }

    public byte[] getImage() { return image; }

    public List<EventEntity> getEvents() { return events; }

    public List<Friendship> getRequesterId() { return requesterId; }

    public List<Friendship> getReceiverId() { return receiverId; }

    //////// SETTERS \\\\\\\\
    public void setId(Integer id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImage(byte[] image) { this.image = image; }

    public void setEvents(List<EventEntity> events) { this.events = events; }

    public void setRequesterId(List<Friendship> requesterId) { this.requesterId = requesterId; }

    public void setReceiverId(List<Friendship> receiverId) { this.receiverId = receiverId; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
