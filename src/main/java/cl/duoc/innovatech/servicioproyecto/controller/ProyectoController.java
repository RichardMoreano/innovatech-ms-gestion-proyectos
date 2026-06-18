package cl.duoc.innovatech.servicioproyecto.controller;

import cl.duoc.innovatech.servicioproyecto.dto.ProyectoRequestDTO;
import cl.duoc.innovatech.servicioproyecto.dto.ProyectoResponseDTO;
import cl.duoc.innovatech.servicioproyecto.service.ProyectoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/proyectos")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public ResponseEntity<List<ProyectoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(proyectoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(proyectoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProyectoResponseDTO> crear(@Valid @RequestBody ProyectoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proyectoService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoResponseDTO> actualizar(
            @PathVariable Long id, 
            @Valid @RequestBody ProyectoRequestDTO request) {
        return ResponseEntity.ok(proyectoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/recursos-ids")
    public ResponseEntity<List<Long>> obtenerRecursosIdsPorProyecto(@PathVariable Long id) {
        return ResponseEntity.ok(proyectoService.obtenerRecursosIds(id));
    }

    @PutMapping("/{id}/estado-interno")
    public ResponseEntity<ProyectoResponseDTO> actualizarEstadoInterno(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(proyectoService.actualizarEstadoInterno(id, estado));
    }

    @PostMapping("/{id}/vincular")
    public ResponseEntity<Void> vincularRecurso(@PathVariable Long id, @RequestParam Long recursoId) {
        proyectoService.vincularRecurso(id, recursoId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/desvincular")
    public ResponseEntity<Void> desvincularRecurso(@PathVariable Long id, @RequestParam Long recursoId) {
        proyectoService.desvincularRecurso(id, recursoId);
        return ResponseEntity.noContent().build();
    }
}