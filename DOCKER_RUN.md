# Guía de Ejecución con Docker - Microservicio de Gestión de Proyectos V2

Este componente provee las capacidades core de negocio y persistencia para la administración de proyectos, operando de manera aislada dentro de la red interna de Docker.

## 1. Prerrequisitos y Dependencias
* **Red de Docker:** `innovatech-net`.
* **Persistencia:** Depende directamente del contenedor `innovatech-db` (PostgreSQL) para la creación de tablas JPA y almacenamiento relacional.

## 2. Puertos y Mapeo de Red
* **Puerto Interno (Contenedor):** `8081`
* **Puerto Externo (Host):** `8081` (Accesible internamente por el BFF y expuesto para inspección o debugging local).

---

## 3. Comandos de Operación

### Despliegue y Construcción Limpia
```bash
docker compose up -d --build ms-gestion-proyectos