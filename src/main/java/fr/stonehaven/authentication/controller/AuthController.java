package fr.stonehaven.authentication.controller;

import fr.stonehaven.authentication.api.request.auth.AuthenticationRequest;
import fr.stonehaven.authentication.api.response.auth.AuthenticationTokenResponse;
import fr.stonehaven.authentication.api.response.user.TUserResponse;
import fr.stonehaven.authentication.entity.User;
import fr.stonehaven.authentication.exception.UserNotFoundException;
import fr.stonehaven.authentication.service.auth.IAuthService;
import fr.stonehaven.authentication.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TUserResponse> getUser(Authentication authentication) throws UserNotFoundException {
        final long start = System.currentTimeMillis();

        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(new TUserResponse(user, start));
    }
}
