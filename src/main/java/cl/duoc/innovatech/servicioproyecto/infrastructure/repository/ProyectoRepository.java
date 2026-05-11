package cl.duoc.innovatech.servicioproyecto.infrastructure.repository;

import cl.duoc.innovatech.servicioproyecto.domain.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    List<Proyecto> findByEstado(String estado);
}