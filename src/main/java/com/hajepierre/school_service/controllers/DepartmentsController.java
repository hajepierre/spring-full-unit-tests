package com.hajepierre.school_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.hajepierre.school_service.dtos.DepartmentsDto;
import com.hajepierre.school_service.entities.Departments;
import com.hajepierre.school_service.services.DepartmentsService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {

    @Autowired
    private DepartmentsService service;

    @PostMapping()
    public Departments saveDepartment(@RequestBody DepartmentsDto dto) {
        return service.record(dto);
    }

    @GetMapping()
    public List<Departments> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Departments getById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/names/{name}")
    public Departments getByName(@PathVariable String name) {
        return service.getByName(name);
    }

}
