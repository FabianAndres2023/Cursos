package co.edu.uceva.cursos_service;

import co.edu.uceva.cursos_service.model.entities.Curso;
import co.edu.uceva.cursos_service.model.service.ICursoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CursoRestControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    /**
     *
     */
    @Autowired
    private ICursoService cursoService;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testHolaMundo() throws Exception {
        String nombre = "Juan";
        this.mockMvc.perform(get("/curso-service/hola/{nombre}", nombre))
                .andExpect(status().isOk())
                .andExpect(content().string("Hola " + nombre));
    }

    @Test
    public void testListar() throws Exception {
        Curso curso1 = new Curso();
        curso1.setNombre_curso("Croacia");
        Curso curso2 = new Curso();
        curso2.setNombre_curso("España");
        cursoService.save(curso1);
        cursoService.save(curso2);
        List<Curso> listaCursos = new ArrayList<>();
        listaCursos.add(curso1);
        listaCursos.add(curso2);

        this.mockMvc.perform(get("/curso-service/cursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is(curso1.getNombre_curso())))
                .andExpect(jsonPath("$[1].nombre", is(curso2.getNombre_curso())));

        cursoService.delete(curso1);
        cursoService.delete(curso2);
    }

    /**
     * Prueba del método GET "/curso-service/cursos/{id}", que comprueba que se recibe el país correcto en la respuesta.
     * @throws Exception
     */

    @Test
    public void testBuscarCurso() throws Exception {
        Curso curso = new Curso();
        curso.setNombre_curso("España");
        cursoService.save(curso);

        this.mockMvc.perform(get("/curso-service/cursos/{id}", curso.getId_curso()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is(curso.getNombre_curso())));

        cursoService.delete(curso);
    }

    /**
     * Prueba del método POST "/curso-service/cursos", que comprueba que se crea un nuevo país correctamente.
     * @throws Exception
     */

    @Test
    public void testCrearCurso() throws Exception {
        Curso curso = new Curso();
        curso.setNombre_curso("España");

        this.mockMvc.perform(post("/curso-service/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(curso)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is(curso.getNombre_curso())));

        cursoService.delete(curso);
    }

    /**
     * Prueba del método PUT "/curso-service/cursos", que comprueba que se actualiza un país correctamente.
     * @throws Exception
     */
    @Test
    public void testActualizarCurso() throws Exception {
        Curso curso = new Curso();
        curso.setNombre_curso("España");
        cursoService.save(curso);
        curso.setNombre_curso("Portugal");

        this.mockMvc.perform(put("/curso-service/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(curso)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is(curso.getNombre_curso())));
//                .andExpect(jsonPath("$.capital", is(curso.getNombre())));

        cursoService.delete(curso);
    }

    /**
     * Prueba del método DELETE "/curso-service/cursos/{id}", que comprueba que se elimina un país correctamente.
     * @throws Exception
     */
    @Test
    public void testBorrarCurso() throws Exception {
        Curso curso = new Curso();
        curso.setNombre_curso("Canada");
        cursoService.save(curso);

        this.mockMvc.perform(delete("/curso-service/cursos/{id}", curso.getId_curso()))
                .andExpect(status().isOk());

        assertNull(cursoService.findById(curso.getId_curso()));
    }

    /**
     * Método para convertir un objeto a una cadena JSON
     *
     * @param obj Objeto a convertir
     * @return Cadena JSON
     */
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
