package cl.duoc.innovatech.servicioproyecto.service;

import cl.duoc.innovatech.servicioproyecto.dto.ProyectoRequestDTO;
import cl.duoc.innovatech.servicioproyecto.dto.ProyectoResponseDTO;
import java.util.List;

public interface ProyectoService {
    List<ProyectoResponseDTO> obtenerTodos();
    ProyectoResponseDTO obtenerPorId(Long id);
    ProyectoResponseDTO crear(ProyectoRequestDTO request);
    ProyectoResponseDTO actualizar(Long id, ProyectoRequestDTO request);
    void eliminar(Long id);
}