package ru.rutmiit.dto;

import java.util.List;

public  class UserProfileView {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<AddOfferDto> offers;
    public UserProfileView() {
    }

    public UserProfileView(String username, String email,String firstName,String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<AddOfferDto> getOffers() {
        return offers;
    }

    public void setOffers(List<AddOfferDto> offers) {
        this.offers = offers;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}