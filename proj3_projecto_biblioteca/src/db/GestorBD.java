package db;

import java.io.FileReader;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.*;

public class GestorBD {

    protected static String DRIVER_NAME;
    protected static String DATABASE_FILE;
    protected static String CONNECTION_STRING;
    private Connection conn;

    // CONSTRUCTOR
    public GestorBD() {
        try {
            // Cargar propiedades
            Properties connectionProperties = new Properties();
            connectionProperties.load(new FileReader("src\\resources\\db\\parametros.properties"));

            DRIVER_NAME = connectionProperties.getProperty("DRIVER_NAME");
            DATABASE_FILE = connectionProperties.getProperty("DATABASE_FILE");
            CONNECTION_STRING = connectionProperties.getProperty("CONNECTION_STRING") + DATABASE_FILE;

            // Cargar driver
            Class.forName(DRIVER_NAME);

            // Establecer conexión
            conn = DriverManager.getConnection(CONNECTION_STRING);

        } catch (Exception ex) {
            System.err.println("Error al iniciar el GestorBD: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }

    // MÉTODOS DE CONSULTA
    public List<Editorial> cargarEditoriales() {
        List<Editorial> editoriales = new ArrayList<>();
        String sql = "SELECT * FROM EDITORIAL";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                editoriales.add(new Editorial(
                        rs.getInt("ID_EDITORIAL"),
                        rs.getString("NOMBRE"),
                        rs.getString("DIRECCION")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return editoriales;
    }

    public List<Autor> cargarAutores() {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM AUTOR";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                autores.add(new Autor(
                        rs.getInt("ID_AUTOR"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO"),
                        rs.getString("NACIONALIDAD")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }

    public List<Libro> cargarLibros(List<Autor> autores, List<Editorial> editoriales) {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM LIBRO";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int autorId = rs.getInt("ID_AUTOR");
                int editorialId = rs.getInt("ID_EDITORIAL");

                libros.add(new Libro(
                        rs.getInt("ID_LIBRO"),
                        rs.getString("TITULO"),
                        Genero.valueOf(rs.getString("GENERO")),
                        rs.getFloat("PRECIO"),
                        autores.get(autorId - 1),
                        editoriales.get(editorialId - 1),
                        rs.getInt("CANTIDAD")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public List<Miembro> cargarMiembros() {
        List<Miembro> miembros = new ArrayList<>();
        String sql = "SELECT * FROM MIEMBRO";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                miembros.add(new Miembro(
                        rs.getInt("ID_MIEMBRO"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDO"),
                        rs.getString("CONTRASEÑA"),
                        Membresia.valueOf(rs.getString("MEMBRESIA"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return miembros;
    }

    public List<Prestamo> cargarPrestamos(List<Libro> libros, List<Miembro> miembros) {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM PRESTAMO";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                prestamos.add(new Prestamo(
                        rs.getInt("ID_PRESTAMO"),
                        libros.get(rs.getInt("ID_LIBRO") - 1),
                        miembros.get(rs.getInt("ID_MIEMBRO") - 1),
                        LocalDate.parse(rs.getString("FECHA_INICIO")),
                        0
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prestamos;
    }

    // INSERCIÓN
    public void nuevoMiembro(String nombre, String apellido, String contraseña) {
        String sql = "INSERT INTO MIEMBRO (NOMBRE, APELLIDO, CONTRASEÑA, MEMBRESIA) VALUES (?, ?, ?, 'BASICA')";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, contraseña);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CERRAR LA CONEXIÓN
    public void cerrar() {
        try {
            if (conn != null && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

