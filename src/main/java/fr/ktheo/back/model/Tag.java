package fr.ktheo.back.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tag")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long            id;

    @Column(nullable = false, length = 80)
    private String          tag;

    @Column(name = "is_trigger")
    private boolean         isTrigger = false;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
    private Set<Artwork> artworks;

}
