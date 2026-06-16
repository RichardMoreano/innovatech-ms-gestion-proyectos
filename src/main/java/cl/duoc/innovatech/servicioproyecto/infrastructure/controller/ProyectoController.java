package cl.duoc.innovatech.servicioproyecto.infrastructure.controller;

import cl.duoc.innovatech.servicioproyecto.application.dto.ProyectoRequest;
import cl.duoc.innovatech.servicioproyecto.application.dto.ProyectoResponse;
import cl.duoc.innovatech.servicioproyecto.application.service.ProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.innovatech.servicioproyecto.application.exception.ProyectoNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;

    @PostMapping
    public ResponseEntity<ProyectoResponse> crear(@RequestBody ProyectoRequest request) {
        ProyectoResponse response = proyectoService.crear(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProyectoResponse>> listarTodos() {
        return ResponseEntity.ok(proyectoService.listarTodos());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ProyectoResponse>> listarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(proyectoService.listarPorEstado(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoResponse> obtenerPorId(@PathVariable Long id) {
        ProyectoResponse resp = proyectoService.obtenerPorId(id);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoResponse> actualizar(@PathVariable Long id, @RequestBody ProyectoRequest request) {
        ProyectoResponse resp = proyectoService.actualizar(id, request);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        proyectoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ProyectoNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ProyectoNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}