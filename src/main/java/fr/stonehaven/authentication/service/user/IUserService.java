package fr.stonehaven.authentication.service.user;

import fr.stonehaven.authentication.entity.User;
import fr.stonehaven.authentication.exception.UserNotFoundException;

public interface IUserService {

    User getUserByEmail(String email) throws UserNotFoundException;
}
