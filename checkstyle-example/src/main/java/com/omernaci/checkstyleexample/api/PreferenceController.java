package com.omernaci.checkstyleexample.api;

import com.omernaci.checkstyleexample.service.PreferenceService;
import com.omernaci.checkstyleexample.service.dto.PreferenceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preferences")
public class PreferenceController {
    private final PreferenceService preferenceService;

    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @PostMapping
    public ResponseEntity<PreferenceDto> createPreference(@RequestBody PreferenceDto preferenceDto) {
        PreferenceDto createdPreference = preferenceService.savePreference(preferenceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPreference);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreferenceDto> getPreferenceById(@PathVariable Long id) {
        PreferenceDto preference = preferenceService.getPreferenceById(id);
        if (preference != null) {
            return ResponseEntity.ok(preference);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PreferenceDto>> getAllPreferences() {
        List<PreferenceDto> preferences = preferenceService.getAllPreferences();
        return ResponseEntity.ok(preferences);
    }

}
