package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import domain.Miembro;
import io.reproductorAudio;

public class VentanaSeleccion extends JFrame{
private static final long serialVersionUID=1L; 


public VentanaSeleccion() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("Biblioteca");
	setSize(500,350);
	setLocationRelativeTo(null);
	
	//Panel Biblioteca
	JPanel panelBiblio=new JPanel();
	panelBiblio.setBackground(Color.WHITE);
	JLabel titulo=new JLabel("Biblioteca");
	titulo.setFont(new Font("Arial",Font.BOLD,30));
	panelBiblio.add(titulo);
	add(panelBiblio,BorderLayout.CENTER);
	
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
		VentanaSeleccion ventana=new VentanaSeleccion();
		ventana.setVisible(true);

	}

}
