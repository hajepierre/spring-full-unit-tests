package com.hajepierre.school_service.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hajepierre.school_service.dtos.DepartmentsDto;
import com.hajepierre.school_service.entities.Departments;
import com.hajepierre.school_service.repositories.DepartmentsRepository;
import com.hajepierre.school_service.services.impls.DepartmentsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DepartmentsServiceTests {
    private final String NAME = "Computer Engineering";
    private final Integer ID = 1;

    private Departments dept;
    @Mock
    private DepartmentsRepository repo;

    @InjectMocks
    private DepartmentsServiceImpl service;

    @BeforeEach
    public void init() {
        dept = Departments.builder()
                .id(ID)
                .name(NAME)
                .build();
    }

    @Test
    public void DepartmentsService_Record_ReturnsDeparment() {
        DepartmentsDto dto = DepartmentsDto.builder()
                .id(ID)
                .name(NAME)
                .build();
        when(repo.save(Mockito.any())).thenReturn(dept);
        Departments result = service.record(dto);
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void DepartmentsService_GetAll_ReturnsListOfDepartments() {
        List<Departments> list = new ArrayList<>();
        list.add(dept);
        when(repo.findAll()).thenReturn(list);
        List<Departments> result = service.getAll();
        Assertions.assertThat(result.size()).isGreaterThan(0);
    }

    @Test
    public void DepartmentsService_GetById_ReturnsDepartmentWithSpecifiedId() {
        List<Departments> list = new ArrayList<>();
        list.add(dept);
        when(repo.findById(ID)).thenReturn(Optional.ofNullable(dept));
        Departments result = service.getById(ID);
        Assertions.assertThat(result.getId()).isEqualTo(ID);
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void DepartmentsService_GetByName_ReturnsDepartmentWithSpecifiedName() {
        List<Departments> list = new ArrayList<>();
        list.add(dept);

        when(repo.findByName(NAME)).thenReturn(dept);

        Departments result = service.getByName(NAME);
        Assertions.assertThat(result.getName()).isEqualTo(NAME);
        Assertions.assertThat(result).isNotNull();
    }

}
