package fr.ktheo.back.model.payload;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class newAddressRequest {
    @NonNull
    private String town;
    @NonNull
    private int streetNumber;

    private String streetNumberComplement;
    @NonNull
    private String streetName;
    @NonNull
    private int postalCode;
    private long userId;
}
