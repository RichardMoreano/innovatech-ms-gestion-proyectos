package cl.duoc.innovatech.servicioproyecto.domain.factory;

import cl.duoc.innovatech.servicioproyecto.domain.entity.Proyecto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProyectoFactory {

    public Proyecto crearProyecto(String nombre, String descripcion, Long responsableId) {
        return Proyecto.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .estado("PENDIENTE")
                .fechaInicio(LocalDateTime.now())
                .responsableId(responsableId)
                .build();
    }
}