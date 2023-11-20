package fr.stonehaven.authentication.service.auth;

import fr.stonehaven.authentication.api.request.auth.AuthenticationRequest;
import fr.stonehaven.authentication.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService {

    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public String authenticate(AuthenticationRequest request) throws AuthenticationException {
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(credentials);
        return jwtService.generateToken((UserDetails) authentication.getPrincipal());
    }
}
