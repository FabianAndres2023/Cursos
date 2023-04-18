package co.edu.uceva.cursos_service.controller;

import co.edu.uceva.cursos_service.model.entities.Curso;
import co.edu.uceva.cursos_service.model.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/curso-service")

public class CursoRestController {

    @Autowired    // Crea un contenedor sin necesidad de hacer un construcctor
    ICursoService cursoService;


    /**
     * Endpoint para recibir un saludo
     * @param nombre_curso Es el nombre que envian desde la url
     * @return Un saludo
     */

    @GetMapping("/hola/{nombre_curso}")
    public String holaMundo(@PathVariable("nombre_curso") String nombre_curso){
        return "Hola "+ nombre_curso;
    }

    @GetMapping("/cursos")
    public List<Curso> Listar(){
        cursoService.findAll();
        return  cursoService.findAll();
    }

    @GetMapping("/cursos/{id_curso}")
    public Curso buscarCursoPorId(@PathVariable("id_curso") long id_curso  ){
        return cursoService.findById(id_curso);

    }

    @PostMapping("/cursos")
    public Curso crearCurso(@RequestBody Curso curso){
        return cursoService.save(curso);

    }

    @DeleteMapping("/cursos/{id_curso}")
    public void borrarCurso(@PathVariable("id_curso") long id_curso ) {
        Curso curso;
        curso = cursoService.findById(id_curso); // Busca Curso
        cursoService.delete(curso);

    }

    @PutMapping("/cursos")
    public Curso actualizarCurso(@RequestBody Curso curso) {
        return cursoService.update(curso);
    }

}