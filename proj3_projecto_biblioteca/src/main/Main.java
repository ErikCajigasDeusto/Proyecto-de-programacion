package main;

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
		
		//Lambda expression para abrir la ventana Principal
				SwingUtilities.invokeLater(() -> new GUIDevolverLibro(libros));
				SwingUtilities.invokeLater(() -> new Ventana_Alquilar(libros));
		
	}

}
