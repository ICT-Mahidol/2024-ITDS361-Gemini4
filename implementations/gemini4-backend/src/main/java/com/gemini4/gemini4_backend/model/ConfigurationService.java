package com.gemini4.gemini4_backend.model;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ConfigurationService {

    private String currentConfig;

    public ConfigurationService() {
        try {
            // Load default config once when service starts
            String workingDir = System.getProperty("user.dir");
            this.currentConfig = new String(Files.readAllBytes(Paths.get(workingDir, "references/gemini_config_default.json")));
        } catch (Exception e) {
            this.currentConfig = "{}";
            System.err.println("Failed to load default config: " + e.getMessage());
        }
    }

    public String getCurrentConfig() {
        return currentConfig;
    }

    public boolean updateConfig(String newConfig) {
        if (isJSONValid(newConfig)) {
            this.currentConfig = newConfig;
            return true;
        }
        return false;
    }

    public boolean isJSONValid(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException ex) {
            try {
                new JSONArray(json);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
