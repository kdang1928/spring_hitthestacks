package com.revature.hitthestacks.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class NewCourseRequest {

    @NotBlank(message = "Course name cannot be blank")
    private String name;

    @NotBlank(message = "Course description cannot be blank")
    private String description;

    @NotNull
    private int capacity;

    @NotNull
    private int units;
}
