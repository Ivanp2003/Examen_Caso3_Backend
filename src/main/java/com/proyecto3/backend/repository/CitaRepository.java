package com.proyecto3.backend.repository;

import com.proyecto3.backend.model.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    List<Cita> findByPacienteId(Integer pacienteId);

    List<Cita> findByEspecialidadId(Integer especialidadId);
}
