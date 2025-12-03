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

    /**
	 * @return the libros
	 */
	public List<Libro> getLibros() {
		return libros;
	}

	/**
	 * @param libros the libros to set
	 */
	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	/**
	 * @return the miembros
	 */
	public List<Miembro> getMiembros() {
		return miembros;
	}

	/**
	 * @param miembros the miembros to set
	 */
	public void setMiembros(List<Miembro> miembros) {
		this.miembros = miembros;
	}

	/**
	 * @return the prestamos
	 */
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	/**
	 * @param prestamos the prestamos to set
	 */
	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	/**
	 * @return the miembroActual
	 */
	public Miembro getMiembroActual() {
		return miembroActual;
	}

	/**
	 * @param miembroActual the miembroActual to set
	 */
	public void setMiembroActual(Miembro miembroActual) {
		this.miembroActual = miembroActual;
	}

	public VentanaConsultar(List<Libro> libros, List<Miembro> miembros, List<Prestamo> prestamos, Miembro miembroActual) {
        this.libros = libros;
        this.miembros = miembros;
        this.prestamos = prestamos;
        this.miembroActual = miembroActual;

        setTitle("Panel de usuario: " + miembroActual.getNombre());
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear contenedor de pestañas
        JTabbedPane pestañas = new JTabbedPane();

        // Agregar pestaña "Alquilar libro"
        VentanaAlquilar panelAlquilar = new VentanaAlquilar(libros, miembros, prestamos, this);
        pestañas.addTab("Alquilar libro", panelAlquilar);

        // Agregar pestaña "Devolver libro"
        GUIDevolverLibro panelDevolver =  new GUIDevolverLibro(prestamos, miembros, libros, panelAlquilar);
        pestañas.addTab("Devolver libro", panelDevolver);

        // Añadir pestañas al frame
        add(pestañas, BorderLayout.CENTER);

        setVisible(true);
    }
}
