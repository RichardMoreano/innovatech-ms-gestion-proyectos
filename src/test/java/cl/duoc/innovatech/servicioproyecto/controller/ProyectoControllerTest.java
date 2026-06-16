package cl.duoc.innovatech.servicioproyecto.controller;

import cl.duoc.innovatech.servicioproyecto.application.service.ProyectoService;
import cl.duoc.innovatech.servicioproyecto.infrastructure.controller.ProyectoController;
import cl.duoc.innovatech.servicioproyecto.application.dto.ProyectoRequest;
import cl.duoc.innovatech.servicioproyecto.application.dto.ProyectoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProyectoControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("testCrearProyectoExitoso")
    void testCrearProyectoExitoso() throws Exception {
        ProyectoService service = Mockito.mock(ProyectoService.class);
        // el servicio simulado devuelve un ProyectoResponse cuando se invoca crear
        when(service.crear(Mockito.any(ProyectoRequest.class))).thenReturn(
                ProyectoResponse.builder().id(1L).nombre("Proyecto Test").descripcion("Descripcion").build()
        );

        ProyectoController controller = new ProyectoController(service);
        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

        ProyectoRequest req = new ProyectoRequest();
    req.setNombre("Proyecto Test");
    req.setDescripcion("Descripcion");
    req.setResponsableId(10L);

        mvc.perform(post("/api/proyectos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("testObtenerProyectoPorIdExitoso")
    void testObtenerProyectoPorIdExitoso() throws Exception {
        ProyectoService service = Mockito.mock(ProyectoService.class);
        when(service.obtenerPorId(1L)).thenReturn(ProyectoResponse.builder().id(1L).nombre("P1").build());

        ProyectoController controller = new ProyectoController(service);
        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/proyectos/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("testObtenerProyectoNoEncontradoRetornaCuatroCientosCuatro")
    void testObtenerProyectoNoEncontradoRetornaCuatroCientosCuatro() throws Exception {
        ProyectoService service = Mockito.mock(ProyectoService.class);
    when(service.obtenerPorId(2L)).thenThrow(new cl.duoc.innovatech.servicioproyecto.application.exception.ProyectoNotFoundException(2L));

    ProyectoController controller = new ProyectoController(service);
    MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

    mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/proyectos/2"))
        .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("testActualizarProyectoExitoso")
    void testActualizarProyectoExitoso() throws Exception {
        ProyectoService service = Mockito.mock(ProyectoService.class);
        ProyectoRequest req = new ProyectoRequest();
        req.setNombre("P actualizado");
        req.setDescripcion("desc");
        req.setResponsableId(5L);

        when(service.actualizar(Mockito.eq(1L), Mockito.any(ProyectoRequest.class))).thenReturn(ProyectoResponse.builder().id(1L).nombre("P actualizado").build());

        ProyectoController controller = new ProyectoController(service);
        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/api/proyectos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(req)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("testActualizarProyectoNoEncontradoRetornaCuatroCientosCuatro")
    void testActualizarProyectoNoEncontradoRetornaCuatroCientosCuatro() throws Exception {
        ProyectoService service = Mockito.mock(ProyectoService.class);
        ProyectoRequest req = new ProyectoRequest();
        req.setNombre("P actualizado");

    when(service.actualizar(Mockito.eq(99L), Mockito.any(ProyectoRequest.class))).thenThrow(new cl.duoc.innovatech.servicioproyecto.application.exception.ProyectoNotFoundException(99L));

    ProyectoController controller = new ProyectoController(service);
    MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

    mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put("/api/proyectos/99")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(req)))
        .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("testEliminarProyectoExitoso")
    void testEliminarProyectoExitoso() throws Exception {
        ProyectoService service = Mockito.mock(ProyectoService.class);
        // eliminar no devuelve nada
        ProyectoController controller = new ProyectoController(service);
        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/proyectos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("testEliminarProyectoNoEncontradoRetornaCuatroCientosCuatro")
    void testEliminarProyectoNoEncontradoRetornaCuatroCientosCuatro() throws Exception {
        ProyectoService service = Mockito.mock(ProyectoService.class);
    Mockito.doThrow(new cl.duoc.innovatech.servicioproyecto.application.exception.ProyectoNotFoundException(50L)).when(service).eliminar(50L);

    ProyectoController controller = new ProyectoController(service);
    MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

    mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("/api/proyectos/50"))
        .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("testListarPorEstadoExitoso")
    void testListarPorEstadoExitoso() throws Exception {
        ProyectoService service = Mockito.mock(ProyectoService.class);
        when(service.listarPorEstado("PENDIENTE")).thenReturn(java.util.List.of(ProyectoResponse.builder().id(1L).nombre("P1").build()));

        ProyectoController controller = new ProyectoController(service);
        MockMvc mvc = MockMvcBuilders.standaloneSetup(controller).build();

        mvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get("/api/proyectos/estado/PENDIENTE"))
                .andExpect(status().isOk());
    }
}
