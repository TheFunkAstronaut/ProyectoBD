package com.ecodeup.jdbc.DAO;
import com.ecodeup.jdbc.Entidades.Estudiante;
import com.ecodeup.jdbc.Entidades.Materia;
import com.ecodeup.jdbc.Entidades.EstudianteEnMateria;
import java.util.List;
import java.sql.Date;


public class TestDAO {

    public static void main(String[] args) {

        // Prueba EstudianteDAO
        EstudianteDAO estudianteDAO = new EstudianteDAO();

        // Insertar Estudiante
        Estudiante nuevoEstudiante = new Estudiante(4, "Juan Carlos", "Pérez", "González", "Ingeniería de Sistemas", new java.util.Date());
        estudianteDAO.insertar(nuevoEstudiante);

        // Actualizar Estudiante
        Estudiante estudianteExistente = new Estudiante(1, "Juan", "Pérez", "González", "Ingeniería de Sistemas", new java.util.Date());
        estudianteDAO.actualizar(estudianteExistente);

        // Eliminar Estudiante
        estudianteDAO.eliminar(4);

        // Listar Estudiantes
        List<Estudiante> estudiantes = estudianteDAO.listar();
        System.out.println("Listado de Estudiantes:");
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante.getId() + " - " + estudiante.getNombre() + " " + estudiante.getApellidoPaterno());
        }

        // Prueba MateriaDAO
        MateriaDAO materiaDAO = new MateriaDAO();

        // Insertar Materia
        Materia nuevaMateria = new Materia(11, "Matemáticas Discretas", 4);
        materiaDAO.insertar(nuevaMateria);

        // Actualizar Materia
        Materia materiaExistente = new Materia(1, "Matemáticas", 5);
        materiaDAO.actualizar(materiaExistente);

        // Eliminar Materia
        materiaDAO.eliminar(11);

        // Listar Materias
        List<Materia> materias = materiaDAO.listar();
        System.out.println("\nListado de Materias:");
        for (Materia materia : materias) {
            System.out.println(materia.getId() + " - " + materia.getNombre() + " - " + materia.getNumCreditos() + " créditos");
        }

        // Prueba EstudianteEnMateriaDAO
        EstudianteEnMateriaDAO estudianteEnMateriaDAO = new EstudianteEnMateriaDAO();

        // Insertar Nota
        EstudianteEnMateria nota = new EstudianteEnMateria(1, 3, "segundo parcial", 90);
        estudianteEnMateriaDAO.insertar(nota);

        // Actualizar Nota
        estudianteEnMateriaDAO.actualizarNotas(1, 3, "segundo parcial", 95);

        // Eliminar Nota
        estudianteEnMateriaDAO.eliminar(1, 3);

        // Listar Notas
        List<EstudianteEnMateria> notas = estudianteEnMateriaDAO.listarMateriasPorEstudiante(1);
        System.out.println("\nListado de Notas de Estudiante 1:");
        for (EstudianteEnMateria n : notas) {
            System.out.println("Materia: " + n.getIdMateria() + ", Nota: " + n.getValorNota());
        }
    }
}




