package co.edu.uceva.cursos_service.model.service;

import co.edu.uceva.cursos_service.model.entities.Curso;

import java.util.List;

public interface ICursoService {
    Curso save(Curso curso);
    void delete(Curso curso);
    Curso update(Curso curso);
    List<Curso> findAll();
    Curso findById(Long id_curso);
}
