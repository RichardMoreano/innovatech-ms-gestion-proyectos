package cl.duoc.innovatech.servicioproyecto.application.service;

import cl.duoc.innovatech.servicioproyecto.application.dto.ProyectoRequest;
import cl.duoc.innovatech.servicioproyecto.application.dto.ProyectoResponse;
import cl.duoc.innovatech.servicioproyecto.domain.entity.Proyecto;
import cl.duoc.innovatech.servicioproyecto.domain.factory.ProyectoFactory;
import cl.duoc.innovatech.servicioproyecto.infrastructure.repository.ProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import cl.duoc.innovatech.servicioproyecto.application.exception.ProyectoNotFoundException;

@Service
@RequiredArgsConstructor
public class ProyectoService {

    private final ProyectoRepository repository;
    private final ProyectoFactory factory;

    public ProyectoResponse crear(ProyectoRequest request) {
        Proyecto proyecto = factory.crearProyecto(
                request.getNombre(),
                request.getDescripcion(),
                request.getResponsableId()
        );
        Proyecto guardado = repository.save(proyecto);
        return mapToResponse(guardado);
    }

    public List<ProyectoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ProyectoResponse> listarPorEstado(String estado) {
        return repository.findByEstado(estado).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ProyectoResponse obtenerPorId(Long id) {
        Proyecto proyecto = repository.findById(id)
                .orElseThrow(() -> new ProyectoNotFoundException(id));
        return mapToResponse(proyecto);
    }

    public ProyectoResponse actualizar(Long id, ProyectoRequest request) {
        Proyecto existente = repository.findById(id)
                .orElseThrow(() -> new ProyectoNotFoundException(id));

        existente.setNombre(request.getNombre());
        existente.setDescripcion(request.getDescripcion());
        existente.setResponsableId(request.getResponsableId());

        Proyecto actualizado = repository.save(existente);
        return mapToResponse(actualizado);
    }

    public void eliminar(Long id) {
        Proyecto existente = repository.findById(id)
                .orElseThrow(() -> new ProyectoNotFoundException(id));
        repository.delete(existente);
    }

    private ProyectoResponse mapToResponse(Proyecto proyecto) {
        return ProyectoResponse.builder()
                .id(proyecto.getId())
                .nombre(proyecto.getNombre())
                .descripcion(proyecto.getDescripcion())
                .estado(proyecto.getEstado())
                .fechaInicio(proyecto.getFechaInicio())
                .fechaFin(proyecto.getFechaFin())
                .responsableId(proyecto.getResponsableId())
                .build();
    }
}