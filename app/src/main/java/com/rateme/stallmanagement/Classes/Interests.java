package com.rateme.stallmanagement.Classes;

public class Interests {
    String uid, email;

    public Interests(String uid, String email) {
        this.uid = uid;
        this.email = email;
    }

    public Interests() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
