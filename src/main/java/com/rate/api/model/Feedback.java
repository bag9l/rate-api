package com.rate.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rate.api.model.user.Lecturer;
import com.rate.api.model.user.Student;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`feedback`")
@Entity
public class Feedback {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id", nullable = true)
    @ToString.Exclude
    @JsonBackReference
    private Lecturer lecturer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    @ToString.Exclude
    private Student owner;

    @Column(name = "quality_of_teaching")
    private Double qualityOfTeaching;

    @Column(name = "methodological_support")
    private Double methodologicalSupport;

    @Column(name = "objectivity_of_assessment")
    private Double objectivityOfAssessment;

    @Column(name = "`comment`", columnDefinition = "text")
    private String comment;

    @Column(name = "`date`", nullable = false)
    private LocalDate date;

    public Feedback(Lecturer lecturer,
                    Student owner,
                    Double qualityOfTeaching,
                    Double methodologicalSupport,
                    Double objectivityOfAssessment,
                    String comment,
                    LocalDate date) {
        this.lecturer = lecturer;
        this.owner = owner;
        this.qualityOfTeaching = qualityOfTeaching;
        this.methodologicalSupport = methodologicalSupport;
        this.objectivityOfAssessment = objectivityOfAssessment;
        this.comment = comment;
        this.date = date;
    }
}
