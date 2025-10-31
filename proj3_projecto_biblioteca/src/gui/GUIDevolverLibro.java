package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import domain.*;



public class GUIDevolverLibro extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Prestamo> prestamos;
	private List<Miembro> miembros;
	private JTable tablaPrestamos;
	private DefaultTableModel modeloUsuarios;
	
	private JTextField txtvalor;
	private Libro seleccionado;
	private JComboBox<String> miembrosBox = new JComboBox<String>();
	private int idbuscado =-1;
	
	
	public GUIDevolverLibro(List<Prestamo> prestamos, List<Miembro> miembros) {
		//Asignamos la lista de prestamos a la variable local
		this.prestamos = prestamos;
		this.miembros = miembros;
		//Se inicializan las tablas y sus modelos de datos
		this.initTables();
		//Se cargan los prestamos en la tabla de prestamos
		this.inicializarPrestamos();
		
		//La tabla de prestamos se inserta en un panel con scroll
		JScrollPane scrollPaneprestamos = new JScrollPane(this.tablaPrestamos);
		scrollPaneprestamos.setBorder(new TitledBorder(""));
		this.tablaPrestamos.setFillsViewportHeight(true);
		
		
		for(Miembro actual : miembros)
		{
			miembrosBox.addItem(actual.getNombreApellido());;
		}
		
				
		//Consiste en el titulo del texto
		JLabel titulo = new JLabel("Devolución de prestamos", SwingConstants.CENTER);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setVerticalAlignment(SwingConstants.NORTH);
		titulo.setFont(new Font(titulo.getFont().getName(), titulo.getFont().getStyle(), 30));
		
		//añadir los JButton del final
		JButton pagar = new JButton("Pagar");
		JButton buscar = new JButton("Buscar");
		
		
		
		buscar.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
            	
                // Crear y mostrar la ventana hija, pasándose a sí misma como referencia
                GUIVentanaComprobacion hija = new GUIVentanaComprobacion(GUIDevolverLibro.this);
                hija.setVisible(true);
            }
        });
		
		this.txtvalor = new JTextField(20);		
		JPanel panelPrecio = new JPanel();
		panelPrecio.add(miembrosBox);
		panelPrecio.add(buscar);	
		panelPrecio.add(new JLabel("Precio a pagar: "));
		panelPrecio.add(txtvalor);
		panelPrecio.add(pagar);
		
		

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
		this.modeloUsuarios = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraprestamos);
		//Se crea la tabla de prestamos con el modelo de datos
		this.tablaPrestamos = new JTable(this.modeloUsuarios);
		
		
		
		
		
		TableCellRenderer headerRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel result = new JLabel(value.toString());			
			result.setHorizontalAlignment(JLabel.CENTER);
			
			
			result.setBackground(table.getBackground());
			result.setForeground(table.getForeground());
			
			result.setOpaque(true);
			
			return result;
		};
			
		//Se define la altura de las filas de la tabla de prestamos
		this.tablaPrestamos.setRowHeight(26);
		
		//Se deshabilita la reordenación de columnas
		this.tablaPrestamos.getTableHeader().setReorderingAllowed(false);
		//Se deshabilita el redimensionado de las columna
		this.tablaPrestamos.getTableHeader().setResizingAllowed(false);
		//Se definen criterios de ordenación por defecto para cada columna
		this.tablaPrestamos.setAutoCreateRowSorter(true);
		
		//hace que solo se puede seleccionar una fila
		this.tablaPrestamos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.tablaPrestamos.getSelectionModel().addListSelectionListener(e -> {
			//Se obtiene el ID del comic de la fila seleccionada si es distinta de -1
			if (tablaPrestamos.getSelectedRow() != -1) {
				
				
				seleccionado = (this.prestamos.get((int) tablaPrestamos.getValueAt(tablaPrestamos.getSelectedRow(), 0) - 1)).getLibro();
				LocalDate actual = LocalDate.now();
				int dif = (int)(ChronoUnit.DAYS.between((this.prestamos.get((int) tablaPrestamos.getValueAt(tablaPrestamos.getSelectedRow(), 0) - 1)).getFecha_inicial_prestamo(), actual));
				
				
				if(dif>seleccionado.getDuracionPrestamo())
				{
					txtvalor.setText(String.valueOf(seleccionado.getPrecio()*1.5));
				}else
				{
					txtvalor.setText(String.valueOf(seleccionado.getPrecio()));
				}
				
				
				
			}
		});
		
		//Se establecen los renderers al la cabecera y el contenido
		this.tablaPrestamos.getTableHeader().setDefaultRenderer(headerRenderer);		
		
		

		
	}
		
	private void inicializarPrestamos() {
		//Se borran los datos del modelo de datos
		this.modeloUsuarios.setRowCount(0);
	}
	

    public void recibirTexto(String usuario, String contra) {
    	
    	if(miembrosBox.getSelectedIndex()!=-1)
    	{
    		for(Miembro miembro: miembros)
    		{
    			if(miembro.getNombre().equals(usuario) && miembro.getpassword().equals(contra))
    			{
    				idbuscado= miembro.getId();
    				filtrarPrestamos();
    				return;
    			}else
    			{
    				inicializarPrestamos();
    			}
    		}
    	}
    	
    	
        
    }
    
    public void filtrarPrestamos()
    {
    	this.modeloUsuarios.setRowCount(0);
    	
    	if(miembrosBox.getSelectedIndex()!=-1)
    	{
   
    		for( Prestamo prestamo: prestamos)
    		{
    			
    			if(miembrosBox.getSelectedItem().toString().equals(prestamo.getMiembro().getNombreApellido() )
    					&& (idbuscado == prestamo.getMiembro().getId()) )
    					{
    						this.modeloUsuarios.addRow(new Object[] {prestamo.getId(), prestamo.getLibro().getTitulo(),
    								prestamo.getLibro().getGenero().name(),
    								prestamo.getLibro().getAutor().getNombreApellido(),
    								prestamo.getFecha_inicial_prestamo()});
    					}
    		}
    	}
    	
    }
	
}