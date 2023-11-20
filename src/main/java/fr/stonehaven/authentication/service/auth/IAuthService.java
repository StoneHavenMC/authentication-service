package fr.stonehaven.authentication.service.auth;

import fr.stonehaven.authentication.api.request.auth.AuthenticationRequest;
import org.springframework.security.core.AuthenticationException;

public interface IAuthService {

    String authenticate(AuthenticationRequest request) throws AuthenticationException;
}
