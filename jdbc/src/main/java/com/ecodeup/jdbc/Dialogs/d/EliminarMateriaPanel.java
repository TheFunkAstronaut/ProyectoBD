package com.ecodeup.jdbc.Dialogs.d;

import com.ecodeup.jdbc.DAO.MateriaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarMateriaPanel extends JDialog {

    private JTextField txtId;
    private JButton btnEliminar;

    public EliminarMateriaPanel(JFrame parent) {
        super(parent, "Eliminar Materia", true);
        configurarLayout();
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new FlowLayout(FlowLayout.CENTER));

        txtId = new JTextField(10);
        btnEliminar = new JButton("Eliminar");

        panelDatos.add(new JLabel("ID Materia:"));
        panelDatos.add(txtId);
        panelDatos.add(btnEliminar);

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarMateria();
            }
        });

        add(panelDatos, BorderLayout.CENTER);

        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    private void eliminarMateria() {
        int id;
        try {
            id = Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MateriaDAO materiaDAO = new MateriaDAO();
        if (materiaDAO.eliminarMateria(id)) {
            JOptionPane.showMessageDialog(this, "Materia eliminada correctamente", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar la materia", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
