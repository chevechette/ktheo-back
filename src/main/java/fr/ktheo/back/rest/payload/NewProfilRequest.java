package fr.ktheo.back.rest.payload;

import fr.ktheo.back.model.User;
import lombok.*;

import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class NewProfilRequest {

    private String avatar;
    @NonNull
    private String description;
    @NonNull
    private String language;

    private int views;

    @NonNull
    @ManyToOne
    private User user;
}
