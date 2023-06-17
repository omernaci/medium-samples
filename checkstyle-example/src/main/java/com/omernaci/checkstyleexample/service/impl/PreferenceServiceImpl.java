package com.omernaci.checkstyleexample.service.impl;

import com.omernaci.checkstyleexample.persistence.entity.Preference;
import com.omernaci.checkstyleexample.persistence.repository.PreferenceRepository;
import com.omernaci.checkstyleexample.service.PreferenceService;
import com.omernaci.checkstyleexample.service.dto.PreferenceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PreferenceServiceImpl implements PreferenceService {

    private final PreferenceRepository preferenceRepository;

    public PreferenceServiceImpl(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    public PreferenceDto savePreference(PreferenceDto preferenceDto) {
        Preference preference = Preference.fromDto(preferenceDto);
        Preference savedPreference = preferenceRepository.save(preference);
        return savedPreference.toDto();
    }

    public PreferenceDto getPreferenceById(Long id) {
        Optional<Preference> preferenceOptional = preferenceRepository.findById(id);
        return preferenceOptional.map(Preference::toDto).orElse(null);
    }

    public List<PreferenceDto> getAllPreferences() {
        List<Preference> preferences = preferenceRepository.findAll();
        return preferences.stream()
                .map(Preference::toDto)
                .toList();
    }

}
