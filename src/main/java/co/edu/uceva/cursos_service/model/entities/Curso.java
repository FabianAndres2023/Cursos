package co.edu.uceva.cursos_service.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="curso") //Mapear

public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id_curso;
    private String nombre_curso;
}


