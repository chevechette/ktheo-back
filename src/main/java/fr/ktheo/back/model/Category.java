package fr.ktheo.back.model;

import lombok.*;

import javax.persistence.*;
/*
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @NonNull
    private ERole   name;


}
*/

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    @NonNull
    private ECategory   category;
}
