package com.example.shubhangi.homepage;
import com.google.firebase.database.IgnoreExtraProperties;

public class UserPayData {

    public String tid;
    public String imgfile;

    // Default constructor reqaser.class)
    public UserPayData () {
    }

    public UserPayData (String tid, String imgfile) {
        this.tid=tid;
        this.imgfile=imgfile;
    }
}