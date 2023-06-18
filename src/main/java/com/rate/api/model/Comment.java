package com.rate.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Table(name = "`comment`")
@Entity
public class Comment {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = true)
    @ToString.Exclude
    private Student owner;

    @Column(name = "`text`")
    private String text;

    @Column(name = "`date`")
    private LocalDate date;

    @Column(name = "`score`")
    private Double score;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "educational_method_id", referencedColumnName = "id", nullable = true)
    @ToString.Exclude
    @JsonBackReference
    private EducationalMethod educationalMethod;

    public Comment(Student owner, String text, LocalDate date, Double score, EducationalMethod educationalMethod) {
        this.owner = owner;
        this.text = text;
        this.date = date;
        this.score = score;
        this.educationalMethod = educationalMethod;
    }
}
