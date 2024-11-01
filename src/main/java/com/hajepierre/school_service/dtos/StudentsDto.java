package com.hajepierre.school_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentsDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String registrationNumber;
}
