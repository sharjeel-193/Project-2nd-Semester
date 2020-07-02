package com.selflearning.starcover.Logic;

public class UserProfile {
    private String userId;
    private String userName;
    private int userDpId;

    public UserProfile(String userId, String userName, int userDpId) {
        this.userId = userId;
        this.userName = userName;
        this.userDpId = userDpId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserDpId() {
        return userDpId;
    }
}
