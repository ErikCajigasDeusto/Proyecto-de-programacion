package main;

import java.util.List;
import javax.swing.SwingUtilities;

import db.GestorBD;
import domain.*;
import gui.*;

public class Main {

    public static void main(String[] args) {

        GestorBD gestor = new GestorBD();

        List<Editorial> editoriales = gestor.cargarEditoriales();
        List<Autor> autores = gestor.cargarAutores();
        List<Libro> libros = gestor.cargarLibros(autores, editoriales);
        List<Miembro> miembros = gestor.cargarMiembros();
        List<Prestamo> prestamos = gestor.cargarPrestamos(libros, miembros);

        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal(libros, miembros, prestamos, gestor);
        });
    }
}
	

