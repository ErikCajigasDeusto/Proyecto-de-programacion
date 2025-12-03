package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import domain.*;
import gui.*;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("No se ha podido cargar el driver de la base de datos");
        }

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:src/resources/db/ProyectoBiblioteca.db")) {
            

            //EDITORIALES
            List<Editorial> editoriales = new ArrayList<>();
            List<Autor> autores = new ArrayList<>();
            List<Libro> libros = new ArrayList<>();
            List<Miembro> miembros = new ArrayList<>();
            List<Prestamo> prestamos = new ArrayList<>();
            String editorialQuery = "SELECT * FROM EDITORIAL";
            try (Statement stmt = conn.createStatement();
                 ResultSet rsEditorial = stmt.executeQuery(editorialQuery)) {

                while (rsEditorial.next()) {
                    int id = rsEditorial.getInt("ID_EDITORIAL");
                    String nombre = rsEditorial.getString("NOMBRE");
                    String direccion = rsEditorial.getString("DIRECCION");
                    editoriales.add(new Editorial(id, nombre, direccion));
                   
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //AUTORES
            String autorQuery = "SELECT * FROM AUTOR";
            try (Statement stmt = conn.createStatement();
                 ResultSet rsAutor = stmt.executeQuery(autorQuery)) {

                while (rsAutor.next()) {
                    int id = rsAutor.getInt("ID_AUTOR");
                    String nombre = rsAutor.getString("NOMBRE");
                    String apellido = rsAutor.getString("APELLIDO");
                    String nacionalidad = rsAutor.getString("NACIONALIDAD");
                    autores.add(new Autor(id, nombre, apellido, nacionalidad));
                    
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //LIBROS
            String libroQuery = "SELECT * FROM LIBRO";
            try (Statement stmt = conn.createStatement();
                 ResultSet rsLibro = stmt.executeQuery(libroQuery)) {

                while (rsLibro.next()) {
                    int id = rsLibro.getInt("ID_LIBRO");
                    String titulo = rsLibro.getString("TITULO");
                    String genero = rsLibro.getString("GENERO");
                    float precio = rsLibro.getFloat("PRECIO");
                    int autorId = rsLibro.getInt("ID_AUTOR");
                    int editorialId = rsLibro.getInt("ID_EDITORIAL");
                    int cantidad = rsLibro.getInt("CANTIDAD");

                    Libro libro = new Libro(id, titulo, Genero.valueOf(genero), precio, autores.get(autorId - 1), editoriales.get(editorialId - 1), cantidad);
                    libros.add(libro);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

           //MIEMBROS
            String miembroQuery = "SELECT * FROM MIEMBRO";
            try (Statement stmt = conn.createStatement();
                 ResultSet rsMiembro = stmt.executeQuery(miembroQuery)) {

                while (rsMiembro.next()) {
                    int id = rsMiembro.getInt("ID_MIEMBRO");
                    String nombre = rsMiembro.getString("NOMBRE");
                    String apellido = rsMiembro.getString("APELLIDO");
                    String contrasena = rsMiembro.getString("CONTRASEÑA");
                    String membresia = rsMiembro.getString("MEMBRESIA");
                    miembros.add(new Miembro(id, nombre, apellido, contrasena, Membresia.valueOf(membresia)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            //PRESTAMOS
            String prestamoQuery = "SELECT * FROM PRESTAMO";
            try (Statement stmt = conn.createStatement();
                 ResultSet rsPrestamo = stmt.executeQuery(prestamoQuery)) {

                while (rsPrestamo.next()) {
                    int id = rsPrestamo.getInt("ID_PRESTAMO");
                    int libroId = rsPrestamo.getInt("ID_LIBRO");
                    int miembroId = rsPrestamo.getInt("ID_MIEMBRO");
                    String fechaString = rsPrestamo.getString("FECHA_INICIO");
                    LocalDate fecha = LocalDate.parse(fechaString); 

                    Prestamo prestamo = new Prestamo(id, libros.get(libroId - 1), miembros.get(miembroId - 1), fecha, 0);
                    prestamos.add(prestamo);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //VENTANA GRAFICA
            SwingUtilities.invokeLater(() -> {
                new VentanaPrincipal(libros, miembros, prestamos);  
            });

        } catch (SQLException e) {
            System.out.println("Error al conectarse a la base de datos: " + e.getMessage());
        		}	
    		}
		

		//AGREGAR NUEVO MIEMBRO A LA BASE DE DATOS
		public static void nuevoMiembro(Connection conn, String nombre, String apellido, String contraseña) {
			String sql = "INSERT INTO MIEMBRO (NOMBRE, APELLIDO, CONTRASEÑA) VALUES (?, ?, ?)";

		    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setString(1, nombre);
		        stmt.setString(2, apellido);
		        stmt.setString(3, contraseña);

		        stmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	}
	

