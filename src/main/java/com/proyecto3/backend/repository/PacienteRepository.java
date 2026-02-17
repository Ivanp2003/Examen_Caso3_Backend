package com.proyecto3.backend.repository;

import com.proyecto3.backend.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    boolean existsByCedula(String cedula);
}