package fr.stonehaven.authentication.configuration;

import fr.stonehaven.authentication.entity.User;
import fr.stonehaven.authentication.exception.UserNotFoundException;
import fr.stonehaven.authentication.service.user.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SHUserDetailsService implements UserDetailsService {

    private final IUserService userService;

    public SHUserDetailsService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userService.getUserByEmail(email);
            return new SHUserDetails(user.getEmail(), user.getPassword());
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage(), e.getCause());
        }
    }
}