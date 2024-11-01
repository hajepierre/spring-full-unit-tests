package com.hajepierre.school_service.controllers;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hajepierre.school_service.dtos.DepartmentsDto;
import com.hajepierre.school_service.entities.Departments;
import com.hajepierre.school_service.services.DepartmentsService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;

@WebMvcTest(controllers = DepartmentsController.class)
@ExtendWith(MockitoExtension.class)
// This annotation is used to avoid security setup, with this we can test
// without providing test token
@AutoConfigureMockMvc(addFilters = false)
public class DepartmentsControllerTests {
    private final String NAME = "Computer Engineering";
    private final Integer ID = 1;

    @Autowired
    private MockMvc mv;

    @MockBean
    private DepartmentsService service; // We use mock bean as the service is auto wired in the controller

    @Autowired
    private ObjectMapper objectMapper;
    private Departments dept;
    private DepartmentsDto dto;

    @BeforeEach
    public void init() {
        dept = Departments.builder()
                .id(ID)
                .name(NAME)
                .build();
        dto = DepartmentsDto.builder()
                .id(ID)
                .name(NAME)
                .build();
    }

    @Test
    public void DepartmentControllers_SaveDepartment_ReturnsStatusOk() throws Exception {
        when(service.record(Mockito.any())).thenReturn(dept);

        // Call mockmvc perform which return

        ResultActions response = mv.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(dept.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(dept.getId())));
    }

    @Test
    public void DepartmentControllers_GetAll_ReturnsListOfDepartments() throws Exception {
        List<Departments> list = new ArrayList<>();
        list.add(dept);
        when(service.getAll()).thenReturn(list);

        ResultActions response = mv.perform(get("/departments")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", CoreMatchers.is(1)));
    }

    @Test
    public void DepartmentControllers_GetById_ReturnsDepartmentWithSpecifiedId() throws Exception {
        when(service.getById(ID)).thenReturn(dept);
        ResultActions response = mv.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(dept.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(dept.getId())));
    }

    @Test
    public void DepartmentControllers_GetByName_ReturnsDepartmentWithSpecifiedName() throws Exception {
        when(service.getByName(NAME)).thenReturn(dept);
        ResultActions response = mv.perform(get("/departments/names/"+NAME)
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(dept.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(dept.getId())));
    }
}
