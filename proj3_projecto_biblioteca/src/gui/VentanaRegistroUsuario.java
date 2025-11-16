package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Miembro;

public class VentanaRegistroUsuario extends JFrame {

    private static final long serialVersionUID = 1L;
    private List<Miembro>miembros;
    
    private JTextField campo_nombreUsuario;
    private JTextField campo_contraseña;
    private JTextField campo_confirmar_contraseña;
    private JLabel nombre;
    private JLabel contraseña;
    private JLabel confirmar_contraseña;
    private JLabel nivel;
    private JButton botonOk;
    private JButton botonCancelar;

    public VentanaRegistroUsuario(List<Miembro> miembros) {
        this.setMiembros(miembros);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con BoxLayout centrado en Y
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        // Panel para nombre de usuario (label + text field)
        JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        nombre = new JLabel("Nombre de usuario:" + "   ");
        campo_nombreUsuario = new JTextField(20);
        panelNombre.add(nombre);
        panelNombre.add(campo_nombreUsuario);

        // Panel para contraseña (label + text field)
        JPanel panelContraseña = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        contraseña = new JLabel("Contraseña:" + "          ");
        campo_contraseña = new JTextField(20);
        panelContraseña.add(contraseña);
        panelContraseña.add(campo_contraseña);
        
        // Panel para confirmar_contraseña (label + text field)
        JPanel panelConfirmarContraseña = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        confirmar_contraseña = new JLabel("Confirmar Contraseña:");
        campo_confirmar_contraseña = new JTextField(20);
        panelConfirmarContraseña.add(confirmar_contraseña);
        panelConfirmarContraseña.add(campo_confirmar_contraseña);
        
        JPanel panelComboBox = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nivel = new JLabel("Nivel");
        
        JComboBox<String> paisCombo = new JComboBox<>(new String[]{
                "Normal", "Premium"});
        		
        panelComboBox.add(nivel);
        panelComboBox.add(paisCombo);
        
        // Panel para botones (OK y Cancelar)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonOk = new JButton("OK");
        botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonOk);
        panelBotones.add(botonCancelar);

        // Agregar los paneles al panel principal
        panelPrincipal.add(panelNombre);
        panelPrincipal.add(panelContraseña);
        panelPrincipal.add(panelConfirmarContraseña);
        panelPrincipal.add(panelComboBox);
        panelPrincipal.add(panelBotones);

        // Agregar el panel principal al JFrame
        this.add(panelPrincipal);
        
     // BOTÓN CANCELAR
        botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(
				        VentanaRegistroUsuario.this,
				        "¿Seguro que desea salir?",
				        "¿Salir?",
				        JOptionPane.YES_NO_OPTION
				    );

				    if (opcion == JOptionPane.YES_OPTION) {
				        dispose(); // Cierra la ventana
				    }
				}
        });
        //Boton Ok para añadir el usuario a la lista
        botonOk.addActionListener(e->{
        	String nombre= campo_nombreUsuario.getText();
        	String contraseña=campo_contraseña.getText().trim();
        	String confirmar=campo_confirmar_contraseña.getText();
        	  
        	boolean existe = false; //nos dice si el usuario esta
        	    for (Miembro m : miembros) {
        	        if (m.getNombre().equalsIgnoreCase(nombre)) {//comparamos
        	            existe = true; //si existe salimos del bucle
        	            break;
        	        }
        	    }

        	    if (existe) {
        	        JOptionPane.showMessageDialog(this, "El usuario ya existe"); //si ya fue registrado
        	    } else if(!contraseña.equals(confirmar)) {
        	    	JOptionPane.showMessageDialog(this, "Las contraseñas no son iguales");//si la contraseña ni concuerda con la 2ª vez que se escribe la contraseña
        	    } else {
        	        int IdUsuario = miembros.size() + 1;
        	        Miembro nuevo = new Miembro(IdUsuario, nombre, "apellido", contraseña);
        	        miembros.add(nuevo);
        	        JOptionPane.showMessageDialog(this, "Usuario registrado");//nuevo usuario registrado
        	        dispose(); 
        	    }
        	});
        	
        	
        setVisible(true);
    
    }

	public List<Miembro> getMiembros() {
		return miembros;
	}

	public void setMiembros(List<Miembro> miembros) {
		this.miembros = miembros;
	}
}
