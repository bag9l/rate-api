package com.rate.api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`stream`")
@Entity
public class Statistic {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id", nullable = true)
    @ToString.Exclude
    private Lecturer lecturer;

    @Column(name = "quality_of_teaching")
    private Double qualityOfTeaching;

    @Column(name = "methodological_support")
    private Double methodologicalSupport;

    @Column(name = "objectivity_of_assessment")
    private Double objectivityOfAssessment;
}
