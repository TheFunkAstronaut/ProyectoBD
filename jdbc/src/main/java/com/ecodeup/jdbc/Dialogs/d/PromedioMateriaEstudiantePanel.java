package com.ecodeup.jdbc.Dialogs.d;

import com.ecodeup.jdbc.DAO.EstudianteDAO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PromedioMateriaEstudiantePanel extends JDialog {

    private JTextField txtIdEstudiante;
    private JTextField txtIdMateria;
    private JButton btnCalcular;
    private JLabel lblPromedio;

    public PromedioMateriaEstudiantePanel(JFrame parent) {
        super(parent, "Calcular Promedio de Materia", true);
        configurarLayout();
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        txtIdEstudiante = new JTextField(10);
        txtIdMateria = new JTextField(10);
        btnCalcular = new JButton("Calcular Promedio");
        lblPromedio = new JLabel("Promedio: ");

        panelDatos.add(new JLabel("ID Estudiante:"));
        panelDatos.add(txtIdEstudiante);
        panelDatos.add(new JLabel("ID Materia:"));
        panelDatos.add(txtIdMateria);
        panelDatos.add(btnCalcular);
        panelDatos.add(lblPromedio);

        add(panelDatos, BorderLayout.CENTER);

        btnCalcular.addActionListener(e -> calcularPromedio());

        setSize(400, 200);
        setLocationRelativeTo(null);
    }

    private void calcularPromedio() {
        int idEstudiante, idMateria;
        try {
            idEstudiante = Integer.parseInt(txtIdEstudiante.getText());
            idMateria = Integer.parseInt(txtIdMateria.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese IDs válidos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EstudianteDAO estudianteDAO = new EstudianteDAO();
        float promedio = estudianteDAO.calcularPromedioMateria(idEstudiante, idMateria);

        lblPromedio.setText("Promedio: " + promedio);
    }
}

