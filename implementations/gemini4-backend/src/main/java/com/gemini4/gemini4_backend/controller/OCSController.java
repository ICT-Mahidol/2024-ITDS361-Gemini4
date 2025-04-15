package com.gemini4.gemini4_backend.controller;

import edu.gemini.app.ocs.OCS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true") // adjust origin as needed
public class OCSController {

//  Initialize OCS class from library
    private OCS ocs;

    private OCS getOCS() {
        if (ocs == null) {
            ocs = new OCS(true);
        }
        return ocs;
    }

//  Execute command line
    @PostMapping("/execute")
    public ResponseEntity<String> executeCommand(@RequestBody Map<String, String> request) {
        String command = request.get("command");

        if (command == null || command.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Command cannot be empty.");
        }

        String result = getOCS().executeCommand(command.trim());
        return ResponseEntity.ok(result);
    }

//  Get Configuration
    @GetMapping("/getconfig")
    public List<Map<String, String>> getConfig() {
        List<Map<String, String>> result = new ArrayList<>();

        // Redirect System.out to a ByteArrayOutputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Call the method — it will print to outContent instead of console
        getOCS().getConfigurations();

        // Reset System.out back
        System.setOut(originalOut);

        // Parse the captured output line by line
        String[] lines = outContent.toString().split("\\r?\\n");
        for (String line : lines) {
            if (line.contains(":")) {
                String[] parts = line.split(":", 2);
                Map<String, String> entry = new HashMap<>();
                entry.put("id", parts[0].trim());
                entry.put("name", parts[1].trim());
                result.add(entry);
            }
        }

        return result;
    }


//  Install Configuration
    @PostMapping("/installconfig")
    public ResponseEntity<?> installConfig(@RequestBody Map<String, String> request) {
        String config_name = request.get("config_name");

        if (config_name == null || config_name.trim().isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("Configuration name must not be null or empty.");
        }

        Boolean result = getOCS().addConfiguration(config_name.trim());
        return ResponseEntity.ok(result);
    }

//  Remove Configuration by No.
    @RequestMapping("/removeconfig/{config_no}")
    public Boolean removeConfig(@PathVariable("config_no") int config_no) {
        return getOCS().removeConfiguration(config_no);
    }


}
