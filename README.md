# Innovatech Solutions - MS Gestión de Proyectos

Microservicio desarrollado en Spring Boot encargado del ciclo de vida, persistencia y control de estados de los proyectos dentro de la plataforma Innovatech, así como de la vinculación lógica de los recursos asociados.

## Resumen Técnico

- **Nombre del Módulo:** `innovatech-ms-gestion-proyectos` (Contenedor: `ms-gestion-proyectos`)
- **Tecnologías Core:** Java 17, Spring Boot 3.x, Spring Data JPA, Hibernate, PostgreSQL.
- **Puerto Base (Host):** `8081` (Consumido internamente por el BFF a través de la red nativa de Docker).
- **Estrategia de Asociación:** Relación desacoplada mediante una colección de llaves primitivas (`Set<Long> recursoIds`) mapeada como `@ElementCollection` (tabla intermedia `proyecto_recursos`).

---

## Estructura de la Arquitectura

- `controller/ProyectoController.java` — Controlador REST que expone la API bajo la ruta base unificada `/api/v2/proyectos`.
- `service/` — Capa de negocio (interfaz `ProyectoService` e implementación `ProyectoServiceImpl`).
- `repository/ProyectoRepository.java` — Interfaz de persistencia para consultas JPA orientadas a PostgreSQL.
- `model/Proyecto.java` — Entidad de datos que mapea las propiedades (id, nombre, descripción, estado y el conjunto de identificadores de recursos).
- `dto/` — Objetos de transferencia de datos con validaciones integradas (`ProyectoRequestDTO` y `ProyectoResponseDTO`).

---

## Contratos de API (Endpoints)

**Ruta Base:** `/api/v2/proyectos`

### Operaciones CRUD Base

| Método | Endpoint | Descripción | Respuesta |
|---------|----------|-------------|------------|
| `GET` | `/api/v2/proyectos` | Recupera la lista completa de proyectos. | `200 OK` |
| `GET` | `/api/v2/proyectos/{id}` | Recupera el detalle de un proyecto por ID. | `200 OK` |
| `POST` | `/api/v2/proyectos` | Crea y persiste un nuevo proyecto. | `201 Created` |
| `PUT` | `/api/v2/proyectos/{id}` | Modifica los campos básicos de un proyecto. | `200 OK` |
| `DELETE` | `/api/v2/proyectos/{id}` | Ejecuta la eliminación física o lógica del registro. | `204 No Content` |

### Operaciones de Asignación y Orquestación

#### Obtener recursos asociados

```http
GET /api/v2/proyectos/{id}/recursos-ids
```

Retorna una lista numérica con los identificadores de recursos asociados al proyecto.

#### Actualizar estado interno

```http
PUT /api/v2/proyectos/{id}/estado-interno?estado={VALOR}
```

Estados permitidos:

- `EN_PLANIFICACION`
- `EN_PROGRESO`
- `FINALIZADO`

#### Vincular recurso

```http
POST /api/v2/proyectos/{id}/vincular?recursoId={ID}
```

Agrega un recurso al conjunto de recursos asociados al proyecto.

#### Desvincular recurso

```http
DELETE /api/v2/proyectos/{id}/desvincular?recursoId={ID}
```

Elimina un recurso asociado y retorna:

```http
204 No Content
```

---

## Estructura de Payloads

### Crear Proyecto

**Solicitud**

```json
{
  "nombre": "Sistema de Gestión Aduanera",
  "descripcion": "Proyecto para modernizar y agilizar procesos fronterizos en Chile",
  "estado": "EN_PLANIFICACION"
}
```

### Respuesta Exitosa

```json
{
  "id": 1,
  "nombre": "Sistema de Gestión Aduanera",
  "descripcion": "Proyecto para modernizar y agilizar procesos fronterizos en Chile",
  "estado": "EN_PLANIFICACION"
}
```

### Validaciones

Las solicitudes son validadas utilizando Jakarta Validation mediante:

- `@NotBlank`
- `@Size`

Las solicitudes inválidas retornan:

```http
400 Bad Request
```

---

## Parámetros de Configuración

Configure las siguientes variables de entorno para conectar correctamente el microservicio con PostgreSQL.

```properties
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-db:5432/innovatech_db
SPRING_DATASOURCE_USERNAME=admin
SPRING_DATASOURCE_PASSWORD=supersecretpassword
SERVER_PORT=8081
```

---

# Instrucciones de Ejecución

## Opción 1: Desarrollo Local (Maven Wrapper)

```bash
cd innovatech-ms-gestion-proyectos

./mvnw spring-boot:run
```

---

## Opción 2: Empaquetado y Docker

### Compilar aplicación

```bash
./mvnw clean package -DskipTests
```

### Construir imagen Docker

```bash
docker build -t innovatech-ms-gestion-proyectos .
```

### Ejecutar contenedor

```bash
docker run -p 8081:8081 \
  -e SPRING_DATASOURCE_URL="jdbc:postgresql://host.docker.internal:5432/innovatech_db" \
  -e SPRING_DATASOURCE_USERNAME="admin" \
  -e SPRING_DATASOURCE_PASSWORD="supersecretpassword" \
  innovatech-ms-gestion-proyectos
```

---

# Estrategia de Testing

Las pruebas unitarias y de integración permiten validar:

- Reglas de negocio.
- Operaciones CRUD.
- Transiciones de estado.
- Gestión de recursos asociados.
- Persistencia JPA y transacciones.

### Ejecutar pruebas

```bash
./mvnw test
```