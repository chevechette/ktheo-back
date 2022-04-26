package fr.ktheo.back.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "artwork_report")
@Getter
@Setter
public class ArtworkReport extends Report {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reported_artwork_id")
    private Artwork     reportedArtwork;
}
