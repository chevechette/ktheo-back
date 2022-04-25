package fr.ktheo.back.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Past;
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

    @Past(message = "La date doit être antérieur à la date actuelle")
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
