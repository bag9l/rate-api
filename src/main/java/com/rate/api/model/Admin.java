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
@ToString()
@Table(name = "`admin`")
@Entity
public class Admin extends User{

    @OneToOne(mappedBy = "admin")
    private Faculty faculty;
}
