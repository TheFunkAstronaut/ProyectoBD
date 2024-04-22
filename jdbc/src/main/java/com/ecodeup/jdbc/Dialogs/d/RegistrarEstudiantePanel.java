package com.ecodeup.jdbc.Dialogs.d;

import com.ecodeup.jdbc.DAO.EstudianteDAO;
import com.ecodeup.jdbc.Entidades.Estudiante;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RegistrarEstudiantePanel extends JDialog {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JComboBox<String> cbCarreras;
    private JDateChooser dateChooser;
    private JButton btnRegistrar;
    private JButton btnCancelar;

    public RegistrarEstudiantePanel(JFrame parent) {
        super(parent, "Registrar Estudiante", true);
        configurarLayout();
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new GridLayout(6, 2, 10, 10));

        txtId = new JTextField(10);
        txtNombre = new JTextField(30);
        txtApellidoPaterno = new JTextField(30);
        txtApellidoMaterno = new JTextField(30);
        cbCarreras = new JComboBox<>(new String[]{"Ingeniería de Sistemas", "Arquitectura", "Medicina", "Otra"});
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
        panelDatos.add(cbCarreras);
        panelDatos.add(new JLabel("Fecha de Nacimiento:"));
        panelDatos.add(dateChooser);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEstudiante();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    private void registrarEstudiante() {
        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombre.getText();
        String apellidoPaterno = txtApellidoPaterno.getText();
        String apellidoMaterno = txtApellidoMaterno.getText();
        String carrera = (String) cbCarreras.getSelectedItem();
        Date fechaNacimiento = dateChooser.getDate();

        // Crear el objeto Estudiante y guardarlo en la base de datos
        Estudiante estudiante = new Estudiante(id, nombre, apellidoPaterno, apellidoMaterno, carrera, fechaNacimiento);
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        estudianteDAO.insertar(estudiante);

        JOptionPane.showMessageDialog(this, "Estudiante registrado exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
