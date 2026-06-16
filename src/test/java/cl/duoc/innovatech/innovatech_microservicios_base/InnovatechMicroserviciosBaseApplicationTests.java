package cl.duoc.innovatech.innovatech_microservicios_base;

import cl.duoc.innovatech.servicioproyecto.ServicioProyectoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = ServicioProyectoApplication.class)
@TestPropertySource(properties = {
		"spring.datasource.url=jdbc:h2:mem:testdb",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=SA",
		"spring.datasource.password=",
		"spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
		"spring.jpa.hibernate.ddl-auto=create-drop"
})
class InnovatechMicroserviciosBaseApplicationTests {

	@Test
	void contextLoads() {
	}

}
