package com.Mothof.TaskManager.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.springframework.core.PropagationContextElement.Key;

@Service
public class JwtService {
    private String secretKey;

    public JwtService() {
        try{
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keyGenerator.generateKey();
            this.secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }

    /*
    This method generate a JavaScript Web Token
    Parameter String username -> represents the username of the user trying to login

    The method then adds to the token the:
    - subject (i.e. username)
    - issue date
    - expiration date (i.e. issue date + 30 minutes)
    - a unique signature (i.e. secret key)
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1800000))
                .and()
                .signWith(getSecretKey())
                .compact();
    }

    /*
    This method takes the generated secret key (type String),
    decodes it into byte array format, and proceeds to use the
    HmacSHA256 algorithm to turn it into a SecretKey object.
     */
    private SecretKey getSecretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        SecretKey key = Keys.hmacShaKeyFor(decodedKey);
        return key;
    }

    /*
    This method takes a valid JWT token, and extracts the subject (username)
    from the token's payload
     */
    public String extractUsernameFromToken(String jwtToken) {
        return extractClaims(jwtToken, Claims::getSubject);
    }

    /*
    This method takes a valid JWT token, and extracts the expiration date
    from the token's payload
     */
    private Date extractExpirationDate(String jwtToken){
        return extractClaims(jwtToken, Claims::getExpiration);
    }

    /*
    This method checks if the user in the application context matches the user sending requests
    by username, and it also checks that the token from the user request has not expired.
     */
    public boolean validateToken(UserDetails userDetails, String jwtToken) {
        if (userDetails.getUsername().equals(extractUsernameFromToken(jwtToken))) {
            return extractExpirationDate(jwtToken).after(new Date(System.currentTimeMillis()));
        }
        return false;
    }

    /*
    This method is used to retrieve the specified data from the JWT token payload.
    It return different data types depending on the information that was requested.

    If we are requesting the expiration date, the return type will be Date. However,
    if we are requesting the subject, then the return type changes to String.

    Parameter Function<Claims, T> claimsResolver -> specified the information we seek
    from the JWT token's payload (e.g. subject, expiration date etc)
    Parameter String jwtToken -> The JWT token received from the user's HTTP request
     */
    private <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    /*
    This method retrieves all the information in a JWT token's payload.
    It (the method) creates a JWT parser which will be put to use decoding
    and verifying the JWT token.
     */
    private Claims extractAllClaims(String jwtToken){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }
}
