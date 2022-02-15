package com.auth.Authenticate.data;

public class UserDto {
    private String fName;
    private String lName;
    private String email;
    private String password;

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

}
