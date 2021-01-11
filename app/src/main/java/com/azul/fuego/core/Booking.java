package com.azul.fuego.core;

import com.google.firebase.Timestamp;

import java.util.Map;

public class Booking {
    private String userID, booked_time;
    private Integer booked;
    private Timestamp bookDate;

    public Booking() {

    }

    public Booking(Map<String, Object> map) {
        userID = (String) map.get("userID");
        booked = Integer.parseInt(map.get("booked").toString());
        booked_time = (String) map.get("Time");
        bookDate = (Timestamp) map.get("date");
    }

    public Booking(String userID, String booked_time, Integer booked) {
        this.userID = userID;
        this.booked_time = booked_time;
        this.booked = booked;
        this.bookDate = Timestamp.now();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBooked_time() {
        return booked_time;
    }

    public void setBooked_time(String booked_time) {
        this.booked_time = booked_time;
    }

    public Integer getBooked() {
        return booked;
    }

    public void setBooked(Integer booked) {
        this.booked = booked;
    }

    public Timestamp getBookDate() {
        return bookDate;
    }

    public void setBookDate(Timestamp bookDate) {
        this.bookDate = bookDate;
    }
}
