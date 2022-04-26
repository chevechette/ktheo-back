package fr.ktheo.back.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    private String  reason;

    @ManyToOne
    private User    reporter;
}
