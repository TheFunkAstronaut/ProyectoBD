package com.ecodeup.jdbc.Dialogs.d;

import com.ecodeup.jdbc.DAO.EstudianteDAO;
import com.ecodeup.jdbc.Entidades.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarEstudiantesPanel extends JDialog {

    private JTable tablaEstudiantes;
    private DefaultTableModel model;

    public ListarEstudiantesPanel(JFrame parent) {
        super(parent, "Listar Estudiantes", true);
        configurarLayout();
        cargarEstudiantes();
    }

    private void configurarLayout() {
        setLayout(new BorderLayout());

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellido Paterno");
        model.addColumn("Apellido Materno");
        model.addColumn("Carrera");
        model.addColumn("Fecha de Nacimiento");

        tablaEstudiantes = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);

        add(scrollPane, BorderLayout.CENTER);

        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    private void cargarEstudiantes() {
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        List<Estudiante> estudiantes = estudianteDAO.listar();

        for (Estudiante estudiante : estudiantes) {
            model.addRow(new Object[]{
                    estudiante.getId(),
                    estudiante.getNombre(),
                    estudiante.getApellidoPaterno(),
                    estudiante.getApellidoMaterno(),
                    estudiante.getCarrera(),
                    estudiante.getFechaNacimiento()
            });
        }
    }
}

