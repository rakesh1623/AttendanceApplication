package com.example.connect_db;

public class reghelper {
    String uname , usec , uroll , uenroll , uyear , upass , uemail;

    public reghelper() {
    }

    public reghelper(String uname, String usec, String uroll, String uenroll, String uyear, String upass , String uemail) {
        this.uname = uname;
        this.usec = usec;
        this.uroll = uroll;
        this.uenroll = uenroll;
        this.uyear = uyear;
        this.upass = upass;
        this.uemail = uemail;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUsec() {
        return usec;
    }

    public void setUsec(String usec) {
        this.usec = usec;
    }

    public String getUroll() {
        return uroll;
    }

    public void setUroll(String uroll) {
        this.uroll = uroll;
    }

    public String getUenroll() {
        return uenroll;
    }

    public void setUenroll(String uenroll) {
        this.uenroll = uenroll;
    }

    public String getUyear() {
        return uyear;
    }

    public void setUyear(String uyear) {
        this.uyear = uyear;
    }

    public String getUpass() {
        return upass;
    }


    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUemail(){
        return uemail;
    }
    public void setUemail(String uname){
        this.uemail = uemail;

    }
}
