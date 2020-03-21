package com.example.dogfinder;

import java.util.ArrayList;
import java.util.List;

/**
 * A data-holding class representing a dog
 */
public class DogProfile {
    private User user;
    private ContactInfo contact;
    private Picture picture;
    private String name;
    private Sex sex = Sex.Male;
    private Breed breed = Breed.Unknown;
    private Color color = Color.Unknown;
    private String height;
    private String weight;
    private String other;
    private Location location;
    private List<String> comments = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ContactInfo getContact() {
        return contact;
    }

    public void setContact(ContactInfo contact) {
        this.contact = contact;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
