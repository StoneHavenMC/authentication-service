package fr.stonehaven.authentication.controller;

import fr.stonehaven.authentication.api.request.auth.AuthenticationRequest;
import fr.stonehaven.authentication.api.response.auth.AuthenticationTokenResponse;
import fr.stonehaven.authentication.api.response.user.UserResponse;
import fr.stonehaven.authentication.entity.DBUser;
import fr.stonehaven.authentication.exception.UserNotFoundException;
import fr.stonehaven.authentication.service.auth.IAuthService;
import fr.stonehaven.authentication.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final IAuthService authService;
    private final IUserService userService;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationTokenResponse> authenticate(@RequestBody AuthenticationRequest request) {
        final long start = System.currentTimeMillis();
        String token = authService.authenticate(request);
        return ResponseEntity.ok(new AuthenticationTokenResponse(token, start));
    }

    @GetMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Boolean> verify() {
        return Map.of("authenticated", true);
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getUser(Authentication authentication) throws UserNotFoundException {
        String email = authentication.getName();
        DBUser user = userService.getUserByEmail(email);
        return ResponseEntity.ok(new UserResponse(user));
    }
}
