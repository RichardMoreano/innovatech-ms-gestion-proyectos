package cl.duoc.innovatech.servicioproyecto.service.impl;

import cl.duoc.innovatech.servicioproyecto.dto.ProyectoRequestDTO;
import cl.duoc.innovatech.servicioproyecto.dto.ProyectoResponseDTO;
import cl.duoc.innovatech.servicioproyecto.model.Proyecto;
import cl.duoc.innovatech.servicioproyecto.repository.ProyectoRepository;
import cl.duoc.innovatech.servicioproyecto.service.ProyectoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public List<ProyectoResponseDTO> obtenerTodos() {
        return proyectoRepository.findAll().stream()
                .map(this::mapearAResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProyectoResponseDTO obtenerPorId(Long id) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con el ID: " + id));
        return mapearAResponse(proyecto);
    }

    @Override
    public ProyectoResponseDTO crear(ProyectoRequestDTO request) {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(request.getNombre());
        proyecto.setDescripcion(request.getDescripcion());
        proyecto.setEstado(request.getEstado());
        
        Proyecto guardado = proyectoRepository.save(proyecto);
        return mapearAResponse(guardado);
    }

    @Override
    public ProyectoResponseDTO actualizar(Long id, ProyectoRequestDTO request) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con el ID: " + id));
        
        proyecto.setNombre(request.getNombre());
        proyecto.setDescripcion(request.getDescripcion());
        proyecto.setEstado(request.getEstado());
        
        Proyecto actualizado = proyectoRepository.save(proyecto);
        return mapearAResponse(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con el ID: " + id));
        proyectoRepository.delete(proyecto);
    }

    private ProyectoResponseDTO mapearAResponse(Proyecto proyecto) {
        return new ProyectoResponseDTO(
                proyecto.getId(),
                proyecto.getNombre(),
                proyecto.getDescripcion(),
                proyecto.getEstado()
        );
    }
}