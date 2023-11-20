package fr.stonehaven.authentication.api.response.user;

import fr.stonehaven.authentication.api.response.TimeElapsedResponse;
import fr.stonehaven.authentication.entity.User;
import lombok.Getter;

@Getter
public class TUserResponse extends TimeElapsedResponse {

    private final UserResponse user;

    public TUserResponse(User user, long start) {
        super(start);
        this.user = new UserResponse(user);
    }
}
