package com.rate.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`statistic`")
@Entity
public class Statistic {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id", nullable = true)
    @ToString.Exclude
    @JsonBackReference
    private Lecturer lecturer;

    @Column(name = "quality_of_teaching")
    private Double qualityOfTeaching;

    @Column(name = "methodological_support")
    private Double methodologicalSupport;

    @Column(name = "objectivity_of_assessment")
    private Double objectivityOfAssessment;
}
