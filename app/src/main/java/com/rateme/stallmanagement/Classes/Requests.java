package com.rateme.stallmanagement.Classes;

public class Requests {
    String landno, owner, type, size, rent, imageurl, uid, requests;

    public Requests(String landno, String owner, String type, String size, String rent, String imageurl, String uid, String requests) {
        this.landno = landno;
        this.owner = owner;
        this.type = type;
        this.size = size;
        this.rent = rent;
        this.imageurl = imageurl;
        this.uid = uid;
        this.requests = requests;
    }

    public Requests() {
    }

    public String getLandno() {
        return landno;
    }

    public void setLandno(String landno) {
        this.landno = landno;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRequests() {
        return requests;
    }

    public void setRequests(String requests) {
        this.requests = requests;
    }
}
