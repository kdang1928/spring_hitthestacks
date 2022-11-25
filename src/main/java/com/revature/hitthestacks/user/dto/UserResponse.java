package com.revature.hitthestacks.user.dto;

import com.revature.hitthestacks.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {
    private String id;
    private  String email;
    private  String fname;
    private  String lname;
    private String department;
    private boolean isFaculty;
    private boolean isActive;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.fname = user.getFname();
        this.lname = user.getLname();
        this.department = user.getDepartment();
        this.isFaculty = user.isFaculty();
        this.isActive = user.isActive();
    }
}
