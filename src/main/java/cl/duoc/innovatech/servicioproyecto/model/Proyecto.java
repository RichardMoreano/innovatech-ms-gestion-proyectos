package cl.duoc.innovatech.servicioproyecto.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "proyectos")
@Data
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
    private String estado;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "proyecto_recursos", joinColumns = @JoinColumn(name = "proyecto_id"))
    @Column(name = "recurso_id")
    private Set<Long> recursoIds = new HashSet<>();
    
    public Set<Long> getRecursoIds() {
        if (this.recursoIds == null) {
            this.recursoIds = new HashSet<>();
        }
        return this.recursoIds;
    }
}