// src/main/java/com/tuempresa/proyecto/security/JwtUtil.java
package com.JMichelDDev.proyecto_base_backend.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
//import io.jsonwebtoken.security.Keys;
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    // Genera el token basado en los datos del usuario
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        System.out.println("generateToken inicio");
        return createToken(claims, userDetails.getUsername());
    }

    // Crea el token configurando los claims, el sujeto, la fecha de emisión y expiración, y la firma
    private String createToken(Map<String, Object> claims, String subject) {
        // Supongamos que 'secret' es tu clave en texto plano

         Key key_secret = null; 

       try {
        System.out.println("cToken3");
            key_secret = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            System.out.println("Clave generada correctamente: " + key_secret);
        } catch (Exception e) {
            System.out.println("Error al generar la clave: " + e.getMessage());
            e.printStackTrace(System.out);  // Opcional: imprime el stack trace completo
        }

        return  Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
        .signWith(key_secret)
        .compact();
    }

    // Extrae el nombre de usuario (subject) del token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extrae la fecha de expiración del token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Método genérico para extraer cualquier claim utilizando una función
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extrae todos los claims del token
    private Claims extractAllClaims(String token) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                   .setSigningKey(key)
                   .parseClaimsJws(token)
                   .getBody();
    }

    // Verifica si el token ha expirado
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Valida el token comprobando que el nombre de usuario coincida y que no haya expirado
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
