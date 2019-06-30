package com.app.taxapp;

import android.widget.Button;

public class Propertytaxdetails {
    String cn,address,ptino,locality,circle,taxdue,current,total;

    public Propertytaxdetails() {
    }

    public String getPtino() {
        return ptino;
    }

    public void setPtino(String ptino) {
        this.ptino = ptino;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    public String getTaxdue() {
        return taxdue;
    }

    public void setTaxdue(String taxdue) {
        this.taxdue = taxdue;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
