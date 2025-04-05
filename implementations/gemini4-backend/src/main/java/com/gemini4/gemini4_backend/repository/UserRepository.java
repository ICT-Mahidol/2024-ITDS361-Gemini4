package com.gemini4.gemini4_backend.repository;

import com.gemini4.gemini4_backend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
