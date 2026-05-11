package cl.duoc.innovatech.servicioproyecto.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoRequest {
    private String nombre;
    private String descripcion;
    private Long responsableId;
}
