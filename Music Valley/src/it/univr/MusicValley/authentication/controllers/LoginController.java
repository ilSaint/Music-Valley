package it.univr.MusicValley.authentication.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import it.univr.MusicValley.authentication.models.LoginModel;
import it.univr.MusicValley.authentication.views.LoginView;
import it.univr.MusicValley.gui.models.LoggedUser;

public class LoginController implements ActionListener, FocusListener {

	private LoginModel loginModel;
	private LoginView loginView;
	
	public LoginController(LoginModel loginModel, LoginView loginView){
		this.loginModel = loginModel;
		this.loginView = loginView;
	}
	
	// -------------------------------------------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == loginView.getLoginButton()) {
			
			loginModel.setUsername(loginView.getUsernameTextField().getText().trim());
			loginModel.setPassword(loginView.getPasswordField().getPassword());
			
			loginModel.authenticationVerification();
			
			if (loginModel.getSucceed()) {
				LoggedUser.getInstance().setLoggedUser(loginModel.getUsername(), false);
				loginView.dispose();
			}
		}
	}
	
	// -------------------------------------------------------------------------------

	@Override
	public void focusGained(FocusEvent e) {
		loginView.hideErrorMessage();
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		
	}
	
}
	
	

