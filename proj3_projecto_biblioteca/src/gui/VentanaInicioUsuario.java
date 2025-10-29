package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaInicioUsuario extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField campo_nombreUsuario;
    private JTextField campo_contraseña;
    private JLabel nombre;
    private JLabel contraseña;
    private JButton botonOk;
    private JButton botonCancelar;

    public VentanaInicioUsuario() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Biblioteca");
        setSize(370, 160);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Panel principal con BoxLayout centrado en Y
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        // Panel para nombre de usuario (label + text field)
        JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nombre = new JLabel("  " + "Usuario:" + "  ");
        campo_nombreUsuario = new JTextField(20);
        panelNombre.add(nombre);
        panelNombre.add(campo_nombreUsuario);

        // Panel para contraseña (label + text field)
        JPanel panelContraseña = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contraseña = new JLabel("Contraseña:");
        contraseña.setAlignmentX(LEFT_ALIGNMENT);
        campo_contraseña = new JTextField(20);
        panelContraseña.add(contraseña);
        panelContraseña.add(campo_contraseña);

        // Panel para botones (OK y Cancelar)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        botonOk = new JButton("OK");
        botonCancelar = new JButton("Cancelar");
        panelBotones.add(botonOk);
        panelBotones.add(botonCancelar);

        // Agregar los paneles al panel principal
        panelPrincipal.add(panelNombre);
        panelPrincipal.add(panelContraseña);
        panelPrincipal.add(panelBotones);
        
        // Agregar el panel principal al JFrame
        this.add(panelPrincipal);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaInicioUsuario();
    }
}

