package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Prestamo;
import domain.Libro;
import domain.Miembro;
import domain.Genero;

public class Ventana_Alquilar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private List<Libro> libros;
	private List<Prestamo> prestamos;
	private List<Miembro> miembros;
	private Libro seleccionado;

	public DefaultTableModel modeloDatos;
	public JTable TablalibrosDisponibles;
	private VentanaConsultar ventana;

	private JTextField tituloTexto = new JTextField();
	private JTextField autorTexto = new JTextField();
	private JComboBox<Genero> generoBox = new JComboBox<>();

	// Constructor
	public Ventana_Alquilar(List<Libro> libros, List<Miembro> miembros, List<Prestamo>prestamos, VentanaConsultar ventana) {
		this.ventana = ventana;
		this.libros = libros;
		this.miembros = miembros;
		this.prestamos = prestamos;

		setLayout(new BorderLayout(10, 10));
		setBackground(Color.WHITE);

		// ---------- PANEL BUSCADOR ----------
		JPanel panelBuscador = new JPanel(new GridLayout(3, 2, 10, 10));
		panelBuscador.setPreferredSize(new Dimension(800, 100));

		JTextField tituloBuscar = new JTextField("Título");
		tituloBuscar.setEditable(false);
		JTextField generoBuscar = new JTextField("Género");
		generoBuscar.setEditable(false);
		JTextField autorBuscar = new JTextField("Autor");
		autorBuscar.setEditable(false);

		for (Genero genero : Genero.values()) {
			generoBox.addItem(genero);
		}
		generoBox.setEditable(false);
		panelBuscador.add(tituloBuscar);
		panelBuscador.add(tituloTexto);
		panelBuscador.add(generoBuscar);
		panelBuscador.add(generoBox);
		panelBuscador.add(autorBuscar);
		panelBuscador.add(autorTexto);

		add(panelBuscador, BorderLayout.NORTH);

		// ---------- TABLA DE LIBROS ----------
		initTabla();
		loadLibros();

		JScrollPane contentPanel = new JScrollPane(this.TablalibrosDisponibles);
		contentPanel.setBorder(new TitledBorder("Alquilar Libros"));
		this.TablalibrosDisponibles.setFillsViewportHeight(true);
		add(contentPanel, BorderLayout.CENTER);

		// ---------- BOTONES ----------
		JPanel panelBotones = new JPanel();
		JButton buscar = new JButton("Buscar");
		JButton alquilar = new JButton("Alquilar Libro");
		JButton salir = new JButton("Salir");
		JButton reset = new JButton("Reset");

		reset.setVisible(false);
		reset.setEnabled(false);

		panelBotones.add(buscar);
		panelBotones.add(alquilar);
		panelBotones.add(reset);
		panelBotones.add(salir);

		add(panelBotones, BorderLayout.SOUTH);

		// ---------- ACCIONES ----------
		buscar.addActionListener(e -> {
			reset.setVisible(true);
			reset.setEnabled(true);
			filtrarBusqueda();
		});

		reset.addActionListener(e -> {
			reset.setVisible(false);
			reset.setEnabled(false);
			reset();
		});

		alquilar.addActionListener(e -> {
			if (seleccionado != null) {
				iniciarSesion();
			} else {
				JOptionPane.showMessageDialog(this, "Selecciona un libro primero");
			}
		});

		salir.addActionListener(e -> salir());
	}

	// ---------- MÉTODOS ----------
	private void iniciarSesion() {
		boolean igual = false;
		String username = JOptionPane.showInputDialog(null, "Escribe tu username:", "Entrada de texto", JOptionPane.QUESTION_MESSAGE);
		String password = JOptionPane.showInputDialog(null, "Escribe tu password:", "Entrada de texto", JOptionPane.QUESTION_MESSAGE);
		if((username!=null)&&(password!=null)) {
			for(Miembro miembro:miembros) {
				if(username.equals(miembro.getNombre())) {
					if(password.equals(miembro.getpassword())) {
						igual = true;
						alquilar(miembro);
						break;
					}
				}
			}
			if(!igual&&(!username.equals("")||(!password.equals("")))) {
				JOptionPane.showMessageDialog(this, "Usuario no encontrado, inténtalo de nuevo");
				iniciarSesion();
			}else {
				return;
			}
		}
	}

	private void salir() {
		JOptionPane.showMessageDialog(this, "Saliendo del sistema de alquiler.");
		ventana.dispose();
	}

	private void alquilar(Miembro miembro) {
		
		int	contador = prestamos.getLast().getId()+1;

		prestamos.add(new Prestamo(contador, seleccionado, miembro, LocalDate.now(), serialVersionUID));
		JOptionPane.showMessageDialog(this, "Libro alquilado");
	}

	private void reset() {
		this.modeloDatos.setRowCount(0);
		for (Libro libro : libros) {
			this.modeloDatos.addRow(new Object[]{
					libro.getId_libro(),
					libro.getTitulo(),
					libro.getGenero().name(),
					libro.getAutor().getNombreApellido(),
					libro.getPrecio(),
					5,
					true
			});
		}
		tituloTexto.setText("");
		autorTexto.setText("");
	}

	private void filtrarBusqueda() {
		this.modeloDatos.setRowCount(0);
		for (Libro libro : libros) {
			
			if(String.valueOf(generoBox.getSelectedItem()).toUpperCase().equals(Genero.DEFECTO.name().toUpperCase()) && (libro.getTitulo().toUpperCase().contains(tituloTexto.getText().toUpperCase())) && (libro.getAutor().getNombreApellido().toUpperCase().contains(autorTexto.getText().toUpperCase()))) {
				this.modeloDatos.addRow(new Object[]{
						libro.getId_libro(),
						libro.getTitulo(),
						libro.getGenero().name(),
						libro.getAutor().getNombreApellido(),
						libro.getPrecio(),
						5,
						true
				});
			}else if (libro.getTitulo().toUpperCase().contains(tituloTexto.getText().toUpperCase()) &&
				libro.getAutor().getNombreApellido().toUpperCase().contains(autorTexto.getText().toUpperCase()) &&
				libro.getGenero().name().equalsIgnoreCase(String.valueOf(generoBox.getSelectedItem()))) {

				this.modeloDatos.addRow(new Object[]{
						libro.getId_libro(),
						libro.getTitulo(),
						libro.getGenero().name(),
						libro.getAutor().getNombreApellido(),
						libro.getPrecio(),
						5,
						true
				});
			}
		}
	}

	private void initTabla() {
		Vector<String> cabecera = new Vector<>(Arrays.asList("ID", "Título", "Género", "Autor", "Precio", "Duración", "Disponible"));
		this.modeloDatos = new DefaultTableModel(new Vector<>(), cabecera) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
			    return false; 
			}
		};
		this.TablalibrosDisponibles = new JTable(this.modeloDatos);

		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
			if (row % 2 == 0) {
				result.setBackground(new Color(245, 245, 245));
			} else {
				result.setBackground(new Color(220, 240, 230));
			}
			if (isSelected) {
				result.setBackground(new Color(184, 207, 229));
			}
			result.setOpaque(true);
			return result;
		};

		this.TablalibrosDisponibles.setDefaultRenderer(Object.class, cellRenderer);
		this.TablalibrosDisponibles.getTableHeader().setDefaultRenderer( new TableCellRenderer() 
				{

					@Override
					public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						JLabel label = new JLabel(value.toString());
						label.setOpaque(true);
						label.setBackground(new Color(255, 255, 255));
						label.setHorizontalAlignment(JLabel.LEFT);
						return label;
					}
			
				}
			
			
	
	);
		TablalibrosDisponibles.setDefaultRenderer(Object.class, cellRenderer);
		TablalibrosDisponibles.getTableHeader().setDefaultRenderer(new TableCellRenderer(){
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				JLabel label = new JLabel(value.toString());
				label.setOpaque(true);
				label.setBackground(new Color(255, 255, 255));
				label.setHorizontalAlignment(JLabel.LEFT);
				return label;
			}

		});

		this.TablalibrosDisponibles.setDefaultRenderer(Object.class, cellRenderer);
		this.TablalibrosDisponibles.setRowHeight(26);
		this.TablalibrosDisponibles.getTableHeader().setReorderingAllowed(false);
		this.TablalibrosDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.TablalibrosDisponibles.getSelectionModel().addListSelectionListener(e -> {
			if (TablalibrosDisponibles.getSelectedRow() != -1) {
				seleccionado = this.libros.get((int)TablalibrosDisponibles.getValueAt(TablalibrosDisponibles.getSelectedRow(), 0)-1);				
			}
		});
	}

	private void loadLibros() {
		this.modeloDatos.setRowCount(0);
		this.libros.forEach(c -> this.modeloDatos.addRow(new Object[]{
				c.getId_libro(),
				c.getTitulo(),
				c.getGenero().name(),
				c.getAutor().getNombreApellido(),
				c.getPrecio(),
				5,
				true
		}));
	}
}

