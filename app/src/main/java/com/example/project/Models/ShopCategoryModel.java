package com.example.project.Models;

public class ShopCategoryModel {

    String catimage,catname,userkey;

    public ShopCategoryModel() {
    }

    public ShopCategoryModel(String catimage, String catname, String userkey) {
        this.catimage = catimage;
        this.catname = catname;
        this.userkey = userkey;
    }

    public String getCatimage() {
        return catimage;
    }

    public void setCatimage(String catimage) {
        this.catimage = catimage;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }
}
