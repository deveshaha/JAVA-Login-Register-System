package com.dam.main;

import java.awt.EventQueue;

import com.dam.control.LoginControl;
import com.dam.view.VLogin;
import com.dam.view.VMain;
import com.dam.view.VRegistro;

public class InicioLogin {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VLogin vLogin = new VLogin();
				VMain vMain = new VMain();
				VRegistro vReg = new VRegistro();
				
				LoginControl control = new LoginControl(vLogin, vMain, vReg);
				
				vLogin.setControlador(control);
				vReg.setControlador(control);

				vLogin.hacerVisible();
				
			}
		});

	}

}
