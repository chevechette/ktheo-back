package fr.ktheo.back.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String locale;

    private Date birthDate;

    @NonNull
    private Date creationDate;

    @NonNull
    private Date lastSeen;

    private String facebookLink;
    private String twitterLink;
    private String instagramLink;

    @NonNull
    private boolean tutorialized;

}
