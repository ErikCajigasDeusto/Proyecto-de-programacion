package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.util.List;

import domain.Libro;
import domain.Miembro;
import domain.Prestamo;

public class VentanaConsultar extends JFrame {

    private static final long serialVersionUID = 1L;

    private List<Libro> libros;
    private List<Miembro> miembros;
    private List<Prestamo> prestamos;
    private Miembro miembroActual;

    public VentanaConsultar(List<Libro> libros, List<Miembro> miembros, List<Prestamo> prestamos, Miembro miembroActual) {
        this.libros = libros;
        this.miembros = miembros;
        this.prestamos = prestamos;
        this.miembroActual = miembroActual;

        setTitle("Panel de usuario: " + miembroActual.getNombre());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear contenedor de pestañas
        JTabbedPane pestañas = new JTabbedPane();

        // Agregar pestaña "Alquilar libro"
        Ventana_Alquilar panelAlquilar = new Ventana_Alquilar(libros, miembros);
        pestañas.addTab("Alquilar libro", panelAlquilar);

        // Agregar pestaña "Devolver libro"
        GUIDevolverLibro panelDevolver =  new GUIDevolverLibro(prestamos, miembros);
        pestañas.addTab("Devolver libro", panelDevolver);

        // Añadir pestañas al frame
        add(pestañas, BorderLayout.CENTER);

        setVisible(true);
    }
}
