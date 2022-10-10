package com.revature.hitthestacks.user;

import com.revature.hitthestacks.user.dto.NewUserRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    @Column(name = "email", nullable = false, unique = true)
    private  String email;

    @Column(name = "password", nullable = false)
    private  String password;

    @Column(name = "fname", nullable = false)
    private  String fname;

    @Column(name = "lname", nullable = false)
    private  String lname;

    @Column(name = "department")
    private String department;

    @Column(nullable = false)
    private boolean isFaculty;

    @Column(nullable = false)
    private boolean isActive;

    public User(NewUserRequest newUserRequest){
        this.id = UUID.randomUUID().toString();
        this.email = newUserRequest.getEmail();
        this.password = newUserRequest.getPassword();
        this.fname = newUserRequest.getFname();
        this.lname = newUserRequest.getLname();
        this.department = newUserRequest.getDepartment();
        this.isFaculty = false;
        this.isActive = true;
    }
}
