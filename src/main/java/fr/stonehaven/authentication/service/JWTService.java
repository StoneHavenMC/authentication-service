package fr.stonehaven.authentication.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import fr.stonehaven.authentication.exception.auth.InvalidTokenException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

@Service
public class JWTService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.rsa.public}")
    private String rsaPublicKey;
    @Value("${jwt.rsa.private}")
    private String rsaPrivateKey;
    @Value("${jwt.duration}")
    private int tokenDuration;

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    @PostConstruct
    public void loadKeyPair() {
        this.publicKey = (RSAPublicKey) loadKey(rsaPublicKey, false);
        this.privateKey = (RSAPrivateKey) loadKey(rsaPrivateKey, true);
    }

    private RSAKey loadKey(String keyPath, boolean privateKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] keyBytes = Files.readAllBytes(Paths.get(keyPath));
            if (privateKey) {
                EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
                return (RSAKey) keyFactory.generatePrivate(encodedKeySpec);
            } else {
                EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(keyBytes);
                return (RSAKey) keyFactory.generatePublic(encodedKeySpec);
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(UserDetails userDetails) {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            return JWT.create()
                    .withSubject(userDetails.getUsername())
                    .withClaim("roles", userDetails.getAuthorities().stream().toList())
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .withExpiresAt(new Date(System.currentTimeMillis() + tokenDuration))
                    .withIssuer("StoneHaven Authentication Service")
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public String validateToken(String token) throws InvalidTokenException {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("StoneHaven Authentication Service")
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException e) {
            throw new InvalidTokenException();
        }
    }
}
