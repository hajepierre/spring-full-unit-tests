package com.hajepierre.school_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hajepierre.school_service.entities.Departments;

public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {
    Departments findByName(String name);
}
