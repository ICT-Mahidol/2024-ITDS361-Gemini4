package com.gemini4.gemini4_backend.model;

import jakarta.persistence.Entity;

@Entity
public class ScienceObserver extends User{
    public ScienceObserver(int id, String username, String password, String role){
        super(id, username, password, role);
    }

    public ScienceObserver() {
        super();
    }

    @Override
    public String toString() {
        return "ScienceObserver{" + super.toString() +
                '}';
    }
}
