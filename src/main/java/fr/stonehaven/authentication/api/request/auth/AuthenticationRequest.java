package fr.stonehaven.authentication.api.request.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AuthenticationRequest {

    @NotNull
    private String email;
    @NotNull
    private String password;
}
