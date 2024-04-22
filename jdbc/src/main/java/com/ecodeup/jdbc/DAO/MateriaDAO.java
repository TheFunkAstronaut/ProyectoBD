package com.ecodeup.jdbc.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.ecodeup.jdbc.ConexionBD;
import com.ecodeup.jdbc.Entidades.Materia;
import javax.swing.*;

public class MateriaDAO {

    private static final String SQL_INSERT = "INSERT INTO Materias (id_materia, nombre, numCreditos) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Materias SET nombre=?, numCreditos=? WHERE id_materia=?";
    private static final String SQL_DELETE = "DELETE FROM Materias WHERE id_materia=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM Materias";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM Materias WHERE id_materia=?";

    private Connection connection;

    public MateriaDAO() {
        connection = ConexionBD.getConnection();
    }

    public int insertar(Materia materia) {
        int rows = 0;
        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
            stmt.setInt(1, materia.getId());
            stmt.setString(2, materia.getNombre());
            stmt.setInt(3, materia.getNumCreditos());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar la materia: " + e.getMessage());
        }
        return rows;
    }



    public int actualizar(Materia materia) {
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            stmt = connection.prepareStatement(SQL_UPDATE);
            stmt.setString(1, materia.getNombre());
            stmt.setInt(2, materia.getNumCreditos());
            stmt.setInt(3, materia.getId());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public int eliminar(int idMateria) {
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            stmt = connection.prepareStatement(SQL_DELETE);
            stmt.setInt(1, idMateria);
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public List<Materia> listar() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Materia> materias = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia(
                        rs.getInt("id_materia"),
                        rs.getString("nombre"),
                        rs.getInt("numCreditos")
                );
                materias.add(materia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materias;
    }

    public Materia buscarPorId(int idMateria) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Materia materia = null;
        try {
            stmt = connection.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, idMateria);
            rs = stmt.executeQuery();
            if (rs.next()) {
                materia = new Materia(
                        rs.getInt("id_materia"),
                        rs.getString("nombre"),
                        rs.getInt("numCreditos")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
        }
        return materia;
    }

    public Materia buscarPorNombre(String nombre) {
        Materia materia = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexionBD.getConnection();
            String query = "SELECT * FROM Materias WHERE nombre = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_materia");
                String nombreMateria = rs.getString("nombre");
                int numCreditos = rs.getInt("numCreditos");

                materia = new Materia(id, nombreMateria, numCreditos);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return materia;
    }

    public boolean registrarMateriaAestudiante(int idEstudiante, int idMateria, String tipoNota, float valorNota) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean registrado = false;

        try {
            conn = ConexionBD.getConnection();
            if (!existeAsociacion(idEstudiante, idMateria, conn)) {
                String query = "INSERT INTO Estudiantes_en_materias (id_estudiante, id_materia, tipoNota, valorNota) VALUES (?, ?, ?, ?)";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, idEstudiante);
                stmt.setInt(2, idMateria);
                stmt.setString(3, tipoNota);
                stmt.setFloat(4, valorNota);

                int resultado = stmt.executeUpdate();

                if (resultado > 0) {
                    registrado = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "La asociación entre el estudiante y la materia ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar la asociación entre el estudiante y la materia", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return registrado;
    }



    private boolean existeAsociacion(int idEstudiante, int idMateria, Connection conn) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        String query = "SELECT * FROM Estudiantes_en_materias WHERE id_estudiante = ? AND id_materia = ?";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, idEstudiante);
        stmt.setInt(2, idMateria);

        rs = stmt.executeQuery();

        if (rs.next()) {
            existe = true;
        }

        if (rs != null) rs.close();
        if (stmt != null) stmt.close();

        return existe;
    }
    public boolean actualizarMateria(Materia materia) {
        Connection conn = ConexionBD.getConnection();

        String query = "UPDATE Materias SET nombre=?, numCreditos=? WHERE id_materia=?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, materia.getNombre());
            statement.setInt(2, materia.getNumCreditos());
            statement.setInt(3, materia.getId());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarMateria(int id) {
        Connection conn = ConexionBD.getConnection();
        boolean eliminado = false;

        String query = "DELETE FROM Materias WHERE id_materia=?";

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
