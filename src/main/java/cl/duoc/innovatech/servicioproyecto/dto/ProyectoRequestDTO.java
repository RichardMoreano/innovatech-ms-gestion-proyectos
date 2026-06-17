package cl.duoc.innovatech.servicioproyecto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProyectoRequestDTO {

    @NotBlank(message = "El nombre del proyecto no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres.")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres.")
    private String descripcion;

    @NotBlank(message = "El estado del proyecto es obligatorio.")
    @Size(max = 50, message = "El estado no puede superar los 50 caracteres.")
    private String estado;
}