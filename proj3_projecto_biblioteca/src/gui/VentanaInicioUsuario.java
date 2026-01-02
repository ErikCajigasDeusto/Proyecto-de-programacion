package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import domain.Miembro;
import domain.Libro;
import domain.Prestamo;

public class VentanaInicioUsuario extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField campo_nombreUsuario;
	private JPasswordField campo_contraseña;
	private JLabel contraseña;
	private JButton botonOk;
	private JButton botonCancelar;
	private JProgressBar progressBar;

	private List<Libro> librosList;
	private List<Miembro> miembrosList;
	private List<Prestamo> prestamosList;
	private Thread t;
	JPanel panel_progreso;

	public VentanaInicioUsuario(List<Libro> libros, List<Miembro> miembros, List<Prestamo> prestamos) {
		this.librosList = libros;
		this.miembrosList = miembros;
		this.prestamosList = prestamos;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Iniciar Sesión");
		setSize(370, 180);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panelNombre = new JPanel(new FlowLayout());
		panelNombre.add(new JLabel("Nombre:"));
		campo_nombreUsuario = new JTextField(20);
		panelNombre.add(campo_nombreUsuario);

		// Panel para contraseña (label + text field)
		JPanel panelContraseña = new JPanel(new FlowLayout(FlowLayout.CENTER));
		contraseña = new JLabel("Contraseña:");
		contraseña.setAlignmentX(LEFT_ALIGNMENT);
		campo_contraseña = new JPasswordField(20);
		panelContraseña.add(contraseña);
		panelContraseña.add(campo_contraseña);

		JPanel panelBotones = new JPanel(new FlowLayout());
		botonOk = new JButton("OK");
		botonCancelar = new JButton("Cancelar");
		panelBotones.add(botonOk);
		panelBotones.add(botonCancelar);

		panel_progreso = new JPanel(new FlowLayout(FlowLayout.CENTER));
		progressBar = new JProgressBar(0, 3);
		panel_progreso.add(progressBar);
		panel_progreso.setVisible(false);

		panel.add(panelNombre);
		panel.add(panelContraseña);
		panel.add(panelBotones);
		panel.add(panel_progreso);

		add(panel);

		// Acción: botón OK (login)
		botonOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = campo_nombreUsuario.getText().trim();

				String contraseña = new String(campo_contraseña.getPassword()).trim();

				Miembro encontrado = null;
				for (Miembro m : miembrosList) {

					if (m.getNombre().equalsIgnoreCase(nombre) && m.getpassword().equals(contraseña)) {

						encontrado = m;
						break;
					}
				}
				final Miembro objeto = encontrado;
				t = new Thread(() -> tiempoCarga(objeto));
				t.start();

			}
		});

		// Acción: botón Cancelar
		botonCancelar.addActionListener(e -> dispose());

		setVisible(true);
	}

	private void tiempoCarga(final Miembro objeto) {
		panel_progreso.setVisible(true);
		
		botonOk.setEnabled(false);
		botonCancelar.setEnabled(false);
		
		for (int i = 0; i <= 3 && !Thread.currentThread().isInterrupted(); i++) {

			
			final int contador = i;


			SwingUtilities.invokeLater(() -> progressBar.setValue(contador));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// en el caso de que hemos despertado el hilo sin querer
				Thread.currentThread().interrupt();
			}
		}

		if (objeto != null && progressBar.getValue() == 3) {
			JOptionPane.showMessageDialog(VentanaInicioUsuario.this,
					"Inicio de sesión correcto, bienvenido " + objeto.getNombre() + " " + objeto.getApellido());
			new VentanaConsultar(librosList, miembrosList, prestamosList, objeto);
			dispose();
			
		} else {
			JOptionPane.showMessageDialog(VentanaInicioUsuario.this, "Usuario no encontrado. Inténtalo de nuevo.");
			progressBar.setValue(0);
			panel_progreso.setVisible(false);
			botonOk.setEnabled(true);
			botonCancelar.setEnabled(true);
			t.interrupt();
		}
	}
}
