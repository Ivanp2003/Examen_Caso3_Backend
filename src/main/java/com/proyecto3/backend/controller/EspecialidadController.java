package com.proyecto3.backend.controller;

import com.proyecto3.backend.dto.request.EspecialidadRequest;
import com.proyecto3.backend.dto.response.EspecialidadResponse;
import com.proyecto3.backend.dto.response.MensajeResponse;
import com.proyecto3.backend.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
@CrossOrigin(origins = "http://localhost:3000")
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<List<EspecialidadResponse>> listarTodos() {
        return ResponseEntity.ok(especialidadService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadResponse> buscarPorId(@PathVariable Integer id) {
        EspecialidadResponse response = especialidadService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MensajeResponse> guardar(
            @RequestBody EspecialidadRequest request) {
        MensajeResponse response = especialidadService.guardar(request);
        if (!response.isExito()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeResponse> actualizar(
            @PathVariable Integer id,
            @RequestBody EspecialidadRequest request) {
        MensajeResponse response = especialidadService.actualizar(id, request);
        if (!response.isExito()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeResponse> eliminar(@PathVariable Integer id) {
        MensajeResponse response = especialidadService.eliminar(id);
        if (!response.isExito()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}