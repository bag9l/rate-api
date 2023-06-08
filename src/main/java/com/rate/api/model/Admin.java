package com.rate.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`admin`")
@Entity
public class Admin extends User {

    @OneToOne(mappedBy = "admin")
    private Faculty faculty;


    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                "faculty=" + faculty +
                '}';
    }
}
