package cl.duoc.innovatech.servicioproyecto.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "proyectos")
@Data
@NoTitle
@NoArgsConstructor
@AllArgsConstructor
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false, length = 50)
    private String estado; // Ejemplo: "PLANIFICADO", "EN_PROGRESO", "FINALIZADO"
}