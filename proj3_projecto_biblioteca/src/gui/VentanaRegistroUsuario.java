package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaRegistroUsuario extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField campo_nombreUsuario;
    private JTextField campo_contraseña;
    private JTextField campo_confirmar_contraseña;
    private JLabel nombre;
    private JLabel contraseña;
    private JLabel confirmar_contraseña;
    private JLabel nivel;
    private JButton botonOk;
    private JButton botonCancelar;

    public VentanaRegistroUsuario() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaRegistroUsuario();
    }
}
