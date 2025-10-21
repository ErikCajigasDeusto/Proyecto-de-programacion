package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaBiblioteca2 extends JFrame{
private static final long serialVersionUID=1L;

public VentanaBiblioteca2() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("Biblioteca");
	setSize(500,350);
	
	//Panel Biblioteca
	JPanel panelBiblio=new JPanel();
	panelBiblio.setBackground(Color.WHITE);
	JLabel titulo=new JLabel("Biblioteca");
	titulo.setFont(new Font("Arial",Font.BOLD,30));
	panelBiblio.add(titulo);
	add(panelBiblio,BorderLayout.NORTH);
	
	//Panel Botones
	JPanel panelBotones=new JPanel();
	JButton botonAlquilar= new JButton("Alquilar");
	panelBotones.add(botonAlquilar);
	JButton botonDevolver=new JButton("Devolver");
	panelBotones.add(botonDevolver);
	JButton botonCancelar=new JButton("Cancelar");
	panelBotones.add(botonCancelar);
	add(panelBotones,BorderLayout.SOUTH);
}

	public static void main(String[] args) {
		VentanaBiblioteca2 ventana=new VentanaBiblioteca2();
		ventana.setVisible(true);

	}

}
