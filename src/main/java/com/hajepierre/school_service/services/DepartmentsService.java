package com.hajepierre.school_service.services;

import java.util.List;

import com.hajepierre.school_service.dtos.DepartmentsDto;
import com.hajepierre.school_service.entities.Departments;

public interface DepartmentsService {
    Departments record(DepartmentsDto dto);
    Departments getByName(String name);
    Departments getById(Integer id);
    List<Departments> getAll();
}
