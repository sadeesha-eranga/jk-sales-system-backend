package com.icbt.jksalessystem.util;

import com.icbt.jksalessystem.model.AuthUserDetailDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2019-10-28
 * Time: 10:12 AM
 */
public class JwtUtil {

    private static final String SECRET_KEY = "V2xoc1MyVnRVbGhUVjJ4UVlWVnZlRmw2U2xkbFZXeHdaREpzYW1KVWJIcFhiR2hPWVZVNWMy";
    private static final JwtUtil jwtUtil = new JwtUtil();

    private JwtUtil() {
    }

    public static JwtUtil getInstance() {
        return jwtUtil;
    }

    public String extractUsername(String token) throws SignatureException {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) throws SignatureException {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws SignatureException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws SignatureException {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) throws SignatureException {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) throws SignatureException {
        Map<String, Object> claims = new HashMap<>();
        // Add custom claims here
        AuthUserDetailDTO authUser = new AuthUserDetailDTO();
        authUser.setUsername(userDetails.getUsername());
        authUser.setPassword(userDetails.getPassword());
        claims.put("roles", userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) throws SignatureException {
        AuthUserDetailDTO authUser = new AuthUserDetailDTO();
        authUser.setUsername(userDetails.getUsername());
        authUser.setPassword(userDetails.getPassword());
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
