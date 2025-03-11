package com.myapp.run.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTUtils {

    private static final long EXPIRATION_TIME = 1000 * 60 * 24 * 7; //for 7 days

    private final SecretKey Key;



    public JWTUtils() {
        String secreteString = "843567893696976453275974432697R634977HJ67H6J7VH6V7V75JH24V76H5V654J6246HJR6834R8763T478378637664538745673865783678548735687R3DAGHSGDHSADAHSKNSAKSL";
        byte[] keyBytes = Decoders.BASE64.decode(secreteString);
        this.Key = Keys.hmacShaKeyFor(keyBytes);

    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser()
        		.verifyWith(Key)
        		.build()
        		.parseSignedClaims(token)
        		.getPayload());
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
