package com.ecodeup.jdbc.Dialogs.d;

import com.ecodeup.jdbc.DAO.MateriaDAO;
import com.ecodeup.jdbc.Entidades.Materia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionarMateriasPanel extends JDialog {

    private JTextField txtIdEstudiante;
    private JComboBox<String> cbMaterias;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private MateriaDAO materiaDAO;
    private JTextField txtTipoNota;
    private JTextField txtValorNota;

    public GestionarMateriasPanel(JFrame parent) {
        super(parent, "Gestionar Materias", true);
        materiaDAO = new MateriaDAO();

        configurarLayout();
        cargarMaterias();
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new GridLayout(0, 2, 5, 5));

        txtIdEstudiante = new JTextField(5);
        txtTipoNota = new JTextField(5);
        txtValorNota = new JTextField(5);

        panelDatos.add(new JLabel("ID Estudiante:"));
        panelDatos.add(txtIdEstudiante);
        panelDatos.add(new JLabel("Tipo de Nota:"));
        panelDatos.add(txtTipoNota);
        panelDatos.add(new JLabel("Valor de Nota:"));
        panelDatos.add(txtValorNota);

        JPanel panelMaterias = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cbMaterias = new JComboBox<>();
        panelMaterias.add(cbMaterias);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valorNotaStr = txtValorNota.getText();
                if (valorNotaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(GestionarMateriasPanel.this, "Por favor ingrese un valor para la nota", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idEstudiante = Integer.parseInt(txtIdEstudiante.getText());
                int idMateria = getIdMateriaSeleccionada();
                String tipoNota = txtTipoNota.getText();
                float valorNota = Float.parseFloat(valorNotaStr);

                if (idMateria == -1) {
                    JOptionPane.showMessageDialog(GestionarMateriasPanel.this, "Seleccione una materia válida", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (materiaDAO.registrarMateriaAestudiante(idEstudiante, idMateria, tipoNota, valorNota)) {
                    JOptionPane.showMessageDialog(GestionarMateriasPanel.this, "Materia asignada correctamente al estudiante con ID: " + idEstudiante, "Asignación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(GestionarMateriasPanel.this, "Error al asignar la materia al estudiante", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(panelDatos, BorderLayout.NORTH);
        add(panelMaterias, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(400, 250);
        setLocationRelativeTo(null);
    }


    private void cargarMaterias() {
        List<Materia> materias = materiaDAO.listar();
        for (Materia materia : materias) {
            cbMaterias.addItem(materia.getNombre());
        }
    }
    private int getIdMateriaSeleccionada() {
        String nombreMateria = (String) cbMaterias.getSelectedItem();
        Materia materiaSeleccionada = materiaDAO.buscarPorNombre(nombreMateria);
        return (materiaSeleccionada != null) ? materiaSeleccionada.getId() : -1;
    }

}



