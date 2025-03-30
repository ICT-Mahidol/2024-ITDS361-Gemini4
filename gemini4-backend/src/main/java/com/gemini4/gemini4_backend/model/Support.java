package com.gemini4.gemini4_backend.model;

import jakarta.persistence.Entity;

@Entity
public class Support extends User{
    public Support(int id, String username, String password, String role){
        super(id, username, password, role);
    }

    public Support() {
        super();
    }

    @Override
    public String toString() {
        return "Support{" + super.toString() +
                '}';
    }
}
