package fr.ktheo.back.model.payload;

import fr.ktheo.back.model.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateArtworkRequest {

    @Size(min = 1)
    @NonNull
    private String              title;

    @NonNull
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
    private LocalDateTime createdOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private long category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "artwork_tag",
            joinColumns = @JoinColumn(name = "artwork_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<String> tags;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private long owner;

    public Artwork     toEntity() {
        Artwork art;

        art = new Artwork();
        art.setTitle(this.title);
        art.setCreator(this.creator);
        art.setDescription(this.description);
        art.setCreationLocation(this.creationLocation);
        art.setRestricted(this.isRestricted);
        art.setEstimatedPrice(this.estimatedPrice);
        return art;
    }
}
