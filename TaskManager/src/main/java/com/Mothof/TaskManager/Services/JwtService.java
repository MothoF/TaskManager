package com.Mothof.TaskManager.Services;

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

    private SecretKey getSecretKey() {
        byte[] encodedKey = Base64.getDecoder().decode(secretKey);
        SecretKey key = Keys.hmacShaKeyFor(encodedKey);
        return key;
    }

    public String extractUsernameFromToken(String jwtToken) {
        return null;
    }

    public boolean validateToken(UserDetails userDetails, String jwtToken) {
        return true;
    }
}
