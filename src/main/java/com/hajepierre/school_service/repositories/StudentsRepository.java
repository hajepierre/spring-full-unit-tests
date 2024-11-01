package com.hajepierre.school_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hajepierre.school_service.entities.Students;

public interface StudentsRepository extends JpaRepository<Students, Integer> {
    Students findByRegistrationNumber(String registrationNumber);
}
