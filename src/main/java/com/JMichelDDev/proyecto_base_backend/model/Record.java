// src/main/java/com/tuempresa/proyecto/model/Record.java
package com.JMichelDDev.proyecto_base_backend.model;

import lombok.Data;

import jakarta.persistence.*;


@Data
@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;
}
