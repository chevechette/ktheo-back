package fr.ktheo.back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "auction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long                id;

    @ManyToOne
    private User                seller;

    @ManyToOne
    private Artwork             artwork;

    private LocalDateTime       startsOn;

    private LocalDateTime       endsOn;

    @OneToMany
    private List<Bid>           bids;
}
