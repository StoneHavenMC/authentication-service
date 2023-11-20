package fr.stonehaven.authentication.entity;

import fr.stonehaven.authentication.enums.UserRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "_user")
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRoles role = UserRoles.USER;
}
