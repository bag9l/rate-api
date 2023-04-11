package com.rate.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`lecturer`")
@Entity
public class Lecturer extends User {

    @Enumerated(EnumType.STRING)
    private Degree degree;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = true)
    @ToString.Exclude
    private Department department;

    @OneToMany(
            mappedBy = "lecturer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(
            mappedBy = "lecturer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    private List<Statistic> statistics = new ArrayList<>();


    public Lecturer(String login,
                    String password,
                    String fullName,
                    String email,
                    List<Subject> subjects,
                    Degree degree,
                    Department department,
                    List<Statistic> statistics,
                    Boolean isExpired,
                    Boolean isLocked,
                    Boolean isCredentialsExpired,
                    Boolean isEnabled
    ) {
        super(login, password, fullName, email, Role.LECTURER, isExpired, isLocked, isCredentialsExpired, isEnabled);
        this.subjects = subjects;
        this.degree = degree;
        this.department = department;
        this.statistics = statistics;
    }
}
