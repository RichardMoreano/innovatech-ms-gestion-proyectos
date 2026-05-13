# Innovatech Servicio Proyecto

Microservicio de Gestión de Proyectos - Innovatech Solutions

## Tecnologías
- Spring Boot 4.0.6
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

## Ejemplos de Uso

```http
POST /api/proyectos
```

**Request Body (JSON):**

```json
{
  "nombre": "Sistema de Gestión Aduanera",
  "descripcion": "Proyecto para modernizar y agilizar procesos fronterizos en Chile",
  "responsableId": 1
}
```

### otro Proyecto

```json
{
  "nombre": "Innovatech ERP",
  "descripcion": "Sistema de gestión empresarial para control interno",
  "responsableId": 2
}
```
## Cómo ejecutar

```bash
./mvnw spring-boot:run