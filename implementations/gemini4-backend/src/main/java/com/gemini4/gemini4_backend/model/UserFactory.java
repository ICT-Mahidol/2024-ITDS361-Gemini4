package com.gemini4.gemini4_backend.model;

import com.gemini4.gemini4_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

public class UserFactory {
    public static User createUser(Map<String, Object> body, UserRepository userRepository, PasswordEncoder passwordEncoder){
        String role = body.get("role").toString();

        if (role.equals("astronomer")) {
            Astronomer a = createNewAstronomer(body, passwordEncoder);
            userRepository.save(a);
            return a;
        } else if (role.equals("telescope_operator")) {
            TelescopeOperator t = createNewTelescopeOperatorWithId(body, passwordEncoder);
            userRepository.save(t);
            return t;
        } else if (role.equals("science_observer")) {
            ScienceObserver s = createNewScienceObserverWithId(body, passwordEncoder);
            userRepository.save(s);
            return s;
        } else if (role.equals("support")) {
            Support su = createNewSupportWithId(body, passwordEncoder);
            userRepository.save(su);
            return su;
        } else if (role.equals("administrator")) {
            Administrator ad = createNewAdministratorWithId(body, passwordEncoder);
            userRepository.save(ad);
            return ad;
        }
        return null;
    }

    private static Astronomer createNewAstronomer(Map<String, Object> body, PasswordEncoder encoder) {
        Astronomer a = new Astronomer();
        return setAstronomerInfo(a, body, encoder);
    }

    private static Astronomer setAstronomerInfo(Astronomer a, Map<String, Object> body, PasswordEncoder encoder) {
        a.setUsername(body.get("username").toString());
        a.setPassword(encoder.encode(body.get("password").toString()));
        a.setRole(body.get("role").toString());
        return a;
    }

    private static TelescopeOperator createNewTelescopeOperatorWithId(Map<String, Object> body, PasswordEncoder encoder) {
        TelescopeOperator t = new TelescopeOperator();
        return setTelescopeOperatorInfo(t, body, encoder);
    }

    private static TelescopeOperator setTelescopeOperatorInfo(TelescopeOperator t, Map<String,Object> body, PasswordEncoder encoder) {
        t.setUsername(body.get("username").toString());
        t.setPassword(encoder.encode(body.get("password").toString()));
        t.setRole(body.get("role").toString());
        return t;
    }

    private static ScienceObserver createNewScienceObserverWithId(Map<String, Object> body, PasswordEncoder encoder) {
        ScienceObserver s = new ScienceObserver();
        return setScienceObserverInfo(s, body, encoder);
    }

    private static ScienceObserver setScienceObserverInfo(ScienceObserver s, Map<String,Object> body, PasswordEncoder encoder) {
        s.setUsername(body.get("username").toString());
        s.setPassword(encoder.encode(body.get("password").toString()));
        s.setRole(body.get("role").toString());
        return s;
    }

    private static Support createNewSupportWithId(Map<String, Object> body, PasswordEncoder encoder) {
        Support su = new Support();
        return setSupportInfo(su, body, encoder);
    }

    private static Support setSupportInfo(Support su, Map<String,Object> body, PasswordEncoder encoder) {
        su.setUsername(body.get("username").toString());
        su.setPassword(encoder.encode(body.get("password").toString()));
        su.setRole(body.get("role").toString());
        return su;
    }

    private static Administrator createNewAdministratorWithId(Map<String, Object> body, PasswordEncoder encoder) {
        Administrator ad = new Administrator();
        return setAdministratorInfo(ad, body, encoder);
    }

    private static Administrator setAdministratorInfo(Administrator ad, Map<String,Object> body, PasswordEncoder encoder) {
        ad.setUsername(body.get("username").toString());
        ad.setPassword(encoder.encode(body.get("password").toString()));
        ad.setRole(body.get("role").toString());
        return ad;
    }
}
