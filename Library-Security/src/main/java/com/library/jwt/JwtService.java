package com.library.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private Logger logger= LoggerFactory.getLogger(JwtService.class);
    private final String SECRET_KEY="p5MMtEEczlSZZBV8tKZ5C3%K%zvcg1RzYj6qB$1L2y$6OpNGxs^wP&OhUirm1ln&Om%NDwDyKRNxer4SK%e62wFr*ZRE0Um2r@M6*iWazJpEPvbP!trAkt5EY8QJC0P34B4JvA5!RCG1Fcn4tOUF!PaFtta@Gd0nEqE?TzXMMcGxY8MvOtbV04QEbyP&GD%?aaek^3hQ&D9BjMfZc@NdPHaL9E@0s%!LLxmy56d1z4fhS1*wC0kYz9RVlWVD@8&g";

    public String generateToken(UserDetails userDetails){
        String authority = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
        logger.info("Claim = " + authority);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles",authority)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*10))
                .signWith(getSignKey())
                .compact();

    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String userName=extractUsername(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractCalims(token,Claims::getExpiration).before(new Date());
    }

    public String extractUsername(String token) {
        return extractCalims(token,Claims::getSubject);
    }

    private <T>T extractCalims(String token, Function<Claims,T> claimsResolver) {
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes= SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
