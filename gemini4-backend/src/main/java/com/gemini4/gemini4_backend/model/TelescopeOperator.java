package com.gemini4.gemini4_backend.model;

import jakarta.persistence.Entity;

@Entity
public class TelescopeOperator extends User{
    public TelescopeOperator(int id, String username, String password, String role){
        super(id, username, password, role);
    }

    public TelescopeOperator() {
        super();
    }

    @Override
    public String toString() {
        return "TelescopeOperator{" + super.toString() +
                '}';
    }
}
