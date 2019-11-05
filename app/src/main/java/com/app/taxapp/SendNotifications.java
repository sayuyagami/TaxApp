package com.app.taxapp;

public class SendNotifications {
    int comid;
    String message,category,prblm,status;

    public SendNotifications() {
    }

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*public String getComid() {
        return comid;
    }*/

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrblm() {
        return prblm;
    }

    public void setPrblm(String prblm) {
        this.prblm = prblm;
    }

    /*public void setComid(String comid) {
        this.comid = comid;
    }*/

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
