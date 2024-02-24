package fr.stonehaven.authentication.api.response.user;

import fr.stonehaven.authentication.entity.DBUser;
import lombok.Getter;

@Getter
public class UserResponse {

    private final String id;
    private final String email;
    private final String username;
    private final String role;

    public UserResponse(DBUser user) {
        this.id = user.getId().toString();
        this.email = user.getEmail();
        this.username = user.getName();
        this.role = user.getRole().getId();
    }
}
