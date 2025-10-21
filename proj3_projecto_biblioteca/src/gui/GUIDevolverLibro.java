package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.EventObject;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import domain.*;


public class GUIDevolverLibro extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Prestamo> prestamos;
	
	private JTable tablaprestamos;
	private DefaultTableModel modeloDatosprestamos;
	private DefaultTableModel modeloDatosPersonajes;
	private JScrollPane scrollPanePersonajes;
	private JTextField txtvalor;
	private Libro seleccionado;
	
	public GUIDevolverLibro(List<Prestamo> prestamos) {
		//Asignamos la lista de prestamos a la varaible local
		this.prestamos = prestamos;

		//Se inicializan las tablas y sus modelos de datos
		this.initTables();
		//Se cargan los prestamos en la tabla de prestamos
		this.loadprestamos();
		
		//La tabla de prestamos se inserta en un panel con scroll
		JScrollPane scrollPaneprestamos = new JScrollPane(this.tablaprestamos);
		scrollPaneprestamos.setBorder(new TitledBorder(""));
		this.tablaprestamos.setFillsViewportHeight(true);
		
				
		//Consiste en el titulo del texto
		JLabel titulo = new JLabel("Devolución de prestamos", SwingConstants.CENTER);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setVerticalAlignment(SwingConstants.NORTH);
		titulo.setFont(new Font(titulo.getFont().getName(), titulo.getFont().getStyle(), 30));
		
		//añadir los JButton del final
		JButton pagar = new JButton("Pagar");
		JButton cancelar = new JButton("Cancelar");
		
		this.txtvalor = new JTextField(20);		
		JPanel panelPrecio = new JPanel();
		panelPrecio.add(new JLabel("Precio a pagar: "));
		panelPrecio.add(txtvalor);
		panelPrecio.add(pagar);
		panelPrecio.add(cancelar);
		
		
		
		
		JPanel panelprestamos = new JPanel();
		panelprestamos.setLayout(new BorderLayout());
		panelprestamos.add(BorderLayout.CENTER, scrollPaneprestamos);
		panelprestamos.add(BorderLayout.NORTH, titulo);
		panelprestamos.add(BorderLayout.SOUTH,panelPrecio);
				
		//El Layout del panel principal es un matriz con 2 filas y 1 columna
		this.getContentPane().setLayout(new GridLayout(2, 1));
		this.getContentPane().add(panelprestamos);
		
		this.setTitle("Ventana principal de prestamos");		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
	}
	
	private void initTables() {
		//Cabecera del modelo de datos
		Vector<String> cabeceraprestamos = new Vector<String>(Arrays.asList( "ID","TITULO", "GENERO","AUTOR", "FECHA DEL PRESTAMO"));
		
		
		//Se crea el modelo de datos para la tabla de prestamos sólo con la cabecera
		/**
		 * Se pasa un modelo de la base de datos que actua con vectores
		 */
		this.modeloDatosprestamos = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraprestamos);
		//Se crea la tabla de prestamos con el modelo de datos
		this.tablaprestamos = new JTable(this.modeloDatosprestamos);
		
		
		
		
		
		TableCellRenderer headerRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());			
			result.setHorizontalAlignment(JLabel.CENTER);
			
			
			result.setBackground(table.getBackground());
			result.setForeground(table.getForeground());
			
			result.setOpaque(true);
			
			return result;
		};
		
		
		

		
		//Se define la altura de las filas de la tabla de prestamos
		this.tablaprestamos.setRowHeight(26);
		
		//Se deshabilita la reordenación de columnas
		this.tablaprestamos.getTableHeader().setReorderingAllowed(false);
		//Se deshabilita el redimensionado de las columna
		this.tablaprestamos.getTableHeader().setResizingAllowed(false);
		//Se definen criterios de ordenación por defecto para cada columna
		this.tablaprestamos.setAutoCreateRowSorter(true);
		
		//hace que solo se puede seleccionar una fila
		this.tablaprestamos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.tablaprestamos.getSelectionModel().addListSelectionListener(e -> {
			//Se obtiene el ID del comic de la fila seleccionada si es distinta de -1
			if (tablaprestamos.getSelectedRow() != -1) {
				seleccionado = (this.prestamos.get((int) tablaprestamos.getValueAt(tablaprestamos.getSelectedRow(), 0) - 1)).getLibro();
				txtvalor.setText(String.valueOf(seleccionado.getPrecio()));
				
			}
		});
		
		//Se establecen los renderers al la cabecera y el contenido
		this.tablaprestamos.getTableHeader().setDefaultRenderer(headerRenderer);		
		
		

		
	}
		
	private void loadprestamos() {
		//Se borran los datos del modelo de datos
		this.modeloDatosprestamos.setRowCount(0);
		//Se añaden los prestamos uno a uno al modelo de datosd
		this.prestamos.forEach(c -> this.modeloDatosprestamos.addRow(
				new Object[] {c.getId(), c.getLibro().getTitulo(),
						c.getLibro().getGenero().name(),
						c.getLibro().getAutor().getNombreApellido(),
						c.getFecha_inicial_prestamo()} )
		);
	}
	
	
}