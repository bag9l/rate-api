package com.rate.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`student`")
@Entity
public class Student extends User{

    @Column(name = "`group`")
    private String group;

    public Student(String login,
                   String password,
                   String fullName,
                   String group,
                   Boolean isAccountExpired,
                   Boolean isAccountLocked,
                   Boolean isCredentialsExpired,
                   Boolean isEnabled) {
        super(login, password, Role.STUDENT, fullName, isAccountExpired, isAccountLocked, isCredentialsExpired, isEnabled);
        this.group = group;
    }
}
