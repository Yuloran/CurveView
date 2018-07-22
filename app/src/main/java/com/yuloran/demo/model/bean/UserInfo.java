package com.yuloran.demo.model.bean;

/**
 * Author & Date: Yuloran, 2018/7/22 19:26
 * Function:
 */
public class UserInfo {
    private String userId;
    private String userName;

    public UserInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserInfo{" + "userId='" + userId + '\'' + ", userName='" + userName + '\'' + '}';
    }
}
