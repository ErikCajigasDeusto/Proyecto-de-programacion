package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaBiblioteca extends JFrame {
private static final long serialVersionUID = 1L;

public VentanaBiblioteca() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("Biblioteca");
	setSize(320,240);
	
	
	//Panel Biblio
	JPanel panelBiblio= new JPanel();
	panelBiblio.setBackground(Color.WHITE);
	JLabel titulo=new JLabel("Biblioteca");
	panelBiblio.add(titulo);
	add(panelBiblio,BorderLayout.NORTH);
	
	//Panel Imagen
	JPanel panelImagen=new JPanel();
	panelImagen.setBackground(Color.LIGHT_GRAY);
	JLabel imagen=new JLabel("Imagen");
	panelImagen.add(imagen);
	add(panelImagen,BorderLayout.CENTER);
	
	//Boton Usuario
	JPanel panelBotones=new JPanel();
	JButton botonUsuario= new JButton("Nuevo Usuario");
	panelBotones.add(botonUsuario);
	JButton botonMiembro= new JButton("Miembro");
	panelBotones.add(botonMiembro);
	add(panelBotones,BorderLayout.SOUTH);
	
}
public static void main(String[]args) {
	VentanaBiblioteca ventana= new VentanaBiblioteca();
	ventana.setVisible(true);
	}
}
