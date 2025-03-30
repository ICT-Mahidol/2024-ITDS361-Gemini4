package com.gemini4.gemini4_backend.model;

import jakarta.persistence.Entity;

@Entity
public class Administrator extends User{
    public Administrator(int id, String username, String password, String role){
        super(id, username, password, role);
    }

    public Administrator() {
        super();
    }

    @Override
    public String toString() {
        return "Administrator{" + super.toString() +
                '}';
    }
}
