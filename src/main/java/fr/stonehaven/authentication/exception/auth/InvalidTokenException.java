package fr.stonehaven.authentication.exception.auth;

public class InvalidTokenException extends Exception {

    public InvalidTokenException() {
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
