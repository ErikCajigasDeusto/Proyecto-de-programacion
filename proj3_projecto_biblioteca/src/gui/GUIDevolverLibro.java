package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import domain.*;
import io.reproductorAudio;

public class GUIDevolverLibro extends JPanel{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private reproductorAudio reproductor;
	
	private List<Prestamo> prestamos;
	private List<Miembro> miembros;
	private JTable tablaPrestamos;
	private DefaultTableModel modeloUsuarios;
	
	private JTextField txtvalor;
	private Libro seleccionado;
	private JComboBox<String> miembrosBox = new JComboBox<String>();
	private int idbuscado =-1;
	private GUIVentanaComprobacion hija = null;
	
	
	public GUIDevolverLibro(List<Prestamo> prestamos, List<Miembro> miembros, reproductorAudio reproductor) {
		//Asignamos la lista de prestamos a la variable local
				this.prestamos = prestamos;
				this.miembros = miembros;
				this.reproductor = reproductor;
				
				//reproductor.playMus("");
				
				setLayout(new BorderLayout(10, 10));
				setBackground(Color.WHITE);

				// ---------- PANEL BUSCADOR ----------
				JPanel panelBuscador = new JPanel();
				panelBuscador.setPreferredSize(new Dimension(800, 650));
				
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
		            	//Llama a inicio de sesión, funcion que creará una ventana de inicio de sesión
		            	String miembrostr = (String) miembrosBox.getSelectedItem();
		        		for (Miembro miembro:miembros) {
		        			if(miembro.getNombreApellido().equals(miembrostr)) {
		        				inicioSesion(miembro);
		        				break;
		        			}
		        		}
		            }
		        });
				
				pagar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// Pagar y eliminar el libro de la lista de prestamos
						if ((!seleccionado.equals(null))&&idbuscado!=-1) {
							Prestamo prestamo = null;
							for(Prestamo prest:prestamos) {
								if (prest.getLibro().equals(seleccionado)) {
									prestamo = prest;
									break;
								}
							}
							if(!prestamo.equals(null)) {
								prestamos.remove(prestamo);
								filtrarPrestamos();
								//reproductor.playSFX("");
								JOptionPane.showMessageDialog(GUIDevolverLibro.this, "Has pagado el libro, gracias");
							}
						}
					}
				});
				
				this.txtvalor = new JTextField(20);		
				JPanel panelPrecio = new JPanel();
				panelPrecio.add(miembrosBox);
				panelPrecio.add(buscar);	
				panelPrecio.add(new JLabel("Precio a pagar: "));
				panelPrecio.add(txtvalor);
				panelPrecio.add(pagar);
				
				

				panelBuscador.setLayout(new BorderLayout());
				panelBuscador.add(BorderLayout.CENTER, scrollPaneprestamos);
				panelBuscador.add(BorderLayout.NORTH, titulo);
				panelBuscador.add(BorderLayout.SOUTH,panelPrecio);
				panelBuscador.setSize(800, 650);		
				

				add(panelBuscador);

				this.setSize(800, 650);

				this.setVisible(true);		
		
	}
		
	
	public void inicioSesion(Miembro miembro) {
		// Crear y mostrar la ventana hija, pasándose a sí misma como referencia
		hija = new GUIVentanaComprobacion(GUIDevolverLibro.this,miembro, reproductor);
        hija.setVisible(true);
	}
	
	
	private void initTables() {
		//Cabecera del modelo de datos
		Vector<String> cabeceraprestamos = new Vector<String>(Arrays.asList( "ID","TITULO", "GENERO","AUTOR", "FECHA DEL PRESTAMO"));
			
		//Se crea el modelo de datos para la tabla de prestamos sólo con la cabecera
		/**
		 * Se pasa un modelo de la base de datos que actua con vectores
		 */
		this.modeloUsuarios = new DefaultTableModel(new Vector<>(), cabeceraprestamos) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
			    return false; 
			}
		};
		//Se crea la tabla de prestamos con el modelo de datos
		this.tablaPrestamos = new JTable(this.modeloUsuarios);
		
		TableCellRenderer headerRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			if(column==5)
			{
				modeloUsuarios.isCellEditable(row, column);
			}
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
    	int prestamo_cont=1;
    	if(miembrosBox.getSelectedIndex()!=-1)
    	{
   
    		for( Prestamo prestamo: prestamos)
    		{
    			
    			if(miembrosBox.getSelectedItem().toString().equals(prestamo.getMiembro().getNombreApellido() )
    					&& (idbuscado == prestamo.getMiembro().getId()) )
    					{
    						this.modeloUsuarios.addRow(new Object[] {prestamo_cont, prestamo.getLibro().getTitulo(),
    								prestamo.getLibro().getGenero().name(),
    								prestamo.getLibro().getAutor().getNombreApellido(),
    								prestamo.getFecha_inicial_prestamo()});
    						prestamo_cont++;
    					}
    		}
    	}
    	
    }
	
}