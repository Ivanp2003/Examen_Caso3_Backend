package com.proyecto3.backend.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 20, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 20, nullable = false)
    private String apellido;

    @Column(name = "cedula", length = 20, nullable = false, unique = true)
    private String cedula;

    @Column(name = "fecha_nacimiento", length = 20)
    private String fechaNacimiento;

    @Column(name = "genero", length = 20)
    private String genero;

    @Column(name = "ciudad", length = 20)
    private String ciudad;

    @Column(name = "direccion", length = 20)
    private String direccion;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", length = 20)
    private String email;
}