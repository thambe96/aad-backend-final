package edu.lk.ijse.gdse.aad.aadBackendFinal.util;


import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.User;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.expiration}")
    private long expiration;

    @Value("${jwt.secret}")
    private String secretKey;

    private final UserRepo userRepo;


    public String generateToken(String username) {

        Optional<User> userOptional = userRepo.findByName(username);

       User user = userOptional.orElse(null);


        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());
        claims.put("email", user.getEmail());
        claims.put("id", user.getId());

        if (user.getUserImage() != null) {
            claims.put("userImageUrl", user.getUserImage().getImageUrl());
        } else {
            claims.put("userImageUrl", "");
        }


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date
                        (System.currentTimeMillis() + expiration))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes())
                        , SignatureAlgorithm.HS256).compact();
    }
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor
                            (secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
