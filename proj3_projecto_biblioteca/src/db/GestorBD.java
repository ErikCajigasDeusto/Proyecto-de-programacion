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


    
    public void verMembresias() {
        String sql = "SELECT DISTINCT MEMBRESIA FROM MIEMBRO"; // Consulta SQL para ver los valores únicos

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Imprime los valores que están en la columna MEMBRESIA
                System.out.println("Membresia: " + rs.getString("MEMBRESIA"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Prestamo> cargarPrestamos(List<Libro> libros, List<Miembro> miembros) {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM PRESTAMO";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int libroId = rs.getInt("ID_LIBRO");
                int miembroId = rs.getInt("ID_MIEMBRO");

                if (libroId > 0 && miembroId > 0) {
                    Libro libro = null;
                    Miembro miembro = null;

                    
                    for (Libro l : libros) {
                        if (l.getId_libro() == libroId) {
                            libro = l;
                            break;
                        }
                    }
                    
                    for (Miembro m : miembros) {
                        if (m.getId() == miembroId) {
                            miembro = m;
                            break;
                        }
                    }

                    if (libro != null && miembro != null) {
                        prestamos.add(new Prestamo(
                                rs.getInt("ID_PRESTAMO"),
                                libro,
                                miembro,
                                LocalDate.parse(rs.getString("FECHA_INICIO")),
                                0
                        ));
                    } else {
                        System.out.println("ERROR: Préstamo con libro o miembro no válido - LIBRO_ID: " + libroId + ", MIEMBRO_ID: " + miembroId);
                    }
                } else {
                    System.out.println("ERROR: ID_LIBRO o ID_MIEMBRO inválido - LIBRO_ID: " + libroId + ", MIEMBRO_ID: " + miembroId);
                }
            }

            // Depuración: Imprimir la cantidad de préstamos cargados
            System.out.println("Préstamos cargados desde la base de datos: " + prestamos.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prestamos;
    }

    
    public void guardarPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO PRESTAMO (ID_LIBRO, ID_MIEMBRO, FECHA_INICIO) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, prestamo.getLibro().getId_libro());
            stmt.setInt(2, prestamo.getMiembro().getId());
            stmt.setString(3, prestamo.getFecha_inicial_prestamo().toString());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                prestamo.setId(rs.getInt(1)); // ID REAL DE BD
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void eliminarPrestamo(Prestamo prestamo) {
        String sql = "DELETE FROM PRESTAMO WHERE ID_PRESTAMO = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, prestamo.getId()); 
            stmt.executeUpdate(); 

            Libro libro = prestamo.getLibro();
            String updateCantidad = "UPDATE LIBRO SET CANTIDAD = ? WHERE ID_LIBRO = ?";
            try (PreparedStatement stmtUpdate = conn.prepareStatement(updateCantidad)) {
                stmtUpdate.setInt(1, libro.getCantidad() + 1);  
                stmtUpdate.setInt(2, libro.getId_libro());
                stmtUpdate.executeUpdate();  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    public void actualizarCantidadLibro(Libro libro) {
        String sql = "UPDATE LIBRO SET CANTIDAD = ? WHERE ID_LIBRO = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, libro.getCantidad());  // Nueva cantidad
            stmt.setInt(2, libro.getId_libro());  // ID del libro
            stmt.executeUpdate();  // Ejecuta la actualización
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // INSERCIÓN
    public int nuevoMiembro(String nombre, String apellido, String contraseña, Membresia membresia) {
        String sql = "INSERT INTO MIEMBRO (NOMBRE, APELLIDO, CONTRASEÑA, MEMBRESIA) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, contraseña);
            stmt.setString(4, membresia.name());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); //ID REAL DE LA BD
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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

