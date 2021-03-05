package com.androidproject.Model;

public class User {
    private String user_id;
    private String name;
    private String contact_no;
    private String address;


    public User() {

    }

    public User(String user_id, String name, String contact_no, String address) {
        this.user_id = user_id;
        this.name = name;
        this.contact_no = contact_no;
        this.address = address;

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
