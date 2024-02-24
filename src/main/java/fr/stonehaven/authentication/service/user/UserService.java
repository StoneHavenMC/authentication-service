package fr.stonehaven.authentication.service.user;

import fr.stonehaven.authentication.entity.DBUser;
import fr.stonehaven.authentication.exception.UserNotFoundException;
import fr.stonehaven.authentication.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public DBUser getUserByEmail(String email) throws UserNotFoundException {
        return repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("USER_NOT_FOUND_BY_EMAIL"));
    }
}
