// src/main/java/com/tuempresa/proyecto/service/RecordService.java
package com.JMichelDDev.proyecto_base_backend.service;

import com.JMichelDDev.proyecto_base_backend.model.Record;
import com.JMichelDDev.proyecto_base_backend.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    // Obtener todos los registros
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    // Obtener un registro por ID
    public Optional<Record> getRecordById(Long id) {
        return recordRepository.findById(id);
    }

    // Crear un nuevo registro
    public Record createRecord(Record record) {
        return recordRepository.save(record);
    }

    // Actualizar un registro existente
    public Record updateRecord(Long id, Record recordDetails) {
        return recordRepository.findById(id).map(record -> {
            record.setName(recordDetails.getName());
            record.setDescription(recordDetails.getDescription());
            return recordRepository.save(record);
        }).orElseGet(() -> {
            // Si no se encuentra el registro, se crea uno nuevo con el ID especificado
            recordDetails.setId(id);
            return recordRepository.save(recordDetails);
        });
    }

    // Eliminar un registro por ID
    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }
}
