package co.edu.uceva.cursos_service.model.service;

import co.edu.uceva.cursos_service.model.dao.ICursoDao;
import co.edu.uceva.cursos_service.model.dao.ICursoDao;
import co.edu.uceva.cursos_service.model.entities.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements ICursoService{
    @Autowired
    ICursoDao cursoDao;

    @Override
    public Curso save(Curso curso) {
        return cursoDao.save(curso);
    }
    @Override
    public void delete(Curso curso) {
        cursoDao.delete(curso);

    }

    @Override
    public Curso update(Curso curso) {
        return cursoDao.save(curso);

    }


    /**
     * Este metodo lista los paises guardados
     * @return una lista de paises
     */

    @Override
    public List<Curso> findAll() {
        return(List<Curso>) cursoDao.findAll();
    }

    @Override
    public Curso findById(Long id) {
        return (Curso)cursoDao.findById(id).get();

    }

}
