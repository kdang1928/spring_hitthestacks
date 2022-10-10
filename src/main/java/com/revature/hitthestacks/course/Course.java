package com.revature.hitthestacks.course;

import com.revature.hitthestacks.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private int capacity;

    @Column
    private int units;

    @ManyToOne
    @Column(name = "faculty", nullable = false)
    private User faculty;

    @ManyToMany
    @Column(name = "students")
    private List<User> students;
}
