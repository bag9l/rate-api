package com.rate.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`educational_method`")
@Entity
public class EducationalMethod {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "`name`")
    private String name;

    @Enumerated(EnumType.STRING)
    private EducationalMethodType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = true)
    @ToString.Exclude
    @JsonBackReference
    private Subject subject;

    @OneToMany(
            mappedBy = "educationalMethod",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @ToString.Exclude
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();

}
