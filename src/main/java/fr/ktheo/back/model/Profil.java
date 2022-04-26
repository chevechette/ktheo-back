package fr.ktheo.back.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Profil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    private String avatar;
    @NonNull
    private String description;
    @NonNull
    private String language;
    @NonNull
    private int views;

    @NonNull
    @ManyToOne
    private User user;
}