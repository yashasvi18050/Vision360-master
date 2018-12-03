package com.example.shubhangi.homepage;

public class complaint
{
    String userid=null;
    String service_person=null;
    String service_reqd=null;
    String date1=null;
    String time1=null;
    String status=null;
    String employee_name=null;

    public complaint(){
    }
    public complaint(String userid,String service_person,String service_reqd,String date1,String time1,String status,String employee_name){
        this.userid=userid;
        this.date1=date1;
        this.employee_name=employee_name;
        this.service_person=service_person;
        this.service_reqd=service_reqd;
        this.time1=time1;
        this.status=status;
    }


    public String getUserid() {
        return userid;
    }

    public String getService_person() {
        return service_person;
    }

    public String getService_reqd() {
        return service_reqd;
    }

    public String getDate1() {
        return date1;
    }

    public String getTime1() {
        return time1;
    }

    public String getStatus() {
        return status;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setService_person(String service_person) {
        this.service_person = service_person;
    }

    public void setService_reqd(String service_reqd) {
        this.service_reqd = service_reqd;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
}
