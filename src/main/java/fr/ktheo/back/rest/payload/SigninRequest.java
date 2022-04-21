package fr.ktheo.back.rest.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SigninRequest {

    @NotBlank
    @Size(min = 3)
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;
}
