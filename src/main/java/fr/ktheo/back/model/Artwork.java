package fr.ktheo.back.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "artwork")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                id;

    @Size(min = 1)
    private String              title;

    private String              creator;

    private String              description;

    @Column(name = "creation_location", length = 80)
    private String              creationLocation;

    @Column(name = "is_restricted")
    private boolean             isRestricted = false;

    @Column(name = "estimated_price")
    private String              estimatedPrice;

    @Column(name = "created_on")
    @ColumnDefault(value="CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private LocalDateTime       createdOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category            category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "artwork_tag",
            joinColumns = @JoinColumn(name = "artwork_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag>           tags;


    /*
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User                user;
     */
}
