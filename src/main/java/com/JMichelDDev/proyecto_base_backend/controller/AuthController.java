// src/main/java/com/tuempresa/proyecto/controller/AuthController.java
package com.JMichelDDev.proyecto_base_backend.controller;

import com.JMichelDDev.proyecto_base_backend.dto.AuthRequest;
import com.JMichelDDev.proyecto_base_backend.dto.AuthResponse;
import com.JMichelDDev.proyecto_base_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Endpoint para iniciar sesión.
     * Recibe las credenciales en el cuerpo de la petición y, si son válidas,
     * genera y retorna un token JWT.
     *
     * @param authRequest Objeto que contiene el username y password.
     * @return AuthResponse que incluye el token JWT.
     */
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody AuthRequest authRequest) {
        // Autentica al usuario utilizando el AuthenticationManager
        System.out.println("controller");
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        System.out.println("controller_2");
        // Extrae los detalles del usuario autenticado
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("controller3");
        // Genera el token JWT utilizando JwtUtil
        System.out.println(userDetails);
        String token = jwtUtil.generateToken(userDetails);

        System.out.println("controller4");
        // Retorna el token en un objeto de respuesta
        return new AuthResponse(token);
    }
}
