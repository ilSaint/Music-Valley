package it.univr.MusicValley.authentication.views;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import it.univr.MusicValley.authentication.models.LoginModel;
import it.univr.MusicValley.factory.ButtonFactory;
import it.univr.MusicValley.factory.LabelFactory;
import it.univr.MusicValley.factory.PasswordFieldFactory;
import it.univr.MusicValley.factory.TextFieldFactory;
import it.univr.MusicValley.gui.components.Logo;
import it.univr.MusicValley.utility.Colors;
import it.univr.MusicValley.utility.FormLimits;
 
public class LoginView extends JDialog implements Observer {
	
	private final JFrame parentFrame;
	
	private final JLabel logo = new Logo(Colors.WHITE, logoImageWidth, logoImageHeight);
	private final JLabel usernameLabel = new LabelFactory("Form", userLabelString, fieldLabelFontSize);
	private JTextField usernameTextField = new TextFieldFactory("Input", "", 15, FormLimits.usernameCharLimit);
	private final JLabel passwordLabel = new LabelFactory("Form", passwordLabelString, fieldLabelFontSize);
	private PasswordFieldFactory passwordField = new PasswordFieldFactory("Input", passwordCharLimit);
	private final JLabel errorMessage = new LabelFactory("FormError", errorMessageString, fieldLabelFontSize);
	private final JButton loginButton = new ButtonFactory("DoButton", loginButtonString);
	
	private static final int logoImageWidth = 100;
	private static final int logoImageHeight = 100;
	private static final int fieldWidth = 300;
	private static final int fieldHeight = 35;
	private static final int fieldLabelFontSize = 16;
	private static final int passwordCharLimit = 20;
	private static final int loginButtonPanelWidth = 380;
	private static final int loginButtonPanelHeight = 100;
	private static final int loginButtonHeight = 40;
	private static final String windowTitle = " Accesso";
	private static final String userLabelString = "Nome utente";
	private static final String passwordLabelString = "Password";
	private static final String errorMessageString = "Nome utente o password non corretta";
	private static final String loginButtonString = "ACCEDI";
	
	// --------------------------------------------------------------------------------------------
	
	public LoginView(JFrame parentFrame) {
		super(parentFrame, windowTitle, true);
		this.parentFrame = parentFrame;
		initialize();		
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void initialize() {
		setLayout(new BorderLayout());
		add(logo, BorderLayout.NORTH);
		add(createFormPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
		pack();
		setResizable(false);
		setLocationRelativeTo(parentFrame);
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createFormPanel() {
		
		usernameLabel.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
		usernameLabel.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
		usernameTextField.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
		usernameTextField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
		passwordLabel.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
		passwordLabel.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
		passwordField.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
		passwordField.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
		errorMessage.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
		errorMessage.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
		
		JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		formPanel.setBackground(Colors.WHITE);
		formPanel.setPreferredSize(new Dimension(loginButtonPanelWidth, fieldHeight*5 - 10));
		formPanel.add(usernameLabel);
		formPanel.add(usernameTextField);
		formPanel.add(passwordLabel);
		formPanel.add(passwordField);
		formPanel.add(errorMessage);
		
		return formPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createButtonPanel() {
		
		loginButton.setPreferredSize(new Dimension((int) loginButton.getPreferredSize().getWidth() + 30, loginButtonHeight));
		
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setPreferredSize(new Dimension(loginButtonPanelWidth, loginButtonPanelHeight));
		buttonPanel.setBackground(Colors.WHITE);
		buttonPanel.add(this.loginButton, new GridBagConstraints());
		
		return buttonPanel;
	}
	
	// ============================================================================================
	
	public JTextField getUsernameTextField()	{ return usernameTextField; } 
	public JPasswordField getPasswordField()	{ return passwordField; }
	public JLabel getErrorMessage()				{ return errorMessage; }
	public JButton getLoginButton()				{ return loginButton; }
	
	// ============================================================================================
	
	public void hideErrorMessage() {
		errorMessage.setForeground(Colors.WHITE);
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void update(Observable obs, Object obj) {
		
		if (obs instanceof LoginModel) {
			LoginModel loginModel = (LoginModel) obs;
			
			if (!loginModel.getSucceed()) {
				usernameTextField.setText("");
				passwordField.setText("");
				errorMessage.setForeground(Colors.RED238);
			}
		}
		
	}
	
}
