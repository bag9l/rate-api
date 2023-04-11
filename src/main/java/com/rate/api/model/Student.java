package com.rate.api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`student`")
@Entity
public class Student extends User {

    @Column(name = "`course`")
    private Integer course;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = true)
    @ToString.Exclude
    private Group group;


    public Student(String login,
                   String password,
                   String fullName,
                   String email,
                   Integer course,
                   Group group,
                   Boolean isExpired,
                   Boolean isLocked,
                   Boolean isCredentialsExpired,
                   Boolean isEnabled) {
        super(login, password, fullName, email, Role.STUDENT, isExpired, isLocked, isCredentialsExpired, isEnabled);
        this.course = course;
        this.group = group;
    }
}
