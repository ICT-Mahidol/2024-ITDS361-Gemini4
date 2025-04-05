package com.gemini4.gemini4_backend.model;

import jakarta.persistence.Entity;

@Entity
public class Astronomer extends User {

    public Astronomer() {

    }

    public Astronomer(int id,String username, String password, String role){
        super(id, username, password, role);
    }


    @Override
    public String toString() {
        return "Astronomer{" + super.toString() + '\'' +
                "}";
    }
}
