package com.dam.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.dam.control.LoginControl;
import com.dam.model.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JButton;

public class VLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int ALTO = 250;
	private static final int ANCHO = 400 ;
	
	public static final String BTN_ACCEDER = "ACCEDER";
	public static final String BTN_REGISTRAR = "REGISTRAR";
	
	
	private JTextField txtUsuario;
	private JPasswordField txtPwd;
	private JButton btnAcceder;
	private JButton btnRegistrar;

	public VLogin() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuario.setBounds(27, 52, 93, 25);
		getContentPane().add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(27, 88, 117, 25);
		getContentPane().add(lblPassword);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(157, 54, 144, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPwd = new JPasswordField();
		txtPwd.setBounds(157, 90, 144, 20);
		getContentPane().add(txtPwd);
		
		btnAcceder = new JButton(BTN_ACCEDER);
		btnAcceder.setBounds(60, 146, 102, 34);
		getContentPane().add(btnAcceder);
		
		btnRegistrar = new JButton(BTN_REGISTRAR);
		btnRegistrar.setBounds(222, 146, 102, 34);
		getContentPane().add(btnRegistrar);
		
		setSize(ANCHO, ALTO);
		
		centrarVentana();
	}

	private void centrarVentana() {
		// asignamos tamaï¿½o a la ventana 
		setPreferredSize(new Dimension(ANCHO, ALTO));  
		// Se obtienen las dimensiones en pixels de la pantalla.       
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		// Se obtienen las dimensiones en pixels de la ventana.       
		Dimension ventana = this.getPreferredSize();               
		// Una cuenta para situar la ventana en el centro de la pantalla.       
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);		
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	public void setControlador(LoginControl control) {
		btnAcceder.addActionListener(control);
		btnRegistrar.addActionListener(control);
	}
	
	public Usuario obtenerUsuario() {
		Usuario user = null;
		
		String nomUsuario = txtUsuario.getText();
		if (nomUsuario.isBlank()) {
			mostrarError("Debe Introducir el nombre de Usuario");
		} else {
			@SuppressWarnings("deprecation")
			String pwd = txtPwd.getText().trim();
			
			String error = validarPassword(pwd, nomUsuario);
			
			if (!error.isEmpty()) {
				mostrarError(error);
			} else {
				user = new Usuario(nomUsuario, pwd);
			}
		}

		
		return user;
	}

	private String validarPassword(String pwd, String nomUsuario) {
		
		String error = "";
		
		if (pwd.isBlank()) {
			error = "Debe introducir la contraseña";
		} else if (pwd.length() < 8 || pwd.length() > 20) {
			error = "La contraseña debe tener entre 8 y 20 caracteres";
		} else if (pwd.equals(nomUsuario)) {
			error = "El nombre de usuario y la coontraseña no pueden ser los mismos";	
		} else if (!pwd.matches(".*[A-Z].*")) {
			error = "La contraseña debe tener al menos una mayuscula";	
		} else if (!pwd.matches(".*[0-9].*")) {
			error = "La contraseña debe tener al menos un numero";
		} else if (pwd.matches(".*[&+_*].*")) {
			error = "La contraseña no puede contener caracteres distintos de letras, numeros y &,+,_,* ";
		}
		
		return error;
	}

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this , mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void cargarUsuario(String nomUsuario) {
		txtUsuario.setText(nomUsuario);
		
	}
}
