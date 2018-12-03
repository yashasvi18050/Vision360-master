package com.example.shubhangi.homepage;

public class Student {

    String name2;
    String gender2;
    String roomNo;
    String contactNo;
    String email;

   public Student(){
    }
    public Student(String name2,String gender2,String roomNo,String contactNo,String email){
        this.name2=name2;
        this.roomNo=roomNo;
        this.gender2=gender2;
        this.contactNo=contactNo;
        this.email=email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setEmail(String email){
       this.email=email;
    }

    public String getEmail(){
       return  email;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }


    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }


    public String getGender2() {
        return gender2;
    }

    public void setGender2(String gender2) {
        this.gender2 = gender2;
    }




}
