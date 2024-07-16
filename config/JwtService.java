package com.example.timesheet.config;

import java.security.Key;

import java.util.function.Function;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "OKxUKbXHsMj5ghURasYgL6jdb0HNqzsZsDzce1q7fyzU8+OfTLHfiYP1dGXXOxa7RLPAJdqq7DzQRBVQ++ToUMKYjcWJUish4jMiPhis/xoOoir4WXE/DoPsAZT6Eb8KiXCAKrITmNW8gGPeMZnJhQYFUt3byAyKtPyeyPueuG/CjInR5Ns4ULKdrGb7cHP2MXZHDiOF/Nftu/o1QtNXxq0KCM9nYl41yy9LNqB/yEVySJe932lqzWDGeeFapI+fbiZP+t+UuR6ZO1sXcr7enoFxLuMIA3sV4O4FIaO9gj77FVOhMQP9FG7Y5zFWl66OoWFXWBBP1chvlLH/ur7o2yrjwLNaJbsG3VPzqqkeztw=";
    private static final long jwtExp = 1000 * 60 * 3; // 3 minute
    private static final long jwtRefreshExp = 1000 * 60 * 60 * 24 * 7; // 1 week

    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String jwt, Function<Claims, T> resolver) {
        final Claims claims = extractAllClaims(jwt);
        return resolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails, jwtExp);
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails, jwtRefreshExp);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expTime) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .signWith(GetSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(GetSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key GetSignInKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

}
