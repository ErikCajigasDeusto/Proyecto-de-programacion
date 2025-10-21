package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Libro;
import domain.Genero;

public class Ventana_Alquilar extends JFrame {
	//iniciación de variables
	private static final long serialVersionUID = 1L;
	private List<Libro> libros;
	public DefaultTableModel modeloDatos;
	public JTable TablalibrosDisponibles;
	//creación de ventana
	public Ventana_Alquilar(List<Libro> libros) {
		this.libros = libros;
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(320, 240);
		setLocationRelativeTo(null);
		
		JPanel panelPrincipal = new JPanel();
		add(panelPrincipal, BorderLayout.CENTER);
		//Botones
		JPanel panelBotones = new JPanel();
		panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
		JButton buscar = new JButton("buscar");
		panelBotones.add(buscar);
		JButton alquilar = new JButton("alquilar");
		panelBotones.add(alquilar);
		JButton salir = new JButton("salir");
		panelBotones.add(salir);
		//Buscador
		JPanel panelBuscador = new JPanel();
		panelBuscador.setLayout(new GridLayout(3, 2));
		panelPrincipal.add(panelBuscador, BorderLayout.NORTH);
		JTextField tituloBuscar = new JTextField("Título");
		tituloBuscar.setEditable(false);
		JTextField generoBuscar = new JTextField("Género");
		generoBuscar.setEditable(false);
		JTextField autorBuscar = new JTextField("Autor");
		autorBuscar.setEditable(false);
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
		//Tabla
		initTabla();
		loadLibros();
		JScrollPane contentPanel = new JScrollPane(this.TablalibrosDisponibles);
		contentPanel.setBorder(new TitledBorder("alquilar Libros"));
		panelPrincipal.add(contentPanel, BorderLayout.CENTER);
		this.TablalibrosDisponibles.setFillsViewportHeight(true);
		//Titulo
		JTextField tituloVentana = new JTextField("Alquilar Libro");
		tituloVentana.setEditable(false);
		tituloVentana.setHorizontalAlignment(JLabel.CENTER);
		add(tituloVentana, BorderLayout.NORTH);
		setVisible(true);
	}
	private void initTabla() {
		Vector<String> cabecera = new Vector<String>(Arrays.asList("Título", "Género", "Autor", "Precio", "Duración Prestamo", "Disponibilidad"));
		this.modeloDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);
		this.TablalibrosDisponibles = new JTable(this.modeloDatos);
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());			
			if (table.equals(TablalibrosDisponibles)) {
				if (row % 2 == 0) {
					result.setBackground(new Color(250, 249, 249));
				} else {
					result.setBackground(new Color(190, 227, 219));
				}
			} else {
				result.setBackground(table.getBackground());
				result.setForeground(table.getForeground());
			}
			if (isSelected) {
				result.setBackground(table.getSelectionBackground());
				result.setForeground(table.getSelectionForeground());			
			}
			result.setOpaque(true);
			return result;
		};
		TablalibrosDisponibles.getTableHeader().setDefaultRenderer(new TableCellRenderer(){
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel label = new JLabel(value.toString());
				label.setOpaque(true);
				label.setBackground(new Color(255, 255, 255));
				label.setHorizontalAlignment(SwingConstants.LEFT);
				return label;
			}
			
		});
		this.TablalibrosDisponibles.setRowHeight(26);
		this.TablalibrosDisponibles.getTableHeader().setReorderingAllowed(false);
		this.TablalibrosDisponibles.getTableHeader().setResizingAllowed(false);
		this.TablalibrosDisponibles.setAutoCreateRowSorter(true);
	}
	private void loadLibros() {
		this.modeloDatos.setRowCount(0);
		this.libros.forEach(c -> this.modeloDatos.addRow(
				new Object[] {c.getTitulo(), c.getGenero().name(), c.getAutor().getNombreApellido(), c.getPrecio(), Math.random(), true } )
		);
	}
}
