package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Libro;
import domain.Miembro;
import domain.Prestamo;

public class VentanaBiblioteca extends JFrame {

    private static final long serialVersionUID = 1L;

    private List<Libro> libros;
    private List<Miembro> miembros;
    private List<Prestamo> prestamos;

    public VentanaBiblioteca(List<Libro> libros, List<Miembro> miembros, List<Prestamo> prestamos) {
        this.libros = libros;
        this.miembros = miembros;
        this.prestamos = prestamos;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Biblioteca");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(Color.WHITE);
        JLabel titulo = new JLabel("Biblioteca");
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        panelTitulo.add(titulo);
        add(panelTitulo, BorderLayout.NORTH);

        // Panel central con imagen de fondo adaptable
        JPanel panelImagen = new JPanel(new BorderLayout()) {
            private static final long serialVersionUID = 1L;
            private Image imagenFondo;

            {
                try {
                    // Cargar imagen
                    ImageIcon icono = new ImageIcon(getClass().getResource("/biblioteca.png"));
                    imagenFondo = icono.getImage();
                } catch (Exception e) {
                    System.err.println("⚠ No se pudo cargar la imagen: " + e.getMessage());
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    // Dibuja la imagen escalada al tamaño actual del panel
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.DARK_GRAY);
                    g.drawString("Imagen no encontrada", getWidth() / 2 - 50, getHeight() / 2);
                }
            }
        };
        add(panelImagen, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        JButton botonUsuario = new JButton("Nuevo Usuario");
        JButton botonMiembro = new JButton("Miembro");
        panelBotones.add(botonUsuario);
        panelBotones.add(botonMiembro);
        add(panelBotones, BorderLayout.SOUTH);

        // Lambdas para acciones de los botones
        botonUsuario.addActionListener(e -> new VentanaRegistroUsuario(miembros));
        botonMiembro.addActionListener(e -> new VentanaInicioUsuario(libros, miembros, prestamos));

        setVisible(true);
    }
    	
}

