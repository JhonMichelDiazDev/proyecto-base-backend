// src/main/java/com/tuempresa/proyecto/controller/RecordController.java
package com.JMichelDDev.proyecto_base_backend.controller;

import com.JMichelDDev.proyecto_base_backend.model.Record;
import com.JMichelDDev.proyecto_base_backend.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    // GET /api/records - Obtiene todos los registros
    @GetMapping
    public List<Record> getAllRecords() {
        return recordService.getAllRecords();
    }

    // GET /api/records/{id} - Obtiene un registro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Record> getRecordById(@PathVariable Long id) {
        return recordService.getRecordById(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/records - Crea un nuevo registro
    @PostMapping
    public Record createRecord(@RequestBody Record record) {
        return recordService.createRecord(record);
    }

    // PUT /api/records/{id} - Actualiza un registro existente
    @PutMapping("/{id}")
    public ResponseEntity<Record> updateRecord(@PathVariable Long id, @RequestBody Record recordDetails) {
        Record updatedRecord = recordService.updateRecord(id, recordDetails);
        return ResponseEntity.ok(updatedRecord);
    }

    // DELETE /api/records/{id} - Elimina un registro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}
