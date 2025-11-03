package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import  domain.*;
import gui.*;

public class Main {

    public static void main(String[] args) {

        // EDITORIALES
        Editorial alfa = new Editorial(1, "Alfa", "Bilbao");
        Editorial beta = new Editorial(2, "Beta", "Madrid");
        Editorial gamma = new Editorial(3, "Gamma", "Polonia");
        Editorial delta = new Editorial(4, "Delta", "Barcelona");
        Editorial omega = new Editorial(5, "Omega", "Valencia");
        Editorial sigma = new Editorial(6, "Sigma", "Lisboa");

        // AUTORES
        Autor escritor = new Autor(1, "Pepe", "Botella", "España");
        Autor dibujante = new Autor(2, "Pepa", "Rueda", "España");
        Autor animador = new Autor(3, "Tuktuk", "Tundra", "Polonia");
        Autor poeta = new Autor(4, "Mario", "Verso", "Italia");
        Autor cientifico = new Autor(5, "Lisa", "Newton", "Reino Unido");
        Autor historico = new Autor(6, "Clara", "Román", "México");
        Autor novelista = new Autor(7, "Juan", "López", "Chile");

        // LIBROS
        List<Libro> libros = new ArrayList<>();
        libros.add(new Libro(1, "Biblia", Genero.BIBLIOGRAFIA, 12, escritor, alfa));
        libros.add(new Libro(2, "Quiniela", Genero.MISTERIO, 13, dibujante, beta));
        libros.add(new Libro(3, "MLP", Genero.COMEDIA, 41, animador, gamma));
        libros.add(new Libro(4, "Poemas del Alma", Genero.POESIA, 18, poeta, delta));
        libros.add(new Libro(5, "Ciencia y Vida", Genero.CIENCIA, 25, cientifico, omega));
        libros.add(new Libro(6, "Historia Antigua", Genero.HISTORIA, 32, historico, sigma));
        libros.add(new Libro(7, "El Fin del Mundo", Genero.FICCION, 27, novelista, beta));
        libros.add(new Libro(8, "Crónicas del Norte", Genero.AVENTURA, 22, animador, gamma));
        libros.add(new Libro(9, "Amor y Guerra", Genero.DRAMA, 19, poeta, delta));
        libros.add(new Libro(10, "El Universo y Tú", Genero.CIENCIA, 29, cientifico, omega));

        // MIEMBROS
        List<Miembro> miembros = new ArrayList<>();

        Miembro ofelia = new Miembro(1,"Ofelia","Tonelaje","123");
		Miembro bacterio = new Miembro(2,"Bacterio","Tonelaje","234");
		Miembro superin = new Miembro(3,"Super","Intendente","567");
		Miembro lola = new Miembro(4,"lola","bunny","432");
		Miembro rupert = new Miembro(5,"rupert","red","156");
		Miembro oscar = new Miembro(6,"oscar","olivera","999");
        Miembro ana = new Miembro(7, "Ana", "Ruiz", "555");
        Miembro luisa = new Miembro(8, "Luisa", "Torres", "666");
        Miembro pedro = new Miembro(9, "Pedro", "Salas", "777");
        Miembro nora = new Miembro(10, "Nora", "Vega", "888");

        miembros.add(ofelia);
        miembros.add(bacterio);
        miembros.add(superin);
        miembros.add(ana);
        miembros.add(luisa);
        miembros.add(pedro);
        miembros.add(nora);

        // PRÉSTAMOS
        List<Prestamo> prestamos = new ArrayList<>();

        prestamos.add(new Prestamo(1, libros.get(0), ofelia, LocalDate.of(2023, 10, 21), 0));
        prestamos.add(new Prestamo(2, libros.get(1), ofelia, LocalDate.of(2024, 2, 15), 0));
        prestamos.add(new Prestamo(3, libros.get(2), bacterio, LocalDate.of(2023, 5, 12), 0));
        prestamos.add(new Prestamo(5, libros.get(5), ana, LocalDate.of(2023, 11, 2), 0));
        prestamos.add(new Prestamo(6, libros.get(6), oscar, LocalDate.of(2024, 1, 3), 0));
        prestamos.add(new Prestamo(7, libros.get(7), pedro, LocalDate.of(2023, 8, 9), 0));
        prestamos.add(new Prestamo(8, libros.get(8), luisa, LocalDate.of(2023, 9, 17), 0));
        prestamos.add(new Prestamo(9, libros.get(9), nora, LocalDate.of(2024, 3, 5), 0));
        prestamos.add(new Prestamo(10, libros.get(3), superin, LocalDate.of(2024, 5, 1), 0));

        // INTERFAZ
        SwingUtilities.invokeLater(() -> {new VentanaPrincipal(libros, miembros, prestamos);
        });
    }
}

