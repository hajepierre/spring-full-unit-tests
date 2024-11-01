package com.hajepierre.school_service.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hajepierre.school_service.dtos.DepartmentsDto;
import com.hajepierre.school_service.entities.Departments;
import com.hajepierre.school_service.repositories.DepartmentsRepository;
import com.hajepierre.school_service.services.DepartmentsService;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {
    @Autowired
    private DepartmentsRepository repo;

    @Override
    public Departments record(DepartmentsDto dto) {
        Departments dept = Departments.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
        return repo.save(dept);
    }

    @Override
    public Departments getByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public Departments getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Departments> getAll() {
        return repo.findAll();
    }

}
