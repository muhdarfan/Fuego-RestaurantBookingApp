package com.azul.fuego.core;

import android.net.Uri;

public class Restaurant {
    private String name, phone, address, about;
    private double rating;
    private Uri imgUri, website;

    public Restaurant(String name, String phone, String address, String about, double rating, Uri imgUri, Uri website) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.about = about;
        this.rating = rating;
        this.imgUri = imgUri;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Uri getImgUri() {
        return imgUri;
    }

    public void setImgUri(Uri imgUri) {
        this.imgUri = imgUri;
    }

    public Uri getWebsite() {
        return website;
    }

    public void setWebsite(Uri website) {
        this.website = website;
    }
}
