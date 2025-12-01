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
	private JTextField campo_apellido;
	private JPasswordField campo_contraseña;
	private JLabel contraseña;
	private JButton botonOk;
	private JButton botonCancelar;

	private List<Libro> librosList;
	private List<Miembro> miembrosList;
	private List<Prestamo> prestamosList;

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
        
        JPanel panelApellido = new JPanel (new FlowLayout());
        panelApellido.add(new JLabel("Apellido:"));
        campo_apellido = new JTextField(20);
        panelApellido.add(campo_apellido);
        
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

        panel.add(panelNombre);
        panel.add(panelApellido);
        panel.add(panelContraseña);
        panel.add(panelBotones);

        add(panel);

        // Acción: botón OK (login)
        botonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campo_nombreUsuario.getText().trim();

                String apellido = campo_apellido.getText().trim();
                String contraseña = new String(campo_contraseña.getPassword()).trim();


                Miembro encontrado = null;
                for (Miembro m : miembrosList) {


                    if (m.getNombre().equalsIgnoreCase(nombre) &&
                            m.getApellido().equalsIgnoreCase(apellido) &&
                            m.getpassword().equals(contraseña)) {

                        encontrado = m;
                        break;
                    }
                }

                if (encontrado != null) {
                    JOptionPane.showMessageDialog(VentanaInicioUsuario.this, 
                            "Inicio de sesión correcto, bienvenido " + encontrado.getNombre() + " "+ encontrado.getApellido());
                    new VentanaConsultar(librosList, miembrosList, prestamosList, encontrado);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(VentanaInicioUsuario.this, 
                            "Usuario no encontrado. Inténtalo de nuevo.");
                }
            }
            });

        // Acción: botón Cancelar
        botonCancelar.addActionListener(e -> dispose());

        setVisible(true);
        }
	}


