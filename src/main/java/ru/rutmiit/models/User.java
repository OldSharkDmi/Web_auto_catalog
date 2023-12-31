package ru.rutmiit.models;


import jakarta.persistence.*;
import ru.rutmiit.models.BaseEntity.BaseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User  extends BaseEntity {

    private UserRole role;
    private Set<Offer> offer;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate modified;
    private LocalDate created;
    private boolean isActive = true;
    private String imageUrl;
    private String email;

    public User() {
        offer = new HashSet<>();
    }

    public User(String userName, String password, String email, String firstName, String lastName)
    {
        this();

        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @PrePersist
    protected void onCreate() {
        created = LocalDate.now();
        modified = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modified = LocalDate.now();
    }

    @Override
    public LocalDate getCreated() {
        return created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }


    @Column(name = "email")
    public String getEmail(){return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    @ManyToOne
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Column(name = "username", nullable = false)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "is_active", nullable = false)
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Column(name = "image_url",  columnDefinition = "TEXT")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    public Set<Offer> getOffer() {
        return offer;
    }

    public void setOffer(Set<Offer> offer) {
        this.offer = offer;
    }

    public String toString() {
        return  userName;
    }



}
