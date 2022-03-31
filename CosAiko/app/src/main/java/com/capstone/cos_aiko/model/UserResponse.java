package com.capstone.cos_aiko.model;

/**
 * This class maps to the UserEntity in the REST Api. This class
 * converts the JSON returned from the Api into a Java Object.
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenrator")
public class UserResponse implements Serializable { // response class for user registration

    @SerializedName("lName")
    private String lName;

    @SerializedName("password")
    private String password;

    @SerializedName("fName")
    private String fName;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("bio")
    private String bio;

    @SerializedName("phone")
    private String phone_number;

    @SerializedName("image")
    private String image;

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getLName() {
        return lName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getFName() {
        return fName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setPhone(String phone) {
        this.phone_number = phone;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bio='" + bio + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }

}