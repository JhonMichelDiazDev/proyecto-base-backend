// src/main/java/com/tuempresa/proyecto/dto/AuthResponse.java
package com.JMichelDDev.proyecto_base_backend.dto;

public class AuthResponse {

    private String token;

    // Constructor vacío (requerido para frameworks de serialización/deserialización)
    public AuthResponse() {}

    // Constructor que recibe el token
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter para el token
    public String getToken() {
        return token;
    }

    // Setter para el token
    public void setToken(String token) {
        this.token = token;
    }
}
