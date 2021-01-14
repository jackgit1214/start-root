package com.example.consumer.demo;

public class UserPojo {
    private String userName;
    private String userSex;
    private int age;

    public UserPojo(String userName, String userSex, int age) {
        this.userName=userName;
        this.userSex=userSex;
        this.age=age;
    }

    public UserPojo() {
        super();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName=userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex=userSex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }
}
