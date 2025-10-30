package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import  domain.*;
import gui.*;
public class Main {

	public static void main(String[] args) {
		Editorial alfa = new Editorial(1,"alfa","bilbao");
		Editorial beta = new Editorial(2,"beta","madrid");
		Editorial gamma = new Editorial(3,"gamma","polonia");
		
		Autor escritor = new Autor(1, "Pepe","botella","españa");
		Autor dibujante = new Autor(2, "Pepa","Rueda","españa");
		Autor animador = new Autor(3, "Tuktuk","tundra","polonia");
		Libro biblia = new Libro(1,"Biblia",Genero.bibliografia,12,escritor,alfa);
		Libro quiniela = new Libro(2,"Quiniela",Genero.misterio,13,dibujante,beta);
		Libro mlp = new Libro(3,"mlp",Genero.comedia,41,animador,gamma);
		List<Libro> libros = new ArrayList<Libro>();
		libros.add(quiniela);
		libros.add(mlp);
		libros.add(biblia);
		
		Miembro ofelia = new Miembro(1,"Ofelia","Tonelaje","123");
		Miembro bacterio = new Miembro(2,"Bacterio","Tonelaje","234");
		Miembro superin = new Miembro(3,"Super","Intendente","567");
		Miembro lola = new Miembro(4,"lola","bunny","432");
		Miembro rupert = new Miembro(5,"rupert","red","156");
		Miembro oscar = new Miembro(6,"oscar","olivera","999");
		
		List<Miembro> miembros = new ArrayList<Miembro>();
		miembros.add(ofelia);
		miembros.add(bacterio);
		miembros.add(superin);
		miembros.add(lola);
		miembros.add(rupert);
		miembros.add(oscar);
		
		List<Prestamo> prestamos = new ArrayList<Prestamo>();
		Prestamo prestamo1 = new Prestamo(1, mlp, ofelia, LocalDate.of(2011, 10, 21), 0);
		Prestamo prestamo2 = new Prestamo(2, biblia, bacterio, LocalDate.of(2013, 10, 21), 0);
		Prestamo prestamo3 = new Prestamo(3, quiniela, ofelia, LocalDate.of(2012, 10, 21), 0);
		Prestamo prestamo4 = new Prestamo(4, biblia, superin, LocalDate.of(2022, 10, 21), 0);
		Prestamo prestamo5 = new Prestamo(5, quiniela, ofelia, LocalDate.of(2015, 10, 21), 0);
		Prestamo prestamo6 = new Prestamo(6, biblia, oscar, LocalDate.of(2024, 10, 21), 0);
		
		prestamos.add(prestamo1);
		prestamos.add(prestamo2);
		prestamos.add(prestamo3);
		prestamos.add(prestamo4);
		prestamos.add(prestamo5);
		prestamos.add(prestamo6);

		
		//Lambda expression para abrir la ventana Principal
			
			SwingUtilities.invokeLater(() -> new GUIDevolverLibro(prestamos,miembros));
			//SwingUtilities.invokeLater(() -> new Ventana_Alquilar(libros, miembros));
		
	}

}
