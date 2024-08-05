package com.example.project.Models;

public class CaltivationModel {

    String caltdisc,caltimg,caltname;

    public CaltivationModel() {
    }

    public CaltivationModel(String caltdisc, String caltimg, String caltname) {
        this.caltdisc = caltdisc;
        this.caltimg = caltimg;
        this.caltname = caltname;
    }

    public String getCaltdisc() {
        return caltdisc;
    }

    public void setCaltdisc(String caltdisc) {
        this.caltdisc = caltdisc;
    }

    public String getCaltimg() {
        return caltimg;
    }

    public void setCaltimg(String caltimg) {
        this.caltimg = caltimg;
    }

    public String getCaltname() {
        return caltname;
    }

    public void setCaltname(String caltname) {
        this.caltname = caltname;
    }
}
