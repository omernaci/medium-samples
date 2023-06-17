package com.omernaci.checkstyleexample.persistence.entity;

import com.omernaci.checkstyleexample.service.dto.PreferenceDto;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "preferences")
@Where(clause = "is_enabled = true")
public class Preference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preference_name")
    private String preferenceName;

    @Column(name = "preference_value")
    private String preferenceValue;

    private boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreferenceName() {
        return preferenceName;
    }

    public void setPreferenceName(String preferenceName) {
        this.preferenceName = preferenceName;
    }

    public String getPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(String preferenceValue) {
        this.preferenceValue = preferenceValue;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public PreferenceDto toDto() {
        return new PreferenceDto(this.preferenceName, this.preferenceValue);
    }

    public static Preference fromDto(PreferenceDto dto) {
        Preference preference = new Preference();
        preference.setPreferenceName(dto.preferenceName());
        preference.setPreferenceValue(dto.preferenceValue());
        return preference;
    }

}
