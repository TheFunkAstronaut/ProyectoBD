package com.ecodeup.jdbc.Dialogs.d;

import com.ecodeup.jdbc.DAO.EstudianteDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarEstudiantePanel extends JDialog {

    private JTextField txtId;
    private JButton btnEliminar;

    public EliminarEstudiantePanel(JFrame parent) {
        super(parent, "Eliminar Estudiante", true);
        configurarLayout();
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new FlowLayout(FlowLayout.CENTER));

        txtId = new JTextField(10);
        btnEliminar = new JButton("Eliminar");

        panelDatos.add(new JLabel("ID Estudiante:"));
        panelDatos.add(txtId);
        panelDatos.add(btnEliminar);

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEstudiante();
            }
        });

        add(panelDatos, BorderLayout.CENTER);

        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    private void eliminarEstudiante() {
        int id;
        try {
            id = Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EstudianteDAO estudianteDAO = new EstudianteDAO();
        if (estudianteDAO.eliminarEstudiante(id)) {
            JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el estudiante", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
