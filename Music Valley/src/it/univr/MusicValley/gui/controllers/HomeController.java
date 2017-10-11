package it.univr.MusicValley.gui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import it.univr.MusicValley.authentication.controllers.LoginController;
import it.univr.MusicValley.authentication.controllers.RegistrationController;
import it.univr.MusicValley.authentication.models.LoginModel;
import it.univr.MusicValley.authentication.models.RegistrationModel;
import it.univr.MusicValley.authentication.views.LoginView;
import it.univr.MusicValley.authentication.views.RegistrationView;
import it.univr.MusicValley.gui.Gui;
import it.univr.MusicValley.gui.components.CardManager;
import it.univr.MusicValley.gui.models.LoggedUser;
import it.univr.MusicValley.gui.models.MainModel;
import it.univr.MusicValley.gui.models.SearchModel;
import it.univr.MusicValley.gui.views.HomeView;

public class HomeController implements ActionListener {
	
	private CardManager cardManager;
	private Map<String, Object> modelMap;
	private HomeView homeView;
	
	// --------------------------------------------------------------------------------------------
	
	public HomeController(CardManager cardManager, Map<String, Object> modelMap, HomeView homeView) {
		this.cardManager = cardManager;
		this.modelMap = modelMap;
		this.homeView = homeView;
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		JButton aeBtn = (JButton) ae.getSource();
		
		if (aeBtn == homeView.getLoginButton()) 			{ loginEvent(); }
		else if (aeBtn == homeView.getRegisterButton()) 	{ registerEvent(); }
		else if (aeBtn == homeView.getExitButton()) 		{ exitEvent(); }
		else if (aeBtn == homeView.getSearchButton()) 		{ searchEvent(); }
		else if (aeBtn == homeView.getCartButton()) 		{ cartEvent(); }
		else if (aeBtn == homeView.getHomeButton()) 		{ mainEvent(); }
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void loginEvent() {
		
		LoginModel loginModel = new LoginModel();
		LoginView loginView = new LoginView((JFrame) SwingUtilities.getWindowAncestor(homeView));
		LoginController loginController = new LoginController(loginModel, loginView);
		
		loginModel.addObserver(loginView);
		
		loginView.getUsernameTextField().addFocusListener(loginController);
		loginView.getPasswordField().addFocusListener(loginController);
		loginView.getLoginButton().addActionListener(loginController);
		loginView.setVisible(true);
		
		if (loginModel.getSucceed()) {
			((MainModel) modelMap.get("MainModel")).setUserPersonalizedListTitle(LoggedUser.getInstance().getUsername());
		}
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void registerEvent() {
		
		RegistrationModel registrationModel = new RegistrationModel();
		RegistrationView registrationView = new RegistrationView((JFrame) SwingUtilities.getWindowAncestor(homeView));
		RegistrationController registrationController = new RegistrationController(registrationModel, registrationView);
		
		registrationModel.addObserver(registrationView);
		
		registrationView.getRegisterButton().addActionListener(registrationController);
		registrationView.setVisible(true);
		
		if (registrationModel.getSucceed()) {
			((MainModel) modelMap.get("MainModel")).setUserPersonalizedListTitle(LoggedUser.getInstance().getUsername());
		}
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void exitEvent() {
		
		LoggedUser.getInstance().userLogout();
		((MainModel) modelMap.get("MainModel")).setUserPersonalizedListTitle(LoggedUser.getInstance().getUsername());
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void mainEvent() {
		
		if (!cardManager.isSameCardAs(Gui.Views.MAIN))
			cardManager.goToCard(Gui.Views.MAIN, true);
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void searchEvent() {
		
		if (!cardManager.isSameCardAs(Gui.Views.SEARCH)) {
			((SearchModel) modelMap.get("SearchModel")).toDefaultModel();
			cardManager.goToCard(Gui.Views.SEARCH, true);
		}
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void cartEvent() {
		
		if (!cardManager.isSameCardAs(Gui.Views.CART))
			cardManager.goToCard(Gui.Views.CART, true);
	}
	
}
