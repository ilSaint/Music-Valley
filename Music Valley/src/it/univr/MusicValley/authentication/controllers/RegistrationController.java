package it.univr.MusicValley.authentication.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.univr.MusicValley.authentication.models.RegistrationModel;
import it.univr.MusicValley.authentication.views.RegistrationView;
import it.univr.MusicValley.gui.models.LoggedUser;

public class RegistrationController implements ActionListener {
	
	private RegistrationView registrationView;
	private RegistrationModel registrationModel;
	

	// --------------------------------------------------------------------------------------------
		
	public RegistrationController(RegistrationModel registrationModel, RegistrationView registrationView) {
		this.registrationModel = registrationModel;
		this.registrationView = registrationView;
	}
	
	// --------------------------------------------------------------------------------------------
		
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == registrationView.getRegisterButton()) {
			
			registrationModel.setFirstName(registrationView.getFirstNameTextField().getText().trim());
			registrationModel.setLastName(registrationView.getLastNameTextField().getText().trim());
			registrationModel.setUsername(registrationView.getUsernameTextField().getText().trim());
			registrationModel.setPassword1(registrationView.getPassword1Field().getPassword());
			registrationModel.setPassword2(registrationView.getPassword2Field().getPassword());
			registrationModel.setFiscalCode(registrationView.getFiscalCodeTextField().getText().trim());
			registrationModel.setRegion(registrationView.getRegionComboBox().getSelectedItem().toString().trim());
			registrationModel.setCity(registrationView.getCityTextField().getText().trim());
			registrationModel.setCap(registrationView.getCapTextField().getText().trim());
			registrationModel.setAddress(registrationView.getAddressTextField().getText().trim());
			registrationModel.setCivicNumber(registrationView.getCivicNumberTextField().getText().trim());
			registrationModel.setPhoneNumber(registrationView.getPhoneNumberTextField().getText().trim());
			registrationModel.setCellphoneNumber(registrationView.getCellphoneNumberTextField().getText().trim());
			
			registrationModel.verifyFields();
			
			if (registrationModel.getSucceed()) {
				registrationModel.registerUser();
				LoggedUser.getInstance().setLoggedUser(registrationModel.getUsername(), true);
				registrationView.dispose();
			} else {
				registrationView.showErrorMessage(registrationModel.getErrorMessage());
			}
		}
	}
	
}
