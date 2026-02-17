# Backend - Sistema de Gestión de Citas Médicas

Sistema desarrollado con Spring Boot para gestionar citas médicas, caso de estudio de práctica

## 🚀 Tecnologías
- Java 21
- Spring Boot 3.2.2
- MySQL 8
- Spring Security + JWT
- Maven
- JPA/Hibernate

## 📋 Características
- Autenticación con JWT
- CRUD completo de Pacientes, Especialidades y Citas
- Arquitectura MVC
- Validaciones con Spring Validation
- Seguridad con Spring Security

## 📦 Endpoints API

### Autenticación
```
POST /api/auth/login - Iniciar sesión
```

### Especialidades (requiere autenticación)
```
GET    /api/especialidades        - Listar todas
GET    /api/especialidades/{id}   - Buscar por ID
POST   /api/especialidades        - Crear nueva
PUT    /api/especialidades/{id}   - Actualizar
DELETE /api/especialidades/{id}   - Eliminar
```

### Pacientes (requiere autenticación)
```
GET    /api/pacientes        - Listar todos
GET    /api/pacientes/{id}   - Buscar por ID
POST   /api/pacientes        - Crear nuevo
PUT    /api/pacientes/{id}   - Actualizar
DELETE /api/pacientes/{id}   - Eliminar
```

### Citas (requiere autenticación)
```
GET    /api/citas        - Listar todas
GET    /api/citas/{id}   - Buscar por ID
POST   /api/citas        - Crear nueva
PUT    /api/citas/{id}   - Actualizar
DELETE /api/citas/{id}   - Eliminar
```

## ⚙️ Configuración e Instalación

### Prerrequisitos
- JDK 21
- MySQL 8
- Maven

### Base de Datos
1. Crear la base de datos:
```sql
CREATE DATABASE gestion_citas_db;
```

2. Ejecutar el script de tablas (ver archivo SQL en el proyecto)

### Configuración
1. Clonar el repositorio
2. Configurar `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_citas_db
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
```

### Ejecución
```bash
mvn clean install
mvn spring-boot:run
```

El servidor iniciará en `http://localhost:8080`

## 🔐 Usuario de Prueba
```
Email: admin@sistema.com
Password: admin123
```
Este proyecto es parte de un trabajo académico.
