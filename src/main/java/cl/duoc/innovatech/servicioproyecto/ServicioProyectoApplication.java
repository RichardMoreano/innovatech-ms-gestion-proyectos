package cl.duoc.innovatech.servicioproyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioProyectoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicioProyectoApplication.class, args);
    }
}