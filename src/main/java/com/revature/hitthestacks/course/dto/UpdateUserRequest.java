package com.revature.hitthestacks.course.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class UpdateUserRequest {
    private  String email;
    private  String password;
    private  String fname;
    private  String lname;
    private String department;
    private boolean isFaculty;
    private boolean isActive;
}
