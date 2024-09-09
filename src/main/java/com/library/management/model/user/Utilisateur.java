package com.library.management.model.user;

public abstract class Utilisateur {
    private int id; // Unique identifier for the user
    private String name;
    private String email;

    // Constructor
    public Utilisateur(int id, String name,String email) {
        this.id = id;
        this.name = name;
        this.email=email;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    // Abstract methods
    public abstract void displayInfo();
}
