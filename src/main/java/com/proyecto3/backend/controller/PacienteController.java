package com.proyecto3.backend.controller;

import com.proyecto3.backend.dto.request.PacienteRequest;
import com.proyecto3.backend.dto.response.MensajeResponse;
import com.proyecto3.backend.dto.response.PacienteResponse;
import com.proyecto3.backend.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "http://localhost:3000")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Integer id) {
        PacienteResponse response = pacienteService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MensajeResponse> guardar(
            @RequestBody PacienteRequest request) {
        MensajeResponse response = pacienteService.guardar(request);
        if (!response.isExito()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeResponse> actualizar(
            @PathVariable Integer id,
            @RequestBody PacienteRequest request) {
        MensajeResponse response = pacienteService.actualizar(id, request);
        if (!response.isExito()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponse> eliminar(@PathVariable Integer id) {
        MensajeResponse response = pacienteService.eliminar(id);
        if (!response.isExito()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}