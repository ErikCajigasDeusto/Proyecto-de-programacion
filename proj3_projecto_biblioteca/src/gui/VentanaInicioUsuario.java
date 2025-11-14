package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import domain.Miembro;
import domain.Libro;
import domain.Prestamo;

import gui.reproductorAudio;

public class VentanaInicioUsuario extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField campo_nombreUsuario;
    private JTextField campo_contraseña;
    private JLabel nombre;
    private JLabel contraseña;
    private JButton botonOk;
    private JButton botonCancelar;

    private reproductorAudio reproductor;
    
    private List<Libro> libros;
    private List<Miembro> miembros;
    private List<Prestamo> prestamos;

    public VentanaInicioUsuario(List<Libro> libros, List<Miembro> miembros, List<Prestamo> prestamos, reproductorAudio reproductor) {
        this.libros = libros;
        this.miembros = miembros;
        this.prestamos = prestamos;
        this.reproductor = reproductor;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Iniciar Sesión");
        setSize(370, 180);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel panelNombre = new JPanel(new FlowLayout());
        panelNombre.add(new JLabel("Usuario:"));
        campo_nombreUsuario = new JTextField(20);
        panelNombre.add(campo_nombreUsuario);

        // Panel para contraseña (label + text field)
        JPanel panelContraseña = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contraseña = new JLabel("Contraseña:");
        contraseña.setAlignmentX(LEFT_ALIGNMENT);
        campo_contraseña = new JTextField(20);
        panelContraseña.add(contraseña);
        panelContraseña.add(campo_contraseña);

        JPanel panelBotones = new JPanel(new FlowLayout());
        botonOk = new JButton("OK");
        botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonOk);
        panelBotones.add(botonCancelar);

        panel.add(panelNombre);
        panel.add(panelContraseña);
        panel.add(panelBotones);

        add(panel);

        // Acción: botón OK (login)
        botonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campo_nombreUsuario.getText().trim();
                String telefono = campo_contraseña.getText().trim();

                Miembro encontrado = null;
                for (Miembro m : miembros) {
                    if (m.getNombre().equalsIgnoreCase(nombre) && m.getpassword().equals(telefono)) {
                        encontrado = m;
                        break;
                    }
                }

                if (encontrado != null) {
                    JOptionPane.showMessageDialog(VentanaInicioUsuario.this, 
                            "Inicio de sesión correcto, bienvenido " + encontrado.getNombre());
                    new VentanaConsultar(libros, miembros, prestamos, encontrado, reproductor);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(VentanaInicioUsuario.this, 
                            "Usuario no encontrado. Inténtalo de nuevo.");
                }
            }
        });

        // Acción: botón Cancelar
        botonCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }
}

