package com.example.dogfinder;

/**
 * Data-holding class containing contact info for a dog's owner
 */
public class ContactInfo {
    private String phone;
    private User user;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
