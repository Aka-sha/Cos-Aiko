package com.auth.Authenticate.data;

/**
 * The class is used as a Data Transfer Object (DTO) for user creation. This class has the
 * attributes that are required upon user registration (first name, last name, email, password)
 * This is used so that we can potentially avoid exposing sensitive information
 */

public class UserDto {
    private String fName;
    private String lName;
    private String email;
    private String password;
    private int id;

    public UserDto() {
        // empty constructor
    }


    ///// GETTERS \\\\\
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

    ///// SETTERS \\\\\
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

    public void setId(int id){   this.id = id; }
    public int  getId() {   return this.id;    }

}
