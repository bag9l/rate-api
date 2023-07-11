package com.rate.api.model.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rate.api.model.Faculty;
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
    @JsonManagedReference
    private Faculty faculty;


    @Override
    public String toString() {
        return "Admin{" +
                super.toString() +
                "faculty=" + faculty +
                '}';
    }
}
