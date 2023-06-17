package com.omernaci.checkstyleexample.persistence.repository;

import com.omernaci.checkstyleexample.persistence.entity.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

}
