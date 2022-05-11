package com.dam.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.dam.model.Usuario;
import com.dam.persistencia.UsuarioPersistencia;
import com.dam.view.VLogin;
import com.dam.view.VMain;
import com.dam.view.VRegistro;

public class LoginControl implements ActionListener{
	
	private VLogin vLogin;
	private VMain vMain;
	private UsuarioPersistencia up;
	private VRegistro vReg;
	private int contIntentos;
	
	public LoginControl(VLogin vLogin, VMain vMain, VRegistro vReg) {
		this.vLogin = vLogin;
		this.vMain = vMain;
		this.vReg = vReg;
		this.up = new UsuarioPersistencia();
		contIntentos = 0;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() instanceof JButton) {
			if (e.getActionCommand().equals(VLogin.BTN_ACCEDER)) {
				acceder();
			} else if (e.getActionCommand().equals(VLogin.BTN_REGISTRAR)) {
				abrirRegistro();
			} else if (e.getActionCommand().equals(VRegistro.BTN_ACEPTAR)) {
				realizarRegistro();
				
			} else if (e.getActionCommand().equals(VRegistro.BTN_CANCELAR)) {
				volverLogin();
			}
		}
		
	}

	private void realizarRegistro() {
		
		Usuario usuario = vReg.obtenerUsuario();
		
		if (usuario != null) {
			
		}
		
	}



	private void volverLogin() {
		vReg.dispose();
		vLogin.hacerVisible();
		
	}



	private void abrirRegistro() {
		vLogin.dispose();
		vReg.hacerVisible();
	}
	
	private void acceder() {
		boolean noAccede = true;
		Usuario usuario = vLogin.obtenerUsuario();
		
		if (usuario != null) {
			contIntentos++;
			String pwd = up.consultarPwdPorUser(usuario.getUsuario());
			if (pwd == null) {
				vLogin.mostrarError("El usuario introducido no existe");
				vLogin.mostrarError("Te quedan: " + (3 - contIntentos) + " intentos");

			} else if (!pwd.equals(usuario.getPwd())) {
				vLogin.mostrarError("La contraseña introducida no es correcta");
				vLogin.mostrarError("Te quedan: " + (3 - contIntentos) + " intentos");

			} else {
				noAccede = false;
				vLogin.dispose();
				vMain.hacerVisible();
			}
			
			if (noAccede && contIntentos == 3) {
				vLogin.mostrarError("Se han superado el numero de intentos permitidos");
				System.exit(0);
			}
		}
	}

}
