package com.ecodeup.jdbc.DAO;
import com.ecodeup.jdbc.ConexionBD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.ecodeup.jdbc.Entidades.Estudiante;
import com.ecodeup.jdbc.Entidades.EstudianteEnMateria;
import com.ecodeup.jdbc.Entidades.Materia;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
public class EstudianteDAO {

    private static final String SQL_INSERT = "INSERT INTO Estudiantes (id_estudiante, nombre, ApellidoPaterno, ApellidoMaterno, carrera, fechaNacimiento) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Estudiantes SET nombre=?, apellidoPaterno=?, apellidoMaterno=?, fechaNacimiento=?, carrera=? WHERE id_estudiante=?";
    private static final String SQL_DELETE = "DELETE FROM Estudiantes WHERE id_estudiante=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM Estudiantes";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM Estudiantes WHERE id_estudiante=?";

    public void insertar(Estudiante estudiante) {
        try {
            Connection conn = ConexionBD.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
            pstmt.setInt(1, estudiante.getId());
            pstmt.setString(2, estudiante.getNombre());
            pstmt.setString(3, estudiante.getApellidoPaterno());
            pstmt.setString(4, estudiante.getApellidoMaterno());
            pstmt.setString(5, estudiante.getCarrera());
            pstmt.setDate(6, new java.sql.Date(estudiante.getFechaNacimiento().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al insertar el estudiante: " + ex.getMessage());
        }
    }

    private void closeResources(PreparedStatement pstmt) {
        try {
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Estudiante estudiante) {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {

            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getApellidoPaterno());
            pstmt.setString(3, estudiante.getApellidoMaterno());
            pstmt.setDate(4, new java.sql.Date(estudiante.getFechaNacimiento().getTime())); // Convertir aquí
            pstmt.setString(5, estudiante.getCarrera());
            pstmt.setInt(6, estudiante.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int idEstudiante) {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_DELETE)) {

            pstmt.setInt(1, idEstudiante);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Estudiante> listar() {
        List<Estudiante> estudiantes = new ArrayList<>();

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_estudiante");
                String nombre = rs.getString("nombre");
                String apellidoPaterno = rs.getString("apellidoPaterno");
                String apellidoMaterno = rs.getString("apellidoMaterno");
                java.util.Date fechaNacimiento = rs.getDate("fechaNacimiento");
                String carrera = rs.getString("carrera");

                estudiantes.add(new Estudiante(id, nombre, apellidoPaterno, apellidoMaterno,carrera,new Date(fechaNacimiento.getTime())));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudiantes;
    }

    public Estudiante obtenerPorId(int idEstudiante) {
        Estudiante estudiante = null;

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {

            pstmt.setInt(1, idEstudiante);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellidoPaterno = rs.getString("apellidoPaterno");
                    String apellidoMaterno = rs.getString("apellidoMaterno");
                    java.util.Date fechaNacimiento = rs.getDate("fechaNacimiento");
                    String carrera = rs.getString("carrera");

                    estudiante = new Estudiante(idEstudiante, nombre, apellidoPaterno, apellidoMaterno, carrera,new Date(fechaNacimiento.getTime()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudiante;
    }
    public int obtenerIdPorNombreCompleto(String nombreCompleto) {
        int id = 0;
        String[] nombres = nombreCompleto.split(" ");
        String nombre = nombres[0];
        String apellidoPaterno = nombres[1];

        String SQL_SELECT_ID = "SELECT id_estudiante FROM Estudiantes WHERE nombre=? AND apellidoPaterno=?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ID)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellidoPaterno);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_estudiante");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public List<Materia> listarMateriasPorEstudiante(int idEstudiante) {
        List<Materia> materias = new ArrayList<>();
        Connection conn = ConexionBD.getConnection();

        String query = "SELECT m.id_materia, m.nombre " +
                "FROM Materias m " +
                "INNER JOIN Estudiantes_en_materias em ON m.id_materia = em.id_materia " +
                "WHERE em.id_estudiante = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idMateria = resultSet.getInt("id_materia");
                String nombreMateria = resultSet.getString("nombre");

                Materia materia = new Materia(idMateria, nombreMateria); // Suponiendo un constructor en la clase Materia
                materias.add(materia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materias;
    }

    public List<EstudianteEnMateria> listarNotasPorEstudiante(int idEstudiante) {
        Connection conn = ConexionBD.getConnection();
        List<EstudianteEnMateria> notas = new ArrayList<>();

        String query = "SELECT em.id_materia, em.tipoNota, em.valorNota " +
                "FROM Estudiantes_en_materias em " +
                "WHERE em.id_estudiante = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idMateria = resultSet.getInt("id_materia");
                String tipoNota = resultSet.getString("tipoNota");
                float valorNota = resultSet.getFloat("valorNota");

                System.out.println("ID Materia: " + idMateria); // Depuración
                System.out.println("Tipo de Nota: " + tipoNota); // Depuración
                System.out.println("Valor de Nota: " + valorNota); // Depuración

                EstudianteEnMateria nota = new EstudianteEnMateria(idMateria, tipoNota, valorNota);
                notas.add(nota);
            }
            while (resultSet.next()) {
                int idMateria = resultSet.getInt("id_materia"); // Asegúrate de que el nombre coincide con el de la columna en la base de datos
                String tipoNota = resultSet.getString("tipoNota");
                float valorNota = resultSet.getFloat("valorNota");

                EstudianteEnMateria nota = new EstudianteEnMateria(idMateria, tipoNota, valorNota); // Cambia el constructor si es necesario
                notas.add(nota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notas;
    }
    public float calcularPromedioMateria(int idEstudiante, int idMateria) {
        Connection conn = ConexionBD.getConnection();
        float promedio = 0;
        int totalNotas = 0;
        float sumaNotas = 0;

        String query = "SELECT valorNota FROM Estudiantes_en_materias " +
                "WHERE id_estudiante = ? AND id_materia = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, idEstudiante);
            statement.setInt(2, idMateria);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                sumaNotas += resultSet.getFloat("valorNota");
                totalNotas++;
            }

            if (totalNotas > 0) {
                promedio = sumaNotas / totalNotas;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return promedio;
    }
    public boolean agregarNota(int idEstudiante, int idMateria, String tipoNota, float valorNota) {
        Connection conn = ConexionBD.getConnection();

        String query = "INSERT INTO Estudiantes_en_materias (id_estudiante, id_materia, tipoNota, valorNota) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, idEstudiante);
            statement.setInt(2, idMateria);
            statement.setString(3, tipoNota);
            statement.setFloat(4, valorNota);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean actualizarEstudiante(Estudiante estudiante) {
        Connection conn = ConexionBD.getConnection();

        String query = "UPDATE Estudiantes SET nombre=?, ApellidoPaterno=?, ApellidoMaterno=?, carrera=?, fechaNacimiento=? WHERE id_estudiante=?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, estudiante.getNombre());
            statement.setString(2, estudiante.getApellidoPaterno());
            statement.setString(3, estudiante.getApellidoMaterno());
            statement.setString(4, estudiante.getCarrera());

            // Convertir java.util.Date a String con formato "yyyy-MM-dd"
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaNacimientoStr = sdf.format(estudiante.getFechaNacimiento());

            statement.setString(5, fechaNacimientoStr);

            statement.setInt(6, estudiante.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarEstudiante(int id) {
        Connection conn = ConexionBD.getConnection();
        boolean eliminado = false;

        String query = "DELETE FROM Estudiantes WHERE id_estudiante=?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                eliminado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eliminado;
    }
}


