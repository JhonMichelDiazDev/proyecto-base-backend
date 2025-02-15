// src/main/java/com/tuempresa/proyecto/dto/AuthRequest.java
package com.JMichelDDev.proyecto_base_backend.dto;

import jakarta.validation.constraints.NotBlank;


public class AuthRequest {
  
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;
    
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    public AuthRequest() {}

    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters...
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
