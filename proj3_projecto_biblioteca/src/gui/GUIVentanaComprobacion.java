package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domain.Miembro;
import gui.reproductorAudio;

public class GUIVentanaComprobacion extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campoUsuario;
    private JPasswordField campoContra;
    private JButton botonEnviar;
    private GUIDevolverLibro ventana;
    private reproductorAudio reproductor;
    
    public GUIVentanaComprobacion(GUIDevolverLibro ventana, Miembro miembro, reproductorAudio reproductor) {
        super(); // Modal
        this.reproductor = reproductor;
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
                if((!textoContra.equals(miembro.getpassword())||!textoUsuario.equals(miembro.getNombre()))) {
                	JOptionPane.showMessageDialog(GUIVentanaComprobacion.this, "Usuario no encontrado, inténtalo de nuevo");
                	campoContra.setText("");
                	campoUsuario.setText("");
                }else {
                	ventana.recibirTexto(textoUsuario,textoContra);  
                    dispose();
                }
                 
            }
        });
        
        add(new JLabel("Introduce Usuario:"));
        add(campoUsuario);
        add(new JLabel("Introduce password:"));
        add(campoContra);
        add(botonEnviar);
        setLocationRelativeTo(this.ventana);
    }
}
