package fr.stonehaven.authentication.api.response.auth;

import fr.stonehaven.authentication.api.response.TimeElapsedResponse;
import lombok.Getter;

@Getter
public class AuthenticationTokenResponse extends TimeElapsedResponse {

    private final String token;

    public AuthenticationTokenResponse(String token, long start) {
        super(start);
        this.token = token;
    }
}
