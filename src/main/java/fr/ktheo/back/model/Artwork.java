package fr.ktheo.back.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "artwork")
@NoArgsConstructor
@Getter
@Setter
public class Artwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                id;

    @NotBlank
    @Size(max = 256)
    private String              title;

    @NotBlank
    @Size(max = 256)
    private String              creator;

    @Size(max = 1024)
    private String              desc;

    @Column(name = "creation_location")
    @Size(max = 80)
    private String              creationLocation;

    @Column(name = "is_restricted")
    @ColumnDefault(false)
    private boolean             isRestricted;

    @Column(name = "estimated_price")
    private String              estimatedPrice;

    @Column(name = "created_on")
    @ColumnDefault(value="CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private LocalDateTime       createdOn;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User                user;
     */
}
