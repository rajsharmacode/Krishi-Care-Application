package com.example.project.Models;

public class JivModel {
    String jivdisc,jivimg,jivname;

    public JivModel() {
    }

    public JivModel(String jivdisc, String jivimg, String jivname) {
        this.jivdisc = jivdisc;
        this.jivimg = jivimg;
        this.jivname = jivname;
    }

    public String getJivdisc() {
        return jivdisc;
    }

    public void setJivdisc(String jivdisc) {
        this.jivdisc = jivdisc;
    }

    public String getJivimg() {
        return jivimg;
    }

    public void setJivimg(String jivimg) {
        this.jivimg = jivimg;
    }

    public String getJivname() {
        return jivname;
    }

    public void setJivname(String jivname) {
        this.jivname = jivname;
    }
}
