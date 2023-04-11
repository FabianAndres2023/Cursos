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
     * @param nombre Es el nombre que envian desde la url
     * @return Un saludo
     */

    @GetMapping("/hola/{nombre}")
    public String holaMundo(@PathVariable("nombre") String nombre){
        return "Hola "+ nombre;
    }

    @GetMapping("/cursos")
    public List<Curso> Listar(){
        cursoService.findAll();
        return  cursoService.findAll();
    }

    @GetMapping("/cursos/{id}")
    public Curso buscarCurso(@PathVariable("id") long id  ){
        return cursoService.findById(id);

    }

    @PostMapping("/cursos")
    public Curso crearCurso(@RequestBody Curso curso){
        return cursoService.save(curso);

    }

    @DeleteMapping("/cursos/{id}")
    public void borrarCurso(@PathVariable("id") long id ) {
        Curso curso;
        curso = cursoService.findById(id); // Busca Curso
        cursoService.delete(curso);

    }

    @PutMapping("/cursos")
    public Curso actualizarCurso(@RequestBody Curso curso) {
        return cursoService.update(curso);
    }

}