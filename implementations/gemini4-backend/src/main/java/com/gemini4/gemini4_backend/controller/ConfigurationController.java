package com.gemini4.gemini4_backend.controller;

import com.gemini4.gemini4_backend.model.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @CrossOrigin
    @GetMapping("/get_default_config")
    public ResponseEntity<String> getConfig() {
        return ResponseEntity.ok(configurationService.getCurrentConfig());
    }

    @CrossOrigin
    @PostMapping("/update_config")
    public ResponseEntity<String> updateConfig(@RequestBody String newConfig) {
        boolean success = configurationService.updateConfig(newConfig);
        if (success) {
            return ResponseEntity.ok("SUCCESS: The new Gemini configuration is already updated.");
        } else {
            return ResponseEntity.badRequest().body("Failed to save file");
        }
    }
}
