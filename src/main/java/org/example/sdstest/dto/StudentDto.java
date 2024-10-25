package org.example.sdstest.dto;

import jakarta.validation.constraints.NotBlank;

public record StudentDto(
        @NotBlank(message = "ID is required")
        String id,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Middle name is required")
        String middleName,

        @NotBlank(message = "Group is required")
        String group,
        Double averageScore) {
}