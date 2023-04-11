package com.rate.api.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`lecturer`")
@Entity
public class Lecturer extends User{

    @Column(name = "`degree`")
    @Enumerated(EnumType.STRING)
    private Degree degree;

    public Lecturer(String id,
                    String login,
                    String password,
                    String fullName,
                    Boolean isAccountExpired,
                    Boolean isAccountLocked,
                    Boolean isCredentialsExpired,
                    Boolean isEnabled,
                    Degree degree) {
        super(id, login, password, Role.LECTURER, fullName, isAccountExpired, isAccountLocked, isCredentialsExpired, isEnabled);
        this.degree = degree;
    }
}
