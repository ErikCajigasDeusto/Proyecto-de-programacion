package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;

import db.GestorBD;
import domain.*;

public class SplashScreen extends JWindow {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JProgressBar barraProgreso;
	private JLabel lblEstado;
	private JLabel lblTitulo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SplashScreen() {
		
		setBounds(100, 100, 500, 300);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(45, 45, 45)); // Color oscuro
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2)); // Borde negro fino
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Título Principal
		lblTitulo = new JLabel("Proyecto Biblioteca");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblTitulo.setBounds(10, 68, 480, 50);
		contentPane.add(lblTitulo);
		
		// Label de estado
		lblEstado = new JLabel("Iniciando...");
		lblEstado.setForeground(Color.LIGHT_GRAY);
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(20, 230, 300, 14);
		contentPane.add(lblEstado);
		
		// Barra de Progreso
		barraProgreso = new JProgressBar();
		barraProgreso.setStringPainted(true);
		barraProgreso.setBounds(20, 250, 460, 25);
		contentPane.add(barraProgreso);
		
		iniciarHiloCarga();
	}
	
	/**
	 * Crea e inicia el SwingWorker. 
	 * Esto evita que la interfaz se congele durante la carga de la BD.
	 */
	private void iniciarHiloCarga() {
		CargaWorker worker = new CargaWorker(this);
		worker.execute(); // lanza el hilo en segundo plano (doInBackground)
	}
	
	/**
	 * Método de transición.
	 * Se llama cuando la carga ha finalizado exitosamente.
	 */
	public void abrirVentanaPrincipal(List<Libro> libros, List<Miembro> miembros, List<Prestamo> prestamos, GestorBD gestor) {
		this.dispose();
		new VentanaPrincipal(libros, miembros, prestamos, gestor);
	}

	/**
	 * Método auxiliar para actualizar la GUI desde el SwingWorker.
	 * @param valor Porcentaje de la barra (0-100)
	 * @param mensaje Texto explicativo del estado actual
	 */
	public void actualizarProgreso(int valor, String mensaje) {
		barraProgreso.setValue(valor);
		lblEstado.setText(mensaje);
	}
	
	/**
	 * Void: No devolvemos un objeto final específico.
	 * String: El tipo de dato que usamos para actualizar la GUI (mensajes de estado).
	 */
	class CargaWorker extends SwingWorker<Void, String> {
		
		private SplashScreen splash;
		private GestorBD gestor;
		private List<Editorial> editoriales;
		private List<Autor> autores;
		private List<Libro> libros;
		private List<Miembro> miembros;
		private List<Prestamo> prestamos;

		public CargaWorker(SplashScreen splash) {
			this.splash = splash;
		}

		/**
		 * Tarea que se hace en segundo plano
		 */
		@Override
		protected Void doInBackground() throws Exception {
			
			publish("Conectando a la base de datos...");
			setProgress(10);
			gestor = new GestorBD();
			Thread.sleep(300); // Pequeña pausa estética

			publish("Cargando editoriales...");
			setProgress(30);
			editoriales = gestor.cargarEditoriales();
			Thread.sleep(400);
			
			publish("Cargando autores...");
			setProgress(50);
			autores = gestor.cargarAutores();
			Thread.sleep(400);
			
			publish("Catalogando libros...");
			setProgress(70);
			libros = gestor.cargarLibros(autores, editoriales);
			Thread.sleep(400);
			
			publish("Verificando miembros...");
			setProgress(85);
			miembros = gestor.cargarMiembros();
			Thread.sleep(400);
			
			publish("Recuperando préstamos...");
			setProgress(95);
			prestamos = gestor.cargarPrestamos(libros, miembros);
			Thread.sleep(200);
			
			publish("¡Bienvenido!");
			setProgress(100);
			Thread.sleep(500);

			return null;
		}

		/**
		 * Recibe los datos enviados por 'publish()' para actualizar la interfaz de forma segura.
		 */
		@Override
		protected void process(List<String> chunks) {
			// chunks contiene todos los mensajes pendientes. Tomamos el más reciente.
			String ultimoMensaje = chunks.get(chunks.size() - 1);
			splash.actualizarProgreso(getProgress(), ultimoMensaje);
		}

		/**
		 * Se ejecuta cuando doInBackground termina.
		 * Gestiona la transición a la siguiente ventana.
		 */
		@Override
		protected void done() {
			try {
				get();
				splash.abrirVentanaPrincipal(libros, miembros, prestamos, gestor);
				
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(splash, "Error crítico al cargar datos: " + e.getMessage());
				System.exit(1);
			}
		}
	}
}
