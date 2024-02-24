package fr.stonehaven.authentication.enums;

public enum UserRoles {

    USER,
    ADMIN
    ;

    public String getId() {
        return name();
    }
}
