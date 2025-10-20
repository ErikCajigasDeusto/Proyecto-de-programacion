package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import domain.*;


public class GUIDevolverLibro extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Libro> libros;
	
	private JTable tablalibros;
	private DefaultTableModel modeloDatoslibros;
	private DefaultTableModel modeloDatosPersonajes;
	private JScrollPane scrollPanePersonajes;
	private JTextField txtFiltro;
	
	public GUIDevolverLibro(List<Libro> libros) {
		//Asignamos la lista de libros a la varaible local
		this.libros = libros;

		//Se inicializan las tablas y sus modelos de datos
		this.initTables();
		//Se cargan los libros en la tabla de libros
		this.loadlibros();
		
		//La tabla de libros se inserta en un panel con scroll
		JScrollPane scrollPanelibros = new JScrollPane(this.tablalibros);
		scrollPanelibros.setBorder(new TitledBorder("devolver libros"));
		this.tablalibros.setFillsViewportHeight(true);
		
				
		//Se define el comportamiento del campo de texto del filtro
		this.txtFiltro = new JTextField(20);		
		JPanel panelFiltro = new JPanel();
		panelFiltro.add(new JLabel("Filtro por título: "));
		panelFiltro.add(txtFiltro);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
		/**
		 * •	Filtra la tabla de libros a partir del valor que se introduzca en la caja de texto.
		 *  A medida que se va introduciendo texto, los libros que aparezcen en la tabla deben incluir 
		 *  el texto del filtro en su título. Si no hay texto, aparecerán todos los libros.
		 */
		txtFiltro.getDocument().addDocumentListener(new DocumentListener()
				{

					@Override
					public void insertUpdate(DocumentEvent e) {
						filtrarlibros();
						
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						filtrarlibros();
						
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						
						
					}
			
				});
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
				
		//////////////////////////////////////////////////////////////////////
		JPanel panellibros = new JPanel();
		panellibros.setLayout(new BorderLayout());
		panellibros.add(BorderLayout.CENTER, scrollPanelibros);
		panellibros.add(BorderLayout.NORTH, panelFiltro);
				
		//El Layout del panel principal es un matriz con 2 filas y 1 columna
		this.getContentPane().setLayout(new GridLayout(2, 1));
		this.getContentPane().add(panellibros);
		
		this.setTitle("Ventana principal de libros");		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * •	Filtra la tabla de libros a partir del valor que se introduzca en la caja de texto.
	 *  A medida que se va introduciendo texto, los libros que aparezcen en la tabla deben incluir 
	 *  el texto del filtro en su título. Si no hay texto, aparecerán todos los libros.
	 */
	private void filtrarlibros() {
		//Se borran los datos del modelo de datos
		this.modeloDatoslibros.setRowCount(0);
		//Se añaden los libros uno a uno al modelo de datos
		for(Libro actual: this.libros)
		{
			//se obtiene el objeto de textarea 
			/**
			 * el contains diferencia entre mayusculas y minusculas
			 */
			if(actual.getTitulo().contains(txtFiltro.getText()))
			{
				this.modeloDatoslibros.addRow(
						new Object[] {actual.getTitulo(), actual.getAutor().getNombreApellido(), actual.getEditorial().getNombre(), actual.getGenero().name(), actual.getPrecio()});
			}
		}
		
//		this.libros.forEach(c -> this.modeloDatoslibros.addRow(
//				new Object[] {c.getId(), c.getEditorial(), c.getTitulo(), c.getPersonajes().size()} )
//		);
	}
	
	//añadir solo los libros que coincidan con el contenido del mensaje
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	private void initTables() {
		//Cabecera del modelo de datos
		Vector<String> cabeceralibros = new Vector<String>(Arrays.asList( "TITULO", "AUTOR", "EDITORIAL", "GENERO","PRECIO"));
		
		
		//Se crea el modelo de datos para la tabla de libros sólo con la cabecera
		/**
		 * Se pasa un modelo de la base de datos que actua con vectores
		 */
		this.modeloDatoslibros = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceralibros);
		//Se crea la tabla de libros con el modelo de datos
		this.tablalibros = new JTable(this.modeloDatoslibros);
		
		
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());
						
			
			//La filas pares e impares se renderizan de colores diferentes de la tabla de comics			
			if (table.equals(tablalibros)) {
				if (row % 2 == 0) {
					result.setBackground(new Color(250, 249, 249));
				} else {
					result.setBackground(new Color(190, 227, 219));
				}
			//Se usan los colores por defecto de la tabla para las celdas de la tabla de personajes
			} else {
				result.setBackground(table.getBackground());
				result.setForeground(table.getForeground());
			}
			
			//Si la celda está seleccionada se renderiza con el color de selección por defecto
			if (isSelected) {
				result.setBackground(table.getSelectionBackground());
				result.setForeground(table.getSelectionForeground());			
			}
			
			
			result.setOpaque(true);
			
			return result;
		};
		
		
		TableCellRenderer headerRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());			
			result.setHorizontalAlignment(JLabel.CENTER);
			
			switch (value.toString()) {
				case "TÍTULO":
				case "NOMBRE":
				case "EMAIL":
					result.setHorizontalAlignment(JLabel.LEFT);
			}
			
			result.setBackground(table.getBackground());
			result.setForeground(table.getForeground());
			
			result.setOpaque(true);
			
			return result;
		};
		//Se crea un CellEditor a partir de un JComboBox()
		

		
		//Se define la altura de las filas de la tabla de libros
		this.tablalibros.setRowHeight(26);
		
		//Se deshabilita la reordenación de columnas
		this.tablalibros.getTableHeader().setReorderingAllowed(false);
		//Se deshabilita el redimensionado de las columna
		this.tablalibros.getTableHeader().setResizingAllowed(false);
		//Se definen criterios de ordenación por defecto para cada columna
		this.tablalibros.setAutoCreateRowSorter(true);
		
		//Se establecen los renderers al la cabecera y el contenido
		this.tablalibros.getTableHeader().setDefaultRenderer(headerRenderer);		

		
		
//		//Se define la anchura de la columna Título
//		this.tablalibros.getColumnModel().getColumn(2).setPreferredWidth(400);
		
		
		
	}
		
	private void loadlibros() {
		//Se borran los datos del modelo de datos
		this.modeloDatoslibros.setRowCount(0);
		//Se añaden los libros uno a uno al modelo de datos
		this.libros.forEach(c -> this.modeloDatoslibros.addRow(
				new Object[] {c.getTitulo(), c.getAutor().getNombreApellido(), c.getEditorial().getNombre(), c.getGenero().name(), c.getPrecio()} )
		);
	}
	
}