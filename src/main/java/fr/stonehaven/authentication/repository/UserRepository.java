package fr.stonehaven.authentication.repository;

import fr.stonehaven.authentication.entity.DBUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<DBUser, UUID> {

    Optional<DBUser> findByName(String name);

    Optional<DBUser> findByEmail(String email);
}
