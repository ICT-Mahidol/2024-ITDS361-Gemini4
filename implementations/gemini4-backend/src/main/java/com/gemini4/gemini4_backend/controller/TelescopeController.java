package com.gemini4.gemini4_backend.controller;

import com.gemini4.gemini4_backend.model.Astronomer;
import com.gemini4.gemini4_backend.model.Telescope;
import com.gemini4.gemini4_backend.model.User;
import com.gemini4.gemini4_backend.model.UserFactory;
import com.gemini4.gemini4_backend.repository.TelescopeRepository;
import com.gemini4.gemini4_backend.repository.UserRepository;
import edu.gemini.app.ocs.model.VirtualTelescope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Optional;

import static org.hibernate.type.descriptor.java.JdbcDateJavaType.DATE_FORMAT;

@Controller
public class TelescopeController {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    @Autowired
    private TelescopeRepository telescopeRepository;


    @CrossOrigin
    @PostMapping("/addtelescope")
    public @ResponseBody String addTelescope(@RequestBody(required = false) Map<String, Object> body) {
        try {
            Telescope tele = (body == null || body.isEmpty())
                    ? new Telescope() : createTelescope(body, telescopeRepository);

            telescopeRepository.save(tele);
            return "Saved: " + tele;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    //  Execute command line
    @PostMapping("/execute")
    public ResponseEntity<String> executeCommand(@RequestBody Map<String, String> request) {
        String commandStr = request.get("command");
        String idStr = request.get("id");

        if (commandStr == null || commandStr.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Command cannot be empty.");
        }

        if (idStr == null || idStr.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Telescope ID is required.");
        }

        try {
            int id = Integer.parseInt(idStr);
            String trimmedCommand = commandStr.trim();

            Optional<Telescope> optionalTele = telescopeRepository.findById(id);
            if (optionalTele.isEmpty()) {
                return ResponseEntity.badRequest().body("Telescope with ID " + id + " not found.");
            }

            Telescope tele = optionalTele.get();

            try {
                VirtualTelescope.COMMAND enumCommand = VirtualTelescope.COMMAND.valueOf(trimmedCommand.toUpperCase());
                String result = tele.executeCommand(enumCommand);
                telescopeRepository.save(tele); // Save updated state
                return ResponseEntity.ok(result);
            } catch (IllegalArgumentException e) {
                // Not an enum command => try as string command
                String result = tele.executeCommand(trimmedCommand);
                return ResponseEntity.ok(result);
            }

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID format.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }




    @CrossOrigin
    @GetMapping("/telescopes")
    public @ResponseBody
    Iterable<Telescope> getAllTelescopes() {
        // This returns list of JSON objects of all the telescopes
        return telescopeRepository.findAll();
    }

    public static Telescope createTelescope(Map<String, Object> body, TelescopeRepository telescopeRepository) throws ParseException {
        Telescope ts = createNewTelescope(body);
        telescopeRepository.save(ts);
        return ts;
    }

    private static Telescope createNewTelescope(Map<String, Object> body) throws ParseException {
        Telescope ts = new Telescope();
        return setTelescopeInfo(ts, body);
    }

    private static Telescope setTelescopeInfo(Telescope ts, Map<String, Object> body) throws ParseException {
        ts.setVersion(body.get("version").toString());
        ts.setStatus(body.get("status").toString());
        ts.setState(body.get("state").toString());
        ts.setLocation(body.get("location").toString());
        ts.setLat(Double.parseDouble(body.get("lat").toString()));
        ts.setLon(Double.parseDouble(body.get("lon").toString()));
        ts.setStep(Double.parseDouble(body.get("step").toString()));
        return ts;
    }

}
