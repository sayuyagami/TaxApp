package com.app.taxapp;

public class SendNotifications {
    String message,comid,category,prblm;

    public SendNotifications() {
    }

    public String getComid() {
        return comid;
    }

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

    public void setComid(String comid) {
        this.comid = comid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}