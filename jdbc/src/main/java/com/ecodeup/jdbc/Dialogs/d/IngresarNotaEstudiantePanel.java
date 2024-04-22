package com.ecodeup.jdbc.Dialogs.d;

import com.ecodeup.jdbc.DAO.EstudianteDAO;

import javax.swing.*;
import java.awt.*;

public class IngresarNotaEstudiantePanel extends JDialog {

    private JTextField txtIdEstudiante;
    private JTextField txtIdMateria;
    private JTextField txtTipoNota;
    private JTextField txtValorNota;
    private JButton btnAgregar;

    public IngresarNotaEstudiantePanel(JFrame parent) {
        super(parent, "Ingresar Notas Estudiante", true);
        configurarLayout();
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new GridLayout(5, 2, 10, 10));
        txtIdEstudiante = new JTextField(10);
        txtIdMateria = new JTextField(10);
        txtTipoNota = new JTextField(10);
        txtValorNota = new JTextField(10);

        panelDatos.add(new JLabel("ID Estudiante:"));
        panelDatos.add(txtIdEstudiante);
        panelDatos.add(new JLabel("ID Materia:"));
        panelDatos.add(txtIdMateria);
        panelDatos.add(new JLabel("Tipo de Nota:"));
        panelDatos.add(txtTipoNota);
        panelDatos.add(new JLabel("Valor de Nota:"));
        panelDatos.add(txtValorNota);

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAgregar = new JButton("Agregar Nota");
        panelBoton.add(btnAgregar);

        add(panelDatos, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregarNota());

        setSize(400, 250);
        setLocationRelativeTo(null);
    }

    private void agregarNota() {
        int idEstudiante, idMateria;
        float valorNota;
        String tipoNota;

        try {
            idEstudiante = Integer.parseInt(txtIdEstudiante.getText());
            idMateria = Integer.parseInt(txtIdMateria.getText());
            valorNota = Float.parseFloat(txtValorNota.getText());
            tipoNota = txtTipoNota.getText();

            EstudianteDAO estudianteDAO = new EstudianteDAO();
            boolean resultado = estudianteDAO.agregarNota(idEstudiante, idMateria, tipoNota, valorNota);

            if (resultado) {
                JOptionPane.showMessageDialog(this, "Nota agregada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al agregar la nota", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese datos válidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

