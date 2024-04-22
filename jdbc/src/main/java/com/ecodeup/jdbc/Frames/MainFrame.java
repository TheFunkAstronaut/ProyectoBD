package com.ecodeup.jdbc.Frames;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.ecodeup.jdbc.Dialogs.d.*;


public class MainFrame extends JFrame {

    public MainFrame() {
        super("Gestión Académica");
        configurarLayout();
    }

    private void configurarLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);


        JMenuBar menuBar = new JMenuBar();


        JMenu menuEstudiantes = new JMenu("Estudiantes");
        JMenuItem miRegistrarEstudiante = new JMenuItem("Registrar Estudiante");
        JMenuItem miVerMateriasEst = new JMenuItem("Ver Materias Estudiante");


        miRegistrarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarEstudiantePanel dialog = new RegistrarEstudiantePanel(MainFrame.this);
                dialog.setVisible(true);
            }
        });

        miVerMateriasEst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionarEstudiantePanel panel = new GestionarEstudiantePanel(MainFrame.this);
                panel.setVisible(true);
            }
        });


        menuEstudiantes.add(miRegistrarEstudiante);
        menuEstudiantes.add(miVerMateriasEst);

        /////Materias////////
        JMenu menuMaterias = new JMenu("Materias");
        JMenuItem miRegistrarMateria = new JMenuItem("Registrar Materia");
        miRegistrarMateria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionarMateriasPanel panel = new GestionarMateriasPanel(MainFrame.this);
                panel.setVisible(true);
            }
        });
        menuMaterias.add(miRegistrarMateria);

        JMenuItem miListarEstudiantes = new JMenuItem("Listar Estudiantes");
        miListarEstudiantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListarEstudiantesPanel panel = new ListarEstudiantesPanel(MainFrame.this);
                panel.setVisible(true);
            }
        });

        menuMaterias.add(miListarEstudiantes);

        ///////Notas/////////
        JMenu menuNotas = new JMenu("Notas");

        JMenuItem miListarNotas = new JMenuItem("Ver Notas de Estudiante");
        miListarNotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VerNotasEstudiantePanel panel = new VerNotasEstudiantePanel(MainFrame.this);
                panel.setVisible(true);
            }
        });

        JMenuItem miCalcularPromedio = new JMenuItem("Calcular Promedio Materia");
        miCalcularPromedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PromedioMateriaEstudiantePanel panel = new PromedioMateriaEstudiantePanel(MainFrame.this);
                panel.setVisible(true);
            }
        });

        JMenuItem miAddNota = new JMenuItem("Ingresar Notas Estudiante");
        miAddNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IngresarNotaEstudiantePanel panel = new IngresarNotaEstudiantePanel(MainFrame.this);
                panel.setVisible(true);
            }
        });
        menuNotas.add(miAddNota);
        menuNotas.add(miCalcularPromedio);
        menuNotas.add(miListarNotas);
        //////Actualizaciones///////
        JMenu menuActu = new JMenu("Actualizaciones");
        JMenuItem miActualizarEstudiante = new JMenuItem("Actualizar Estudiante");
        miActualizarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActualizarEstudiantePanel panel = new ActualizarEstudiantePanel(MainFrame.this);
                panel.setVisible(true);
            }
        });
        menuActu.add(miActualizarEstudiante);

        JMenuItem miActualizarMateria = new JMenuItem("Actualizar Materia");
        miActualizarMateria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActualizarMateriaPanel panel = new ActualizarMateriaPanel(MainFrame.this);
                panel.setVisible(true);
            }
        });
        JMenuItem miEliminarEstudiante = new JMenuItem("Eliminar Estudiante");
        miEliminarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EliminarEstudiantePanel panel = new EliminarEstudiantePanel(MainFrame.this);
                panel.setVisible(true);
            }
        });


        JMenuItem miEliminarMateria = new JMenuItem("Eliminar Materia");
        miEliminarMateria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EliminarMateriaPanel panel = new EliminarMateriaPanel(MainFrame.this);
                panel.setVisible(true);
            }
        });

        menuActu.add(miActualizarEstudiante);
        menuActu.add(miEliminarEstudiante);
        menuActu.add(miEliminarMateria);
        menuActu.add(miActualizarMateria);

        menuBar.add(menuEstudiantes);
        menuBar.add(menuMaterias);
        menuBar.add(menuNotas);
        menuBar.add(menuActu);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}



