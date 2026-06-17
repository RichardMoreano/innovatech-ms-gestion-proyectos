package cl.duoc.innovatech.servicioproyecto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v2/proyectos")
public class ProyectoController {

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listarProyectos() {
        List<Map<String, Object>> proyectos = new ArrayList<>();
        
        // Datos mockeados de prueba para validar que el circuito completo funciona
        Map<String, Object> p1 = Map.of(
            "id", 1,
            "nombre", "Plataforma Core V2",
            "descripcion", "Migración completa",
            "estado", "EN_PROGRESO"
        );
        proyectos.add(p1);
        
        return ResponseEntity.ok(proyectos);
    }
}