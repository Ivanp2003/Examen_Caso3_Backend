package com.proyecto3.backend.service.impl;

import com.proyecto3.backend.dto.request.CitaRequest;
import com.proyecto3.backend.dto.response.CitaResponse;
import com.proyecto3.backend.dto.response.MensajeResponse;
import com.proyecto3.backend.model.entity.Cita;
import com.proyecto3.backend.model.entity.Especialidad;
import com.proyecto3.backend.model.entity.Paciente;
import com.proyecto3.backend.repository.CitaRepository;
import com.proyecto3.backend.repository.EspecialidadRepository;
import com.proyecto3.backend.repository.PacienteRepository;
import com.proyecto3.backend.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Override
    public List<CitaResponse> listarTodos() {
        return citaRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CitaResponse buscarPorId(Integer id) {
        return citaRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public MensajeResponse guardar(CitaRequest request) {
        try {
            Optional<Paciente> pacienteOpt =
                    pacienteRepository.findById(request.getPacienteId());
            Optional<Especialidad> especialidadOpt =
                    especialidadRepository.findById(request.getEspecialidadId());

            if (pacienteOpt.isEmpty()) {
                return new MensajeResponse("Paciente no encontrado",
                        false, null);
            }
            if (especialidadOpt.isEmpty()) {
                return new MensajeResponse("Especialidad no encontrada",
                        false, null);
            }

            Cita cita = new Cita();
            cita.setCodigo(request.getCodigo());
            cita.setDescripcion(request.getDescripcion());
            cita.setPaciente(pacienteOpt.get());
            cita.setEspecialidad(especialidadOpt.get());
            citaRepository.save(cita);

            return new MensajeResponse("Cita guardada correctamente",
                    true, null);
        } catch (Exception e) {
            return new MensajeResponse("Error al guardar cita: "
                    + e.getMessage(), false, null);
        }
    }

    @Override
    public MensajeResponse actualizar(Integer id, CitaRequest request) {
        try {
            Optional<Cita> citaOpt = citaRepository.findById(id);
            if (citaOpt.isEmpty()) {
                return new MensajeResponse("Cita no encontrada",
                        false, null);
            }

            Optional<Paciente> pacienteOpt =
                    pacienteRepository.findById(request.getPacienteId());
            Optional<Especialidad> especialidadOpt =
                    especialidadRepository.findById(request.getEspecialidadId());

            if (pacienteOpt.isEmpty()) {
                return new MensajeResponse("Paciente no encontrado",
                        false, null);
            }
            if (especialidadOpt.isEmpty()) {
                return new MensajeResponse("Especialidad no encontrada",
                        false, null);
            }

            Cita cita = citaOpt.get();
            cita.setCodigo(request.getCodigo());
            cita.setDescripcion(request.getDescripcion());
            cita.setPaciente(pacienteOpt.get());
            cita.setEspecialidad(especialidadOpt.get());
            citaRepository.save(cita);

            return new MensajeResponse("Cita actualizada correctamente",
                    true, null);
        } catch (Exception e) {
            return new MensajeResponse("Error al actualizar cita: "
                    + e.getMessage(), false, null);
        }
    }

    @Override
    public MensajeResponse eliminar(Integer id) {
        try {
            if (!citaRepository.existsById(id)) {
                return new MensajeResponse("Cita no encontrada",
                        false, null);
            }
            citaRepository.deleteById(id);
            return new MensajeResponse("Cita eliminada correctamente",
                    true, null);
        } catch (Exception e) {
            return new MensajeResponse("Error al eliminar cita: "
                    + e.getMessage(), false, null);
        }
    }

    private CitaResponse mapToResponse(Cita c) {
        return new CitaResponse(
                c.getId(),
                c.getCodigo(),
                c.getDescripcion(),
                c.getPaciente().getId(),
                c.getPaciente().getNombre(),
                c.getPaciente().getApellido(),
                c.getEspecialidad().getId(),
                c.getEspecialidad().getNombre()
        );
    }
}
