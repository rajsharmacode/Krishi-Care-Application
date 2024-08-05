package com.example.project.Models;

public class ReadPracticesModel {
    String readdata,readimg,readname;

    public ReadPracticesModel() {
    }

    public ReadPracticesModel(String readdata, String readimg, String readname) {
        this.readdata = readdata;
        this.readimg = readimg;
        this.readname = readname;
    }

    public String getReaddata() {
        return readdata;
    }

    public void setReaddata(String readdata) {
        this.readdata = readdata;
    }

    public String getReadimg() {
        return readimg;
    }

    public void setReadimg(String readimg) {
        this.readimg = readimg;
    }

    public String getReadname() {
        return readname;
    }

    public void setReadname(String readname) {
        this.readname = readname;
    }
}
