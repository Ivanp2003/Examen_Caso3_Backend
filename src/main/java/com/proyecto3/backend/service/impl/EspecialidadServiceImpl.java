package com.proyecto3.backend.service.impl;

import com.proyecto3.backend.dto.request.EspecialidadRequest;
import com.proyecto3.backend.dto.response.EspecialidadResponse;
import com.proyecto3.backend.dto.response.MensajeResponse;
import com.proyecto3.backend.model.entity.Especialidad;
import com.proyecto3.backend.repository.EspecialidadRepository;
import com.proyecto3.backend.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public List<EspecialidadResponse> listarTodos() {
        return especialidadRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EspecialidadResponse buscarPorId(Integer id) {
        return especialidadRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public MensajeResponse guardar(EspecialidadRequest request) {
        try {
            Especialidad especialidad = new Especialidad();
            especialidad.setCodigo(request.getCodigo());
            especialidad.setNombre(request.getNombre());
            especialidad.setDescripcion(request.getDescripcion());
            especialidadRepository.save(especialidad);
            return new MensajeResponse("Especialidad guardada correctamente",
                    true, null);
        } catch (Exception e) {
            return new MensajeResponse("Error al guardar especialidad: "
                    + e.getMessage(), false, null);
        }
    }

    @Override
    public MensajeResponse actualizar(Integer id, EspecialidadRequest request) {
        try {
            Optional<Especialidad> opt = especialidadRepository.findById(id);
            if (opt.isEmpty()) {
                return new MensajeResponse("Especialidad no encontrada",
                        false, null);
            }
            Especialidad especialidad = opt.get();
            especialidad.setCodigo(request.getCodigo());
            especialidad.setNombre(request.getNombre());
            especialidad.setDescripcion(request.getDescripcion());
            especialidadRepository.save(especialidad);
            return new MensajeResponse("Especialidad actualizada correctamente",
                    true, null);
        } catch (Exception e) {
            return new MensajeResponse("Error al actualizar especialidad: "
                    + e.getMessage(), false, null);
        }
    }

    @Override
    public MensajeResponse eliminar(Integer id) {
        try {
            if (!especialidadRepository.existsById(id)) {
                return new MensajeResponse("Especialidad no encontrada",
                        false, null);
            }
            especialidadRepository.deleteById(id);
            return new MensajeResponse("Especialidad eliminada correctamente",
                    true, null);
        } catch (Exception e) {
            return new MensajeResponse("Error al eliminar especialidad: "
                    + e.getMessage(), false, null);
        }
    }

    private EspecialidadResponse mapToResponse(Especialidad e) {
        return new EspecialidadResponse(
                e.getId(),
                e.getCodigo(),
                e.getNombre(),
                e.getDescripcion()
        );
    }
}