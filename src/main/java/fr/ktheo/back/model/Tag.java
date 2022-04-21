package fr.ktheo.back.model;

import javax.persistence.*;

public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    @Column(nullable = false, length = 80)
    private String  tag;

    @Column(name = "is_trigger")
    private boolean isTrigger = false;
}
