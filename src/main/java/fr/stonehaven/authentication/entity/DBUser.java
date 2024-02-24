package fr.stonehaven.authentication.entity;

import fr.stonehaven.authentication.enums.UserRoles;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("users")
@Getter
public class DBUser {

    @Id
    private UUID id;

    private String name;
    private String email;
    private String password;

    private UserRoles role;

    public DBUser() {
        this.role = UserRoles.USER;
    }
}
