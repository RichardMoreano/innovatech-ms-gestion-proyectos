# Innovatech Project Service

Microservicio de Gestión de Proyectos - Innovatech Solutions

## Tecnologías
- Spring Boot 3.3
- Java 25
- JPA + PostgreSQL
- JWT Authentication
- Lombok

## Endpoints Principales

| Método | Endpoint                    | Descripción                    |
|--------|-----------------------------|--------------------------------|
| POST   | `/api/proyectos`            | Crear nuevo proyecto           |
| GET    | `/api/proyectos`            | Listar todos los proyectos     |
| GET    | `/api/proyectos/estado/{estado}` | Listar por estado         |

## Cómo ejecutar

```bash
./mvnw spring-boot:run