package com.example.myadmin;


public class UserProfile {

    String userImage;
    String userName;
    String userEmail;
    String userCarNumber;
    String userCarModel;
    String UserId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public UserProfile() {
    }

    public UserProfile(String userName, String userEmail, String userCarNumber, String userCarModel,String UserId) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userCarNumber = userCarNumber;
        this.userCarModel = userCarModel;
        this.UserId =UserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCarNumber() {
        return userCarNumber;
    }

    public void setUserCarNumber(String userCarNumber) {
        this.userCarNumber = userCarNumber;
    }

    public String getUserCarModel() {
        return userCarModel;
    }

    public void setUserCarModel(String userCarModel) {
        this.userCarModel = userCarModel;
    }
}