package fr.ktheo.back.model;


import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Profil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String avatar;
    @NonNull
    private String description;
    @NonNull
    private String language;

    private int views;

    @NonNull
    @ManyToOne
    private User user;

}
