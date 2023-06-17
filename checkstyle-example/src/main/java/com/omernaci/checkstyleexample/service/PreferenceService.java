package com.omernaci.checkstyleexample.service;

import com.omernaci.checkstyleexample.service.dto.PreferenceDto;

import java.util.List;

public interface PreferenceService {

    PreferenceDto savePreference(PreferenceDto preferenceDto);

    PreferenceDto getPreferenceById(Long id);

    List<PreferenceDto> getAllPreferences();

}
