package cl.duoc.innovatech.servicioproyecto.application.exception;

public class ProyectoNotFoundException extends RuntimeException {
    public ProyectoNotFoundException(Long id) {
        super("Proyecto no encontrado: " + id);
    }
}
