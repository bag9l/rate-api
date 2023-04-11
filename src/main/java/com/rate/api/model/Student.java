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

    public Student(String id,
                   String login,
                   String password,
                   String fullName,
                   Boolean isAccountExpired,
                   Boolean isAccountLocked,
                   Boolean isCredentialsExpired,
                   Boolean isEnabled, String group) {
        super(id, login, password, Role.STUDENT, fullName, isAccountExpired, isAccountLocked, isCredentialsExpired, isEnabled);
        this.group = group;
    }
}
