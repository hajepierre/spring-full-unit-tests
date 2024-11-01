package com.hajepierre.school_service.repositories;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hajepierre.school_service.entities.Departments;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DepartmentsRepositoryTests {
    private final String INIT_NAME = "Civil Engineering";

    @Autowired
    private DepartmentsRepository repo;

    @BeforeEach
    public void before() {
        Departments d = Departments.builder().id(1).name(INIT_NAME).build();
        repo.save(d);
    }

    @Test
    public void DepartmentDepository_Save_ReturnsDepartment() {
        String name = "Computer Engineering";
        Departments d = Departments.builder().name(name).build();
        Departments result = repo.save(d);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualToIgnoringCase(name);
        Assertions.assertThat(result.getId()).isGreaterThan(0);
    }

    @Test
    public void DepartmentsRepository_FindAll_ResultsAllDepartments() {
        List<Departments> depts = repo.findAll();
        Assertions.assertThat(depts.size()).isGreaterThan(0);
        Assertions.assertThat(depts.size()).isEqualTo(1);
    }

    @Test
    public void DepartmentsRepository_FindById_ReturnsDepartmentWithSpecifiedId(){
        Optional<Departments> d= repo.findById(1);
        Assertions.assertThat(d.isPresent());
    }

    @Test
    public void DepartmentsRepository_FindById_ReturnsNull(){
        Optional<Departments> d= repo.findById(2);
        Assertions.assertThat(!d.isPresent());
    }

    @Test
    public void DepartmentsRepository_FindByName_ReturnsDepartmentWithSpecifiedName(){
        Departments dept= repo.findByName(INIT_NAME);
        Assertions.assertThat(dept).isNotNull();
        Assertions.assertThat(dept.getName()).isEqualTo(INIT_NAME);
    }

}
