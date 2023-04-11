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

    public Lecturer(String login,
                    String password,
                    String fullName,
                    Degree degree,
                    Boolean isAccountExpired,
                    Boolean isAccountLocked,
                    Boolean isCredentialsExpired,
                    Boolean isEnabled) {
        super(login, password, Role.LECTURER, fullName, isAccountExpired, isAccountLocked, isCredentialsExpired, isEnabled);
        this.degree = degree;
    }
}
