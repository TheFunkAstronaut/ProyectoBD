package com.ecodeup.jdbc.Dialogs.d;

import com.ecodeup.jdbc.DAO.EstudianteDAO;
import com.ecodeup.jdbc.Entidades.EstudianteEnMateria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VerNotasEstudiantePanel extends JDialog {

    private JTextField txtIdEstudiante;
    private JTable tablaNotas;
    private DefaultTableModel model;

    public VerNotasEstudiantePanel(JFrame parent) {
        super(parent, "Ver Notas de Estudiante", true);
        configurarLayout();
    }

    private void configurarLayout() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelDatos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        txtIdEstudiante = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar");
        panelDatos.add(new JLabel("ID Estudiante:"));
        panelDatos.add(txtIdEstudiante);
        panelDatos.add(btnBuscar);

        model = new DefaultTableModel();
        model.addColumn("ID Materia");
        model.addColumn("Tipo de Nota");
        model.addColumn("Valor");

        tablaNotas = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaNotas);

        add(panelDatos, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> cargarNotas());

        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    private void cargarNotas() {
        int idEstudiante;
        try {
            idEstudiante = Integer.parseInt(txtIdEstudiante.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EstudianteDAO estudianteDAO = new EstudianteDAO();
        List<EstudianteEnMateria> notas = estudianteDAO.listarNotasPorEstudiante(idEstudiante);

        model.setRowCount(0); // Limpiar tabla

        for (EstudianteEnMateria nota : notas) {
            model.addRow(new Object[]{
                    nota.getIdMateria(),
                    nota.getTipoNota(),
                    nota.getValorNota()
            });
        }
        tablaNotas.repaint();
    }
}


