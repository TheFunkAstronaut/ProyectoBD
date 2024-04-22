package com.ecodeup.jdbc.Dialogs.d;

import com.ecodeup.jdbc.DAO.EstudianteDAO;
import com.ecodeup.jdbc.Entidades.Estudiante;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ActualizarEstudiantePanel extends JDialog {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JTextField txtCarrera;
    private JDateChooser dateChooser;

    private JButton btnActualizar;

    public ActualizarEstudiantePanel(JFrame parent) {
        super(parent, "Actualizar Estudiante", true);
        configurarLayout();
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new GridLayout(6, 2, 10, 10));

        txtId = new JTextField(10);
        txtNombre = new JTextField(20);
        txtApellidoPaterno = new JTextField(20);
        txtApellidoMaterno = new JTextField(20);
        txtCarrera = new JTextField(20);
        dateChooser = new JDateChooser();

        panelDatos.add(new JLabel("ID:"));
        panelDatos.add(txtId);
        panelDatos.add(new JLabel("Nombre:"));
        panelDatos.add(txtNombre);
        panelDatos.add(new JLabel("Apellido Paterno:"));
        panelDatos.add(txtApellidoPaterno);
        panelDatos.add(new JLabel("Apellido Materno:"));
        panelDatos.add(txtApellidoMaterno);
        panelDatos.add(new JLabel("Carrera:"));
        panelDatos.add(txtCarrera);
        panelDatos.add(new JLabel("Fecha Nacimiento:"));
        panelDatos.add(dateChooser);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstudiante();
            }
        });

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.add(btnActualizar);

        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    private void actualizarEstudiante() {
        int id;
        try {
            id = Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = txtNombre.getText();
        String apellidoPaterno = txtApellidoPaterno.getText();
        String apellidoMaterno = txtApellidoMaterno.getText();
        String carrera = txtCarrera.getText();
        Date fechaNacimiento = dateChooser.getDate();

        Estudiante estudiante = new Estudiante(id, nombre, apellidoPaterno, apellidoMaterno, carrera, fechaNacimiento);

        EstudianteDAO estudianteDAO = new EstudianteDAO();
        if (estudianteDAO.actualizarEstudiante(estudiante)) {
            JOptionPane.showMessageDialog(this, "Estudiante actualizado correctamente", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar el estudiante", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



