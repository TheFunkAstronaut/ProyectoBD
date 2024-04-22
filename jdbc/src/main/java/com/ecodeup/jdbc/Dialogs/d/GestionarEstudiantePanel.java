package com.ecodeup.jdbc.Dialogs.d;

import com.ecodeup.jdbc.Entidades.Estudiante;
import com.ecodeup.jdbc.DAO.EstudianteDAO;
import com.ecodeup.jdbc.Entidades.Materia;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class GestionarEstudiantePanel extends JDialog {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JDateChooser dateChooser;
    private JComboBox<String> cbCarrera;

    private JTextArea txtAreaEstudiantes;
    private JTextField txtIdEstudianteBusqueda;
    private JButton btnBuscarMaterias;
    private JTextArea txtAreaMaterias;
    public GestionarEstudiantePanel(JFrame parent) {
        super(parent, "Registrar Estudiante", true);
        configurarLayout();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(parent);
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblId = new JLabel("ID:");
        panelDatos.add(lblId, gbc);

        gbc.gridx++;
        txtId = new JTextField(15);
        panelDatos.add(txtId, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblNombre = new JLabel("Nombre:");
        panelDatos.add(lblNombre, gbc);

        gbc.gridx++;
        txtNombre = new JTextField(15);
        panelDatos.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblApellidoPaterno = new JLabel("Apellido Paterno:");
        panelDatos.add(lblApellidoPaterno, gbc);

        gbc.gridx++;
        txtApellidoPaterno = new JTextField(15);
        panelDatos.add(txtApellidoPaterno, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblApellidoMaterno = new JLabel("Apellido Materno:");
        panelDatos.add(lblApellidoMaterno, gbc);

        gbc.gridx++;
        txtApellidoMaterno = new JTextField(15);
        panelDatos.add(txtApellidoMaterno, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento:");
        panelDatos.add(lblFechaNacimiento, gbc);

        gbc.gridx++;
        dateChooser = new JDateChooser();
        panelDatos.add(dateChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel lblCarrera = new JLabel("Carrera:");
        panelDatos.add(lblCarrera, gbc);

        gbc.gridx++;
        String[] carreras = {"Ingeniería de Sistemas", "Arquitectura", "Derecho"};
        cbCarrera = new JComboBox<>(carreras);
        panelDatos.add(cbCarrera, gbc);



        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnCancelar);

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(panelDatos, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);


        txtAreaEstudiantes = new JTextArea(15, 40);
        txtAreaEstudiantes.setEditable(false);
        add(new JScrollPane(txtAreaEstudiantes), BorderLayout.CENTER);

        txtIdEstudianteBusqueda = new JTextField(5);
        btnBuscarMaterias = new JButton("Buscar Materias");
        btnBuscarMaterias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEstudiante = Integer.parseInt(txtIdEstudianteBusqueda.getText());

                EstudianteDAO estudianteDAO = new EstudianteDAO();
                List<Materia> materias = estudianteDAO.listarMateriasPorEstudiante(idEstudiante);

                txtAreaMaterias.setText("");


                for (Materia materia : materias) {
                    txtAreaMaterias.append("ID Materia: " + materia.getId() + "\n");
                    txtAreaMaterias.append("Nombre: " + materia.getNombre() + "\n");
                    txtAreaMaterias.append("------------------------\n");
                }
            }
        });
        txtAreaMaterias = new JTextArea(10, 30);

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBusqueda.add(new JLabel("ID Estudiante:"));
        panelBusqueda.add(txtIdEstudianteBusqueda);
        panelBusqueda.add(btnBuscarMaterias);

        JPanel panelMaterias = new JPanel(new BorderLayout());
        panelMaterias.setBorder(BorderFactory.createTitledBorder("Materias del Estudiante"));
        panelMaterias.add(new JScrollPane(txtAreaMaterias), BorderLayout.CENTER);

        add(panelBusqueda, BorderLayout.NORTH);
        add(panelMaterias, BorderLayout.CENTER);


    }
    private void guardarEstudiante() {
        String id = getId();
        String nombre = getNombre();
        String apellidoPaterno = getApellidoPaterno();
        String apellidoMaterno = getApellidoMaterno();
        Date fechaNacimiento = getFechaNacimiento();
        String carrera = getCarrera();

        Estudiante estudiante = new Estudiante(Integer.parseInt(id), nombre, apellidoPaterno, apellidoMaterno, carrera, fechaNacimiento);
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        estudianteDAO.insertar(estudiante);

        JOptionPane.showMessageDialog(this, "Estudiante registrado correctamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

        dispose();
    }

    public String getId() {
        return txtId.getText();
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getApellidoPaterno() {
        return txtApellidoPaterno.getText();
    }

    public String getApellidoMaterno() {
        return txtApellidoMaterno.getText();
    }

    public Date getFechaNacimiento() {
        return new Date(dateChooser.getDate().getTime());
    }

    public String getCarrera() {
        return (String) cbCarrera.getSelectedItem();
    }
}

