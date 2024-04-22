package com.ecodeup.jdbc.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ecodeup.jdbc.ConexionBD;
import com.ecodeup.jdbc.Entidades.Estudiante;
import com.ecodeup.jdbc.Entidades.Materia;
import com.ecodeup.jdbc.Entidades.EstudianteEnMateria;

public class EstudianteEnMateriaDAO {

    private static final String SQL_INSERT = "INSERT INTO Estudiantes_en_materias (id_estudiante, id_materia, tipoNota, valorNota) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Estudiantes_en_materias SET tipoNota=?, valorNota=? WHERE id_estudiante=? AND id_materia=?";
    private static final String SQL_DELETE = "DELETE FROM Estudiantes_en_materias WHERE id_estudiante=? AND id_materia=?";
    private static final String SQL_SELECT_MATERIAS_BY_ESTUDIANTE = "SELECT * FROM Estudiantes_en_materias WHERE id_estudiante=?";
    private static final String SQL_SELECT_NOTAS_BY_MATERIA = "SELECT tipoNota, valorNota FROM Estudiantes_en_materias WHERE id_estudiante=? AND id_materia=?";
    private static final String SQL_CHECK_MATERIA = "SELECT COUNT(*) FROM Materias WHERE id_materia=?";
    private Connection connection;

    public EstudianteEnMateriaDAO() {
        connection = ConexionBD.getConnection();
    }

    public void insertar(EstudianteEnMateria estudianteEnMateria) {
        try (PreparedStatement pstmtCheck = connection.prepareStatement(SQL_CHECK_MATERIA)) {
            pstmtCheck.setInt(1, estudianteEnMateria.getIdMateria());
            ResultSet rs = pstmtCheck.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) { // Si la materia existe
                try (PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT)) {
                    pstmt.setInt(1, estudianteEnMateria.getIdEstudiante());
                    pstmt.setInt(2, estudianteEnMateria.getIdMateria());
                    pstmt.setString(3, estudianteEnMateria.getTipoNota());
                    pstmt.setFloat(4, estudianteEnMateria.getValorNota());
                    pstmt.executeUpdate();
                }
            } else {
                System.out.println("La materia con id " + estudianteEnMateria.getIdMateria() + " no existe.");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el estudiante en materia: " + e.getMessage());
        }
    }

    public void actualizarNotas(int idEstudiante, int idMateria, String tipoNota, float valorNota) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE)) {
            pstmt.setString(1, tipoNota);
            pstmt.setFloat(2, valorNota);
            pstmt.setInt(3, idEstudiante);
            pstmt.setInt(4, idMateria);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int idEstudiante, int idMateria) {
        try (PreparedStatement pstmt = connection.prepareStatement(SQL_DELETE)) {
            pstmt.setInt(1, idEstudiante);
            pstmt.setInt(2, idMateria);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<EstudianteEnMateria> listarMateriasPorEstudiante(int idEstudiante) {
        List<EstudianteEnMateria> materias = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_MATERIAS_BY_ESTUDIANTE)) {
            pstmt.setInt(1, idEstudiante);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                EstudianteEnMateria eem = new EstudianteEnMateria();
                eem.setIdEstudiante(rs.getInt("id_estudiante"));
                eem.setIdMateria(rs.getInt("id_materia"));
                eem.setTipoNota(rs.getString("tipoNota"));
                eem.setValorNota(rs.getFloat("valorNota"));
                materias.add(eem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materias;
    }

    public Map<String, Float> obtenerNotasPorMateria(int idEstudiante, int idMateria) {
        Map<String, Float> notas = new HashMap<>();

        try (PreparedStatement pstmt = connection.prepareStatement(SQL_SELECT_NOTAS_BY_MATERIA)) {
            pstmt.setInt(1, idEstudiante);
            pstmt.setInt(2, idMateria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String tipoNota = rs.getString("tipoNota");
                float valorNota = rs.getFloat("valorNota");
                notas.put(tipoNota, valorNota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notas;
    }
    public void actualizarIdEstudiante(int idEstudianteAntiguo, int idEstudianteNuevo) {
        String SQL_UPDATE_ID = "UPDATE Estudiantes_en_materias SET id_estudiante=? WHERE id_estudiante=?";

        try (PreparedStatement pstmt = connection.prepareStatement(SQL_UPDATE_ID)) {
            pstmt.setInt(1, idEstudianteNuevo);
            pstmt.setInt(2, idEstudianteAntiguo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



