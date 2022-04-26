package fr.ktheo.back.model;


import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User implements UserDetails {

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
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roleList;


    @OneToMany
    private Set<Asset> assets;

    @OneToMany
    private Set<Artwork> artworks;

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

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private UserData userData;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleList
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}