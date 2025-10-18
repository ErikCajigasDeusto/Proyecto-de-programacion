package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import domain.Genero;

public class Ventana_Alquilar extends JFrame {
	private static final long serialVersionUID = 1L;
	public DefaultTableModel modeloDatos;
	public JTable librosDisponibles;
	public Ventana_Alquilar() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(320, 240);
		setLocationRelativeTo(null);
		setExtendedState(MAXIMIZED_BOTH);
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(2,2));
		add(contentPanel, BorderLayout.CENTER);
		JPanel panelBuscador = new JPanel();
		panelBuscador.setLayout(new GridLayout(3, 2));
		contentPanel.add(panelBuscador);
		JPanel panelBotones = new JPanel();
		add(panelBotones, BorderLayout.SOUTH);
		JButton buscar = new JButton("buscar");
		contentPanel.add(buscar);
		JButton alquilar = new JButton("alquilar");
		panelBotones.add(alquilar);
		JButton salir = new JButton("salir");
		panelBotones.add(salir);
		JTextField tituloVentana = new JTextField("Alquilar Libro");
		tituloVentana.setEditable(false);
		tituloVentana.setAlignmentX(CENTER_ALIGNMENT);
		add(tituloVentana, BorderLayout.NORTH);
		JTextField tituloBuscar = new JTextField("Título");
		tituloBuscar.setEditable(false);
		JTextField generoBuscar = new JTextField("Género");
		generoBuscar.setEditable(false);
		JTextField autorBuscar = new JTextField("Autor");
		autorBuscar.setEditable(false);
		initTabla();
		contentPanel.add(librosDisponibles);
		JTextField tituloTexto = new  JTextField();
		JTextField autorTexto = new JTextField();
		JComboBox<Genero> generoBox = new JComboBox<>();
		generoBox.setEditable(false);
		panelBuscador.add(tituloBuscar);
		panelBuscador.add(tituloTexto);
		panelBuscador.add(generoBuscar);
		panelBuscador.add(generoBox);
		panelBuscador.add(autorBuscar);
		panelBuscador.add(autorTexto);
		pack();
		setVisible(true);
	}
	private void initTabla() {
		Vector<String> cabecera = new Vector<String>(Arrays.asList("Título", "Género", "Autor", "Precio", "Duración Prestamo", "Disponibilidad"));
		this.modeloDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);
		this.librosDisponibles = new JTable(this.modeloDatos);
	}
	public static void main(String[] args) {
		new Ventana_Alquilar();
	}
}
