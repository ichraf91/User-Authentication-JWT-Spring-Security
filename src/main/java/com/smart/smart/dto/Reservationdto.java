package com.smart.smart.dto;


public class Reservationdto {
    private String username;
    private String email;
    private String eventTitle;

    // Constructeur, getters et setters

    public Reservationdto(String username, String email, String eventTitle) {
        this.username = username;
        this.email = email;
        this.eventTitle = eventTitle;
    }

    public Reservationdto(String username, String title) {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
}

