package fr.ktheo.back.model;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(unique = true)
    @Size(max = 50)
    private String username;

    @Email
    @NonNull
    @Column(unique = true)
    private String mail;

    @NonNull
    private String password;

    @NonNull
    private boolean isVerified;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roleList;

    @OneToOne
    private UserData userData;

    @OneToMany
    private Set<Asset> assets;

    @OneToMany
    private Set<Artwork>    artworks;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "kudos")
    private List<Artwork>   kudoedArtworks;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reportedUser")
    private List<UserReport>   reports;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reporter")
    private List<Report>      delations;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Auction>       auctions;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Bid>           bids;
}
