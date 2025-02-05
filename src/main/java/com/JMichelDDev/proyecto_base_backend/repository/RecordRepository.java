// src/main/java/com/tuempresa/proyecto/repository/RecordRepository.java
package com.JMichelDDev.proyecto_base_backend.repository;

import com.JMichelDDev.proyecto_base_backend.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    // Aquí puedes definir consultas personalizadas si es necesario.
}
