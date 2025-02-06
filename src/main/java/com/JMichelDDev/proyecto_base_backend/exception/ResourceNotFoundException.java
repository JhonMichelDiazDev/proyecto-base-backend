// src/main/java/com/tuempresa/proyecto/exception/ResourceNotFoundException.java
package com.JMichelDDev.proyecto_base_backend.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
