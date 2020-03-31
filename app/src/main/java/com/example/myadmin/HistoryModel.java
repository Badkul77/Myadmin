package com.example.myadmin;

public class HistoryModel {
    String Details,Issue,status,id,Userid;



    public HistoryModel(String details, String issue, String status, String id,String userid) {
        Details = details;
        Issue = issue;
        this.status = status;
       this.id=id;
       this.Userid=userid;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getIssue() {
        return Issue;
    }

    public void setIssue(String issue) {
        Issue = issue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
