package cl.duoc.innovatech.servicioproyecto.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "proyectos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private String estado; // PENDIENTE, EN_PROGRESO, FINALIZADO, CANCELADO

    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    private Long responsableId; // ID del usuario responsable
}