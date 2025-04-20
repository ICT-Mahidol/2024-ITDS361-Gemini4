package com.gemini4.gemini4_backend.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import com.gemini4.gemini4_backend.model.*;
import com.gemini4.gemini4_backend.repository.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin
    @GetMapping("/")
    public @ResponseBody String welcome(){
        return "Hello, welcome to gemini 4 web application";
    }


    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")  // allow credentials for cookie
    @PostMapping("/login")
    public @ResponseBody String checkUser(@RequestBody Map<String, Object> body, HttpServletResponse response) {
        System.out.println(body);

        String username = body.get("username").toString();
        String password = body.get("password").toString();

        List<User> users = (List<User>) userRepository.findAll();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                String encryptedPassword = user.getPassword();
                if (passwordEncoder.matches(password, encryptedPassword)) {
                    // 🎯 Create a cookie
                    Cookie userName_ = new Cookie("user_name", user.getUsername());
                    userName_.setHttpOnly(true);
                    userName_.setSecure(false);
                    userName_.setPath("/");
                    userName_.setMaxAge(3600);
                    response.addCookie(userName_);

                    Cookie userRole = new Cookie("user_role", user.getRole());
                    userRole.setHttpOnly(false); // can be read by JS if needed
                    userRole.setSecure(false);
                    userRole.setPath("/");
                    userRole.setMaxAge(3600);
                    response.addCookie(userRole);

                    System.out.println("yeah yeah");
                    return "Login successfully";
                } else {
                    return "Wrong username or password";
                }
            }
        }

        return "Wrong username or password";
    }

    @CrossOrigin
    @PostMapping("/register")
    public @ResponseBody
    String addUser(@RequestBody Map<String,Object> body) {
        System.out.println(body);
        User user = UserFactory.createUser(body, userRepository, passwordEncoder);
        if(user == null){
            return "Error: wrong user role";
        }
        userRepository.save(user);
        return "Saved: " + user;

    }

    @CrossOrigin
    @GetMapping("/users")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns list of JSON objects of all the heroes
        return userRepository.findAll();
    }

}
