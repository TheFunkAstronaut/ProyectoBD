package com.ecodeup.jdbc.Dialogs.d;

import com.ecodeup.jdbc.DAO.MateriaDAO;
import com.ecodeup.jdbc.Entidades.Materia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActualizarMateriaPanel extends JDialog {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtNumCreditos;

    private JButton btnActualizar;

    public ActualizarMateriaPanel(JFrame parent) {
        super(parent, "Actualizar Materia", true);
        configurarLayout();
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new GridLayout(3, 2, 10, 10));

        txtId = new JTextField(10);
        txtNombre = new JTextField(20);
        txtNumCreditos = new JTextField(10);

        panelDatos.add(new JLabel("ID:"));
        panelDatos.add(txtId);
        panelDatos.add(new JLabel("Nombre:"));
        panelDatos.add(txtNombre);
        panelDatos.add(new JLabel("Número de Créditos:"));
        panelDatos.add(txtNumCreditos);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarMateria();
            }
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnActualizar);

        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(400, 200);
        setLocationRelativeTo(null);
    }

    private void actualizarMateria() {
        int id;
        try {
            id = Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = txtNombre.getText();
        int numCreditos;
        try {
            numCreditos = Integer.parseInt(txtNumCreditos.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un número de créditos válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Materia materia = new Materia(id, nombre, numCreditos);

        MateriaDAO materiaDAO = new MateriaDAO();
        if (materiaDAO.actualizarMateria(materia)) {
            JOptionPane.showMessageDialog(this, "Materia actualizada correctamente", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar la materia", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

