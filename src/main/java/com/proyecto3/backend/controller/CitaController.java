package com.proyecto3.backend.controller;

import com.proyecto3.backend.dto.request.CitaRequest;
import com.proyecto3.backend.dto.response.CitaResponse;
import com.proyecto3.backend.dto.response.MensajeResponse;
import com.proyecto3.backend.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "http://localhost:3000")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public ResponseEntity<List<CitaResponse>> listarTodos() {
        return ResponseEntity.ok(citaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponse> buscarPorId(@PathVariable Integer id) {
        CitaResponse response = citaService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MensajeResponse> guardar(
            @RequestBody CitaRequest request) {
        MensajeResponse response = citaService.guardar(request);
        if (!response.isExito()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeResponse> actualizar(
            @PathVariable Integer id,
            @RequestBody CitaRequest request) {
        MensajeResponse response = citaService.actualizar(id, request);
        if (!response.isExito()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponse> eliminar(@PathVariable Integer id) {
        MensajeResponse response = citaService.eliminar(id);
        if (!response.isExito()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
