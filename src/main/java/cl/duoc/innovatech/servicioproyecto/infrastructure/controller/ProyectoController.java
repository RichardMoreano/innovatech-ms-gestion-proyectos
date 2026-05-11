package cl.duoc.innovatech.servicioproyecto.infrastructure.controller;

import cl.duoc.innovatech.servicioproyecto.application.dto.ProyectoRequest;
import cl.duoc.innovatech.servicioproyecto.application.dto.ProyectoResponse;
import cl.duoc.innovatech.servicioproyecto.application.service.ProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}