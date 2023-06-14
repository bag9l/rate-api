package com.rate.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`student`")
@Entity
public class Student extends User {

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = true)
    @ToString.Exclude
    @JsonBackReference
    private Group group;


    public Student(String login,
                   String password,
                   String fullName,
                   String email,
                   Group group,
                   Boolean isExpired,
                   Boolean isLocked,
                   Boolean isCredentialsExpired,
                   Boolean isEnabled) {
        super(login, password, fullName, email, Role.STUDENT, isExpired, isLocked, isCredentialsExpired, isEnabled);
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() +
                "group=" + group +
                '}';
    }
}
