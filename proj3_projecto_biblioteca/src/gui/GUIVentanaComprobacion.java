package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUIVentanaComprobacion extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campoUsuario;
    private JPasswordField campoContra;
    private JButton botonEnviar;
    private GUIDevolverLibro ventana;
    
    public GUIVentanaComprobacion(GUIDevolverLibro ventana) {
        super(ventana, "Ventana Hija", true); // Modal
        this.ventana = ventana;
        setSize(300, 200);
        setLayout(new java.awt.FlowLayout());

        campoUsuario = new JTextField(20);
        campoContra = new JPasswordField(20);
        botonEnviar = new JButton("Aceptar");

        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoUsuario = campoUsuario.getText();
                String textoContra = campoContra.getText();
                ventana.recibirTexto(textoUsuario,textoContra);  
                dispose(); 
            }
        });
        
        add(new JLabel("Introduce Usuario:"));
        add(campoUsuario);
        add(new JLabel("Introduce password:"));
        add(campoContra);
        add(botonEnviar);
        setLocationRelativeTo(ventana);
    }
}
