package com.auth.Authenticate.data;

/**
 * The class is used as a Data Transfer Object (DTO) for user the user profile. This class has the
 * attributes that are required for the user profile (name, email, bio, phone number, image)
 */

public class UserProfileDto {
    private String fName;
    private String lName;
    private String email;
    private String bio;
    private String phone;
    private byte[] image;

    public UserProfileDto() {

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

    public String getBio() {
        return bio;
    }

    public String getPhone() {
        return phone;
    }

    public byte[] getImage() {
        return image;
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

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        else if(!(o instanceof UserProfileDto)){
            return false;
        }
        else{
            return ((UserProfileDto) o).getEmail().equals(this.email);
        }
    }
}
