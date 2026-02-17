package com.proyecto3.backend.service.impl;

import com.proyecto3.backend.dto.request.PacienteRequest;
import com.proyecto3.backend.dto.response.MensajeResponse;
import com.proyecto3.backend.dto.response.PacienteResponse;
import com.proyecto3.backend.model.entity.Paciente;
import com.proyecto3.backend.repository.PacienteRepository;
import com.proyecto3.backend.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public List<PacienteResponse> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PacienteResponse buscarPorId(Integer id) {
        return pacienteRepository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    @Override
    public MensajeResponse guardar(PacienteRequest request) {
        try {
            if (pacienteRepository.existsByCedula(request.getCedula())) {
                return new MensajeResponse("Ya existe un paciente con esa cédula",
                        false, null);
            }
            Paciente paciente = mapToEntity(request);
            pacienteRepository.save(paciente);
            return new MensajeResponse("Paciente guardado correctamente",
                    true, null);
        } catch (Exception e) {
            return new MensajeResponse("Error al guardar paciente: "
                    + e.getMessage(), false, null);
        }
    }

    @Override
    public MensajeResponse actualizar(Integer id, PacienteRequest request) {
        try {
            Optional<Paciente> opt = pacienteRepository.findById(id);
            if (opt.isEmpty()) {
                return new MensajeResponse("Paciente no encontrado",
                        false, null);
            }
            Paciente paciente = opt.get();
            paciente.setNombre(request.getNombre());
            paciente.setApellido(request.getApellido());
            paciente.setCedula(request.getCedula());
            paciente.setFechaNacimiento(request.getFechaNacimiento());
            paciente.setGenero(request.getGenero());
            paciente.setCiudad(request.getCiudad());
            paciente.setDireccion(request.getDireccion());
            paciente.setTelefono(request.getTelefono());
            paciente.setEmail(request.getEmail());
            pacienteRepository.save(paciente);
            return new MensajeResponse("Paciente actualizado correctamente",
                    true, null);
        } catch (Exception e) {
            return new MensajeResponse("Error al actualizar paciente: "
                    + e.getMessage(), false, null);
        }
    }

    @Override
    public MensajeResponse eliminar(Integer id) {
        try {
            if (!pacienteRepository.existsById(id)) {
                return new MensajeResponse("Paciente no encontrado",
                        false, null);
            }
            pacienteRepository.deleteById(id);
            return new MensajeResponse("Paciente eliminado correctamente",
                    true, null);
        } catch (Exception e) {
            return new MensajeResponse("Error al eliminar paciente: "
                    + e.getMessage(), false, null);
        }
    }

    private Paciente mapToEntity(PacienteRequest request) {
        Paciente paciente = new Paciente();
        paciente.setNombre(request.getNombre());
        paciente.setApellido(request.getApellido());
        paciente.setCedula(request.getCedula());
        paciente.setFechaNacimiento(request.getFechaNacimiento());
        paciente.setGenero(request.getGenero());
        paciente.setCiudad(request.getCiudad());
        paciente.setDireccion(request.getDireccion());
        paciente.setTelefono(request.getTelefono());
        paciente.setEmail(request.getEmail());
        return paciente;
    }

    private PacienteResponse mapToResponse(Paciente p) {
        return new PacienteResponse(
                p.getId(),
                p.getNombre(),
                p.getApellido(),
                p.getCedula(),
                p.getFechaNacimiento(),
                p.getGenero(),
                p.getCiudad(),
                p.getDireccion(),
                p.getTelefono(),
                p.getEmail()
        );
    }
}