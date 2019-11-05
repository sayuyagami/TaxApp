package com.app.taxapp;

public class Complaintdetails {
    int complaintid;
    String category,problem,mno,land,descp,addpicleft,addpic,addpicright;

    public Complaintdetails() {
    }

    public void setComplaintid(int complaintid) {
        this.complaintid = complaintid;
    }

    public Complaintdetails(String category, String problem, String mno, String land, String descp, String complaintid, String addpicleft, String addpic, String addpicright) {
        this.category = category;
        this.problem = problem;
        this.mno = mno;
        this.land = land;
        this.descp = descp;
        //this.complaintid = complaintid;
        this.addpicleft = addpicleft;
        this.addpic = addpic;
        this.addpicright = addpicright;
    }

    public String getAddpicleft() {
        return addpicleft;
    }

    public void setAddpicleft(String addpicleft) {
        this.addpicleft = addpicleft;
    }

    public String getAddpic() {
        return addpic;
    }

    public void setAddpic(String addpic) {
        this.addpic = addpic;
    }

    public String getAddpicright() {
        return addpicright;
    }

    public void setAddpicright(String addpicright) {
        this.addpicright = addpicright;
    }

    /*public String getComplaintid() {
        return complaintid;
    }

    public void setComplaintid(String complaintid) {
        this.complaintid = complaintid;
    }*/

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getMno() {
        return mno;
    }

    public void setMno(String mno) {
        this.mno = mno;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}
