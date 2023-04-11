package com.rate.api.model;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "educationalMethod_id", referencedColumnName = "id", nullable = true)
    @ToString.Exclude
    private EducationalMethod educationalMethod;
}
