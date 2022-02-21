package com.capstone.cos_aiko.model;

public class User {

    private Integer id;

    private String fName;

    private String lName;

    private String email;

    private String password;

    //////// CONSTRUCTORS \\\\\\\\
    public User() {

    }

    public User(Integer id, String fName, String lName, String email, String password) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }

    public User(String fName, String lName, String email, String password) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
    }

    //////// GETTERS \\\\\\\\
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

    //////// GETTERS \\\\\\\\
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
