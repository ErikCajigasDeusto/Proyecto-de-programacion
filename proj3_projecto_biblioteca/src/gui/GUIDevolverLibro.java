package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import domain.*;

public class GUIDevolverLibro extends JPanel{

	private static final long serialVersionUID = 1L;
	private List<Prestamo> prestamos;
	
	private JTable tablaUsuarios;
	private DefaultTableModel modeloUsuarios;
	
	private int filaseleccionada = -1;
	private DefaultTableModel modeloLibros;
	private JTable tablaLibros;
	private JScrollPane scrollPanelibros;
	private JTextField txtvalor;
	private Libro seleccionado;
	
	 public GUIDevolverLibro(List<Prestamo> prestamos) {
	        this.prestamos = prestamos;

	        // Inicializar tablas
	        this.initTables();
	        this.loadprestamos();

	        // Scroll principal para la tabla de préstamos
	        JScrollPane scrollPaneprestamos = new JScrollPane(this.tablaUsuarios);
	        scrollPaneprestamos.setBorder(new TitledBorder("Listado de préstamos"));
	        this.tablaUsuarios.setFillsViewportHeight(true);

	        // Etiqueta de título
	        JLabel titulo = new JLabel("Devolución de préstamos", SwingConstants.CENTER);
	        titulo.setFont(new Font("Arial", Font.BOLD, 24));

	        // Panel inferior con botones
	        JButton pagar = new JButton("Pagar");
	        JButton cancelar = new JButton("Cancelar");
	        this.txtvalor = new JTextField(10);

	        JPanel panelPrecio = new JPanel();
	        panelPrecio.add(new JLabel("Precio a pagar:"));
	        panelPrecio.add(txtvalor);
	        panelPrecio.add(pagar);
	        panelPrecio.add(cancelar);

	        // Panel intermedio con la tabla
	        JPanel panelPrestamos = new JPanel(new BorderLayout());
	        panelPrestamos.add(scrollPaneprestamos, BorderLayout.CENTER);

	        // ✅ Aquí se construye el panel principal
	        setLayout(new BorderLayout());
	        add(titulo, BorderLayout.NORTH);
	        add(panelPrestamos, BorderLayout.CENTER);
	        add(panelPrecio, BorderLayout.SOUTH);
	    }
				
		//El Layout del panel principal es un matriz con 2 filas y 1 columna
//		this.getContentPane().setLayout(new GridLayout(2, 1));
//		this.getContentPane().add(panelprestamos);
//		
//		this.setTitle("Ventana principal de prestamos");		
//		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//		this.setSize(800, 600);
//		this.setLocationRelativeTo(null);
//		this.setVisible(true);		
	
	private void initTables() {
		//Cabecera del modelo de datos
		Vector<String> cabeceraprestamos = new Vector<String>(Arrays.asList( "ID","TITULO", "GENERO","AUTOR", "FECHA DEL PRESTAMO"));
		
		
		//Se crea el modelo de datos para la tabla de prestamos sólo con la cabecera
		/**
		 * Se pasa un modelo de la base de datos que actua con vectores
		 */
		this.modeloUsuarios = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraprestamos);
		//Se crea la tabla de prestamos con el modelo de datos
		this.tablaUsuarios = new JTable(this.modeloUsuarios);
		
		TableCellRenderer headerRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());			
			result.setHorizontalAlignment(JLabel.CENTER);
			
			
			result.setBackground(table.getBackground());
			result.setForeground(table.getForeground());
			
			result.setOpaque(true);
			
			return result;
		};
		
		//Se define la altura de las filas de la tabla de prestamos
		this.tablaUsuarios.setRowHeight(26);
		
		//Se deshabilita la reordenación de columnas
		this.tablaUsuarios.getTableHeader().setReorderingAllowed(false);
		//Se deshabilita el redimensionado de las columna
		this.tablaUsuarios.getTableHeader().setResizingAllowed(false);
		//Se definen criterios de ordenación por defecto para cada columna
		this.tablaUsuarios.setAutoCreateRowSorter(true);
		
		//hace que solo se puede seleccionar una fila
		this.tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.tablaUsuarios.getSelectionModel().addListSelectionListener(e -> {
			//Se obtiene el ID del comic de la fila seleccionada si es distinta de -1
			if (tablaUsuarios.getSelectedRow() != -1) {
				seleccionado = (this.prestamos.get((int) tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(), 0) - 1)).getLibro();
				txtvalor.setText(String.valueOf(seleccionado.getPrecio()));
				
			}
		});
		
		//Se establecen los renderers al la cabecera y el contenido
		this.tablaUsuarios.getTableHeader().setDefaultRenderer(headerRenderer);		
		
		

		
	}
		
	private void loadprestamos() {
		//Se borran los datos del modelo de datos
		this.modeloUsuarios.setRowCount(0);
		//Se añaden los prestamos uno a uno al modelo de datosd
		this.prestamos.forEach(c -> this.modeloUsuarios.addRow(
				new Object[] {c.getId(), c.getLibro().getTitulo(),
						c.getLibro().getGenero().name(),
						c.getLibro().getAutor().getNombreApellido(),
						c.getFecha_inicial_prestamo()} )
		);
	}
	
	
	private void comparadorDepassword()
	{
		String entradaUsuario = JOptionPane.showInputDialog(null, "Escribe un texto:", "Entrada de texto", JOptionPane.QUESTION_MESSAGE);

        // Verificar si el usuario pulsó "Cancelar" o cerró la ventana
        if (entradaUsuario == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada por el usuario.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Definir el texto con el que se realizará la comparación
        String textoComparar = "Hola Mundo";

        // Comparar el texto ingresado con el texto definido
        if (entradaUsuario.equals(textoComparar)) {
            JOptionPane.showMessageDialog(null, "¡El texto coincide!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "El texto no coincide.", "Resultado", JOptionPane.WARNING_MESSAGE);
        }
	}
	
	private void comparadorDOstextareas()
	{
		
	}
	
}