package it.univr.MusicValley.gui.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.univr.MusicValley.factory.ButtonFactory;
import it.univr.MusicValley.gui.components.Logo;
import it.univr.MusicValley.gui.controllers.HomeController;
import it.univr.MusicValley.gui.models.LoggedUser;
import it.univr.MusicValley.utility.Colors;

public class HomeView extends JPanel implements Observer {
	
	private final Map<String, Object> controllerMap;
	
	private final JButton loginButton = new ButtonFactory("Home", loginButtonString);
	private final JButton registerButton = new ButtonFactory("Home", registerButtonString);
	private final JButton exitButton = new ButtonFactory("Home", exitString);
	private final JButton homeButton = new ButtonFactory("Home", homeString);
	private final JButton searchButton = new ButtonFactory("Home", searchButtonString);
	private final JButton cartButton = new ButtonFactory("Home", cartButtonString);
	private final JLabel authenticatedUserLabel = new JLabel();
	
	private static final int logoImageWidth = 100;
	private static final int logoImageHeight = 100;
	private static final int buttonHeight = 40;
	private static final int buttonTextMargin = 40;
	private static final int buttonPanelWidth = 470;
	private static final int spaceBetweenButtons = 20;
	private static final int spaceFromFrameBorder = 30;
	private static final int welcomeLabelMaxWidth = 320;
	private static final String loginButtonString = "ACCEDI";
	private static final String registerButtonString = "REGISTRATI";
	private static final String exitString = "ESCI";
	private static final String homeString = "HOME";
	private static final String searchButtonString = "CERCA";
	private static final String cartButtonString = "CARRELLO";
	private static final String welcomeString = "BENVENUTO, ";
	private static final String welcomeBackString = "BENTORNATO, ";
	private static final String stringFormat = "<html><nobr><span>%s<font color='rgb(238,0,0)'>%s</font>!</span></nobr></html>";
	
	// --------------------------------------------------------------------------------------------
	
	public HomeView(Map<String, Object> controllerMap) {
		
		this.controllerMap = controllerMap;
		
		setLayout(new BorderLayout());
		setBackground(Colors.WHITE252);
		setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Colors.GRAY100));
		add(createLeftHomePanel(), BorderLayout.WEST);
		add(createLogo(), BorderLayout.CENTER);
		add(createRightHomePanel(), BorderLayout.EAST);
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createLeftHomePanel() {
		
		loginButton.setPreferredSize(new Dimension((int) loginButton.getPreferredSize().getWidth() + buttonTextMargin, buttonHeight));
		registerButton.setPreferredSize(new Dimension((int) registerButton.getPreferredSize().getWidth() + buttonTextMargin, buttonHeight));
		authenticatedUserLabel.setFont(new Font("Titillium Web", Font.BOLD, 20));
		authenticatedUserLabel.setVisible(false);
		exitButton.setPreferredSize(new Dimension((int) exitButton.getPreferredSize().getWidth() + buttonTextMargin, buttonHeight));
		exitButton.setVisible(false);
		
		JPanel leftHomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, spaceBetweenButtons, (logoImageHeight - buttonHeight)/2));
		leftHomePanel.setOpaque(false);
		leftHomePanel.setPreferredSize(new Dimension(buttonPanelWidth, logoImageHeight));
		leftHomePanel.setBorder(new EmptyBorder(0, spaceFromFrameBorder, 0, 0));
		leftHomePanel.add(loginButton);
		leftHomePanel.add(registerButton);
		leftHomePanel.add(authenticatedUserLabel);
		leftHomePanel.add(exitButton);
		
		return leftHomePanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JLabel createLogo() {
		return new Logo(Colors.WHITE252, logoImageWidth, logoImageHeight);
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createRightHomePanel() {
		
		homeButton.setPreferredSize(new Dimension((int) homeButton.getPreferredSize().getWidth() + buttonTextMargin, buttonHeight));
		searchButton.setPreferredSize(new Dimension((int) searchButton.getPreferredSize().getWidth() + buttonTextMargin, buttonHeight));
		cartButton.setPreferredSize(new Dimension((int) cartButton.getPreferredSize().getWidth() + buttonTextMargin, buttonHeight));
		
		JPanel rightHomePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, spaceBetweenButtons, (logoImageHeight - buttonHeight)/2));
		rightHomePanel.setOpaque(false);
		rightHomePanel.setPreferredSize(new Dimension(buttonPanelWidth, logoImageHeight));
		rightHomePanel.setBorder(new EmptyBorder(0, 0, 0, spaceFromFrameBorder));
		rightHomePanel.add(homeButton);
		rightHomePanel.add(searchButton);
		rightHomePanel.add(cartButton);
		
		return rightHomePanel;
	}
	
	// ============================================================================================
	
	public void addController() {
		
		HomeController homeController = (HomeController) controllerMap.get("HomeController");
		
		loginButton.addActionListener(homeController);
		registerButton.addActionListener(homeController);
		exitButton.addActionListener(homeController);
		searchButton.addActionListener(homeController);
		cartButton.addActionListener(homeController);
		homeButton.addActionListener(homeController);
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void update(Observable obs, Object obj) {
		
		if (obs instanceof LoggedUser) {
			LoggedUser loggedUser = (LoggedUser) obs;
			
			if (!loggedUser.getUsername().equals("") && !loggedUser.getUsername().equals(null)) {
				
				if (loggedUser.getNewUser()) {
					authenticatedUserLabel.setText(String.format(
						stringFormat, welcomeString, loggedUser.getUsername().toUpperCase()
					));
				} else {
					authenticatedUserLabel.setText(String.format(
						stringFormat, welcomeBackString, loggedUser.getUsername().toUpperCase()
					));
				}
				
				authenticatedUserLabel.setPreferredSize(null);
				authenticatedUserLabel.setPreferredSize(new Dimension(
					Math.min((int) authenticatedUserLabel.getPreferredSize().getWidth(), welcomeLabelMaxWidth), buttonHeight)
				);
				
				loginButton.setVisible(false);
				registerButton.setVisible(false);
				authenticatedUserLabel.setVisible(true);
				exitButton.setVisible(true);
				
			} else {
				authenticatedUserLabel.setVisible(false);
				exitButton.setVisible(false);
				authenticatedUserLabel.setText("");
				loginButton.setVisible(true);
				registerButton.setVisible(true);
			}
		}
	}
	
	// ============================================================================================
	
	public JButton getLoginButton()		{ return loginButton; }
	public JButton getRegisterButton()	{ return registerButton; }
	public JButton getExitButton()		{ return exitButton; }
	public JButton getSearchButton()	{ return searchButton; }
	public JButton getCartButton()		{ return cartButton; }
	public JButton getHomeButton()		{ return homeButton; }
	
}
