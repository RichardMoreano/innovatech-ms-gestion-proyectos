package cl.duoc.innovatech.servicioproyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String estado;
}