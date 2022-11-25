package com.revature.hitthestacks.util.security.dto;

import com.revature.hitthestacks.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Principal {

    @NotBlank
    private String id;
    @NotBlank
    private String email;
    private boolean isFaculty;
    private boolean isActive;

    public Principal(User authUser) {
        this.id = authUser.getId();
        this.email = authUser.getEmail();
        this.isFaculty = authUser.isFaculty();
        this.isActive = authUser.isFaculty();
    }
}
