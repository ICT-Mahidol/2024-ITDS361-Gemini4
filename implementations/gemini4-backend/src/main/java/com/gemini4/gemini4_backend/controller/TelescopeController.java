package com.gemini4.gemini4_backend.controller;

import edu.gemini.app.ocs.OCS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true") // adjust origin as needed
public class TelescopeController {

    private OCS ocs; // 👈 Not initialized at startup

    private OCS getOCS() {
        if (ocs == null) {
            ocs = new OCS(true); // 👈 Will only run once when first needed
        }
        return ocs;
    }


    @PostMapping("/execute")
    public ResponseEntity<String> executeCommand(@RequestBody Map<String, String> request) {
        String command = request.get("command");

        if (command == null || command.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Command cannot be empty.");
        }

        String result = getOCS().executeCommand(command.trim());
        return ResponseEntity.ok(result);
    }
}
