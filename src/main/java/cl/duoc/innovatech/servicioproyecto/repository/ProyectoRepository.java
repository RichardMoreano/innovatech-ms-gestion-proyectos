package cl.duoc.innovatech.servicioproyecto.repository;

import cl.duoc.innovatech.servicioproyecto.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}