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

import com.hajepierre.school_service.entities.Students;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StudentsRepositoryTests {

    private final Students STD = Students.builder()
            .id(1)
            .firstName("Joe")
            .lastName("Doe")
            .registrationNumber("123")
            .build();

    @Autowired
    private StudentsRepository repo;

    @BeforeEach
    public void before() {
        repo.save(STD);
    }

    @Test
    public void StudentsRepository_FindAll_ReturnsAllStudents() {
        List<Students> students= repo.findAll();
        Assertions.assertThat(students.size()).isGreaterThan(0);
    }

    @Test
    public void StudentsRepository_Save_ReturnsAStudent() {
        Students student = Students.builder()
            .firstName("Alice")
            .lastName("Bobi")
            .registrationNumber("124")
            .build();

        Students result= repo.save(student);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isGreaterThan(1);
        Assertions.assertThat(result.getFirstName()).isEqualTo("Alice");
        Assertions.assertThat(result.getLastName()).isEqualTo("Bobi");
        Assertions.assertThat(result.getRegistrationNumber()).isEqualTo("124");
    }

    @Test
    public void StudentsRepository_FindById_ReturnsStudentWithSpecifiedId() {
        Optional<Students> student= repo.findById(1);
        Assertions.assertThat(student.isPresent());
    }

    @Test
    public void StudentsRepository_FindAll_ReturnsStudentWithRegNumber() {
        Students student= repo.findByRegistrationNumber("123");
        Assertions.assertThat(student).isNotNull();
        Assertions.assertThat(student.getRegistrationNumber()).isEqualTo("123");
    }

  

}
