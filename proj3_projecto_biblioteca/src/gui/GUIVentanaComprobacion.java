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
import io.reproductorAudio;

public class GUIVentanaComprobacion extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField campoUsuario;
    private JPasswordField campoContra;
    private JButton botonEnviar;
    private GUIDevolverLibro ventana;
    private reproductorAudio repro;
    
    public GUIVentanaComprobacion(GUIDevolverLibro ventana, Miembro miembro, reproductorAudio reproductor) {
        super(); // Modal
        this.repro = reproductor;
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
                String textoContra = String.valueOf(campoContra.getPassword());
                if((!textoContra.equals(miembro.getpassword())||!textoUsuario.equals(miembro.getNombre()))) {
                	//repro.playSFX("");
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
