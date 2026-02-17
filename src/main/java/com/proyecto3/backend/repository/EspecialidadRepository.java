package com.proyecto3.backend.repository;

import com.proyecto3.backend.model.entity.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {

    boolean existsByCodigo(String codigo);
}