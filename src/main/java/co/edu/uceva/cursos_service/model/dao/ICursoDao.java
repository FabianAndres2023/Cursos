package co.edu.uceva.cursos_service.model.dao;

import co.edu.uceva.cursos_service.model.entities.Curso;
import org.springframework.data.repository.CrudRepository;

public interface ICursoDao extends CrudRepository<Curso, Long> {

}
