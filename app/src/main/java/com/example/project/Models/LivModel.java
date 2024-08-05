package com.example.project.Models;

public class LivModel {
    String livdisc,livimg,livname;

    public LivModel() {
    }

    public LivModel(String livdisc, String livimg, String livname) {
        this.livdisc = livdisc;
        this.livimg = livimg;
        this.livname = livname;
    }

    public String getLivdisc() {
        return livdisc;
    }

    public void setLivdisc(String livdisc) {
        this.livdisc = livdisc;
    }

    public String getLivimg() {
        return livimg;
    }

    public void setLivimg(String livimg) {
        this.livimg = livimg;
    }

    public String getLivname() {
        return livname;
    }

    public void setLivname(String livname) {
        this.livname = livname;
    }
}
