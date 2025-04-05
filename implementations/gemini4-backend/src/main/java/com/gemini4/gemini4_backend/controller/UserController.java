package com.gemini4.gemini4_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import com.gemini4.gemini4_backend.model.*;
import com.gemini4.gemini4_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @CrossOrigin
    @RestController
    @RequestMapping("/auth")
    public class AuthController {

        @Autowired
        private UserRepository userRepository;
    }

    @CrossOrigin
    @PostMapping("/login")
    public @ResponseBody String checkUser(@RequestBody Map<String, Object> body) {
        System.out.println(body);

        String username = body.get("username").toString();
        String password = body.get("password").toString();

        // Retrieve all users from the database
        List<User> users = (List<User>) userRepository.findAll();

        // Loop through users and check username & password
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                String encryptedPassword = user.getPassword();
                if (passwordEncoder.matches(password, encryptedPassword)) {
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
//        var role = body.get("role").toString();

        if (body.get("role").toString().equals("astronomer")) {
            Astronomer a = createNewAstronomer(body);
            userRepository.save(a);
            return "Saved: " + a;
        } else if (body.get("role").toString().equals("telescope_operator")) {
            TelescopeOperator t = createNewTelescopeOperatorWithId(body);
            userRepository.save(t);
            return "Saved: " + t;
        } else if (body.get("role").toString().equals("science_observer")) {
            ScienceObserver s = createNewScienceObserverWithId(body);
            userRepository.save(s);
            return "Saved: " + s;
        } else if (body.get("role").toString().equals("support")) {
            Support su = createNewSupportWithId(body);
            userRepository.save(su);
            return "Saved: " + su;
        } else if (body.get("role").toString().equals("administrator")) {
            Administrator ad = createNewAdministratorWithId(body);
            userRepository.save(ad);
            return "Saved: " + ad;
        }
        return "Error: wrong user role";
    }

    @CrossOrigin
    @GetMapping("/users")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns list of JSON objects of all the heroes
        return userRepository.findAll();
    }


    // Astronomer
    private Astronomer createNewAstronomer(Map<String,Object> body) {
        // Create a new astronomer without setting the id manually
        Astronomer a = new Astronomer();
        return setAstronomerInfo(a, body);
    }

    private Astronomer setAstronomerInfo(Astronomer a, Map<String,Object> body) {
        a.setUsername(body.get("username").toString());
        a.setPassword(body.get("password").toString());
        a.setPassword(this.passwordEncoder.encode(body.get("password").toString()));
        a.setRole(body.get("role").toString());
        return a;
    }

    // Science Observer
    private ScienceObserver createNewScienceObserverWithId(Map<String,Object> body) {
        // create a new ScienceObserver with the given info
        ScienceObserver s = new ScienceObserver();
        return setScienceObserverInfo(s, body);
    }

    private ScienceObserver setScienceObserverInfo(ScienceObserver s, Map<String,Object> body) {
        s.setUsername(body.get("username").toString());
        s.setPassword(body.get("password").toString());
        s.setPassword(this.passwordEncoder.encode(body.get("password").toString()));
        s.setRole(body.get("role").toString());
        return s;
    }

    // Support
    private Support createNewSupportWithId(Map<String,Object> body) {
        // create a new Support with the given info
        Support su = new Support();
        return setSupportInfo(su, body);
    }

    private Support setSupportInfo(Support su, Map<String,Object> body) {
        su.setUsername(body.get("username").toString());
        su.setPassword(body.get("password").toString());
        su.setPassword(this.passwordEncoder.encode(body.get("password").toString()));
        su.setRole(body.get("role").toString());
        return su;
    }

    // TelescopeOperator
    private TelescopeOperator createNewTelescopeOperatorWithId(Map<String,Object> body) {
        // create a new TelescopeOperator with the given info
        TelescopeOperator t = new TelescopeOperator();
        return setTelescopeOperatorInfo(t, body);
    }

    private TelescopeOperator setTelescopeOperatorInfo(TelescopeOperator t, Map<String,Object> body) {
        t.setUsername(body.get("username").toString());
        t.setPassword(body.get("password").toString());
        t.setPassword(this.passwordEncoder.encode(body.get("password").toString()));
        t.setRole(body.get("role").toString());
        return t;
    }

    //Administrator
    private Administrator createNewAdministratorWithId(Map<String,Object> body) {
        // create a new Administrator with the given info
        Administrator ad = new Administrator();
        return setAdministratorInfo(ad, body);
    }

    private Administrator setAdministratorInfo(Administrator ad, Map<String,Object> body) {
        ad.setUsername(body.get("username").toString());
        ad.setPassword(body.get("password").toString());
        ad.setPassword(this.passwordEncoder.encode(body.get("password").toString()));
        ad.setRole(body.get("role").toString());
        return ad;
    }

}
