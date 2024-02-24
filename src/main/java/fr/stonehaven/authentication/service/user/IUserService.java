package fr.stonehaven.authentication.service.user;

import fr.stonehaven.authentication.entity.DBUser;
import fr.stonehaven.authentication.exception.UserNotFoundException;

public interface IUserService {

    DBUser getUserByEmail(String email) throws UserNotFoundException;
}
