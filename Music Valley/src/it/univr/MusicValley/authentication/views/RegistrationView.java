package it.univr.MusicValley.authentication.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import it.univr.MusicValley.factory.ButtonFactory;
import it.univr.MusicValley.factory.LabelFactory;
import it.univr.MusicValley.factory.PasswordFieldFactory;
import it.univr.MusicValley.factory.TextFieldFactory;
import it.univr.MusicValley.gui.components.Logo;
import it.univr.MusicValley.gui.components.Scrollable;
import it.univr.MusicValley.utility.Colors;
import it.univr.MusicValley.utility.FormLimits;

public class RegistrationView extends JDialog implements Observer {

	private final JFrame parentFrame;
	
	private final JLabel logo = new Logo(Colors.WHITE, logoImageWidth, logoImageHeight);
	private final JLabel firstNameLabel = new LabelFactory("Form", firstNameLabelString, fieldLabelFontSize);
	private final JLabel lastNameLabel = new LabelFactory("Form", lastNameLabelString, fieldLabelFontSize);
	private final JLabel usernameLabel = new LabelFactory("Form", usernameLabelString, fieldLabelFontSize);
	private final JLabel passwordLabel1 = new LabelFactory("Form", password1LabelString, fieldLabelFontSize);
	private final JLabel passwordLabel2 = new LabelFactory("Form", password2LabelString, fieldLabelFontSize);
	private final JLabel fiscalCodeLabel = new LabelFactory("Form", fiscalCodeLabelString, fieldLabelFontSize);
	private final JLabel regionLabel = new LabelFactory("Form", regionLabelString, fieldLabelFontSize);
	private final JLabel cityLabel = new LabelFactory("Form", cityLabelString, fieldLabelFontSize);
	private final JLabel capLabel = new LabelFactory("Form", capLabelString, fieldLabelFontSize);
	private final JLabel addressLabel = new LabelFactory("Form", addressLabelString, fieldLabelFontSize);
	private final JLabel civicNumberLabel = new LabelFactory("Form", civicNumberLabelString, fieldLabelFontSize);
	private final JLabel phoneNumberLabel = new LabelFactory("Form", phoneNumberLabelString, fieldLabelFontSize);
	private final JLabel cellphoneNumberLabel = new LabelFactory("Form", cellphoneNumberLabelString, fieldLabelFontSize);
	
	private JTextField firstNameTextField = new TextFieldFactory("Input", "", fieldTextFontSize, FormLimits.firstNameCharLimit);
	private JTextField lastNameTextField = new TextFieldFactory("Input", "", fieldTextFontSize, FormLimits.lastNameCharLimit);
	private JTextField usernameTextField = new TextFieldFactory("Input", "", fieldTextFontSize, FormLimits.usernameCharLimit);
	private JPasswordField password1Field = new PasswordFieldFactory("Input", FormLimits.passwordCharLimit);
	private JPasswordField password2Field = new PasswordFieldFactory("Input", FormLimits.passwordCharLimit);
	private JTextField fiscalCodeTextField = new TextFieldFactory("Input", "", fieldTextFontSize, FormLimits.fiscalCodeCharLimit);
	private JComboBox<String> regionComboBox = new JComboBox<String>(regionNames);
	private JTextField cityTextField = new TextFieldFactory("Input", "", fieldTextFontSize, FormLimits.cityCharLimit);
	private JTextField capTextField = new TextFieldFactory("Input", "", fieldTextFontSize, FormLimits.capCharLimit);
	private JTextField addressTextField = new TextFieldFactory("Input", "", fieldTextFontSize, FormLimits.addressCharLimit);
	private JTextField civicNumberTextField = new TextFieldFactory("Input", "", fieldTextFontSize, FormLimits.civicNumberCharLimit);
	private JTextField phoneNumberTextField = new TextFieldFactory("Input", "", fieldTextFontSize, FormLimits.phoneNumberCharLimit);
	private JTextField cellphoneNumberTextField = new TextFieldFactory("Input", "", fieldTextFontSize, FormLimits.cellphoneNumberCharLimit);
	private final JButton registerButton = new ButtonFactory("DoButton", registerButtonString);
	
	private static final int logoImageWidth = 100;
	private static final int logoImageHeight = 100;
	
	private static final int columns = 6;
	private static final int rows = 16;
	private static final int columnWidth = 95;
	private static final int fieldLabelFontSize = 17;
	private static final int fieldHeight = 35;
	private static final int spaceBetweenFields = 8;
	private static final int fieldTextFontSize = 15;
	
	private static final String windowTitle = " Registrazione";
	private static final String firstNameLabelString = "Nome";
	private static final String lastNameLabelString = "Cognome";
	private static final String usernameLabelString = "Nome utente";
	private static final String password1LabelString = "Password";
	private static final String password2LabelString = "Conferma password";
	private static final String fiscalCodeLabelString = "Codice fiscale";
	private static final String regionLabelString = "Regione";
	private static final String cityLabelString = "Città";
	private static final String capLabelString = "CAP";
	private static final String addressLabelString = "Indirizzo";
	private static final String civicNumberLabelString = "Numero civico";
	private static final String phoneNumberLabelString = "Numero di telefono";
	private static final String cellphoneNumberLabelString = "Numero di cellulare (facoltativo)";
	private static final String[] regionNames = {"Abruzzo", "Basilicata", "Calabria", "Campania", "Emilia-Romagna",
												 "Friuli-Venezia Giulia", "Lazio", "Liguria", "Lombardia", "Marche",
												 "Molise", "Piemonte", "Puglia", "Sardegna", "Sicilia", "Toscana",
												 "Trentino-Alto Adige", "Umbria", "Valle d'Aosta", "Veneto"};
	
	private static final int registerButtonPanelHeight = 100;
	private static final int registerButtonPanelWidth = columns * columnWidth + 70;
	private static final int registerButtonHeight = 40;
	private static final String registerButtonString = "REGISTRATI";
	private static final String errorDialogTitle = " Dati inseriti non validi";
	
	// --------------------------------------------------------------------------------------------
	
	public RegistrationView(JFrame parentFrame) {
		super(parentFrame, windowTitle, true);
		this.parentFrame = parentFrame;
		initialize();
	}
	
	// --------------------------------------------------------------------------------------------
	
	private void initialize() {
		add(logo, BorderLayout.NORTH);
		add(createFormPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.SOUTH);
		setMinimumSize(new Dimension(registerButtonPanelWidth, registerButtonPanelWidth/2));
		pack();
		setResizable(true);
		setLocationRelativeTo(parentFrame);
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JScrollPane createFormPanel() {

		regionComboBox.setFont(new Font("Titillium Web", Font.PLAIN, 16));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[columns];
		gridBagLayout.rowHeights = new int[rows];
		Arrays.fill(gridBagLayout.columnWidths, columnWidth);
		Arrays.fill(gridBagLayout.rowHeights, fieldHeight);
		
		JPanel formPanel = new JPanel();
		formPanel.setBackground(Colors.WHITE);
		formPanel.setLayout(gridBagLayout);
		
		GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
		gbc_firstNameLabel.fill = GridBagConstraints.BOTH;
		gbc_firstNameLabel.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_firstNameLabel.gridwidth = 3;
		gbc_firstNameLabel.gridx = 0;
		gbc_firstNameLabel.gridy = 0;
		
		GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
		gbc_lastNameLabel.fill = GridBagConstraints.BOTH;
		gbc_lastNameLabel.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_lastNameLabel.gridwidth = 3;
		gbc_lastNameLabel.gridx = 3;
		gbc_lastNameLabel.gridy = 0;
		
		GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
		gbc_firstNameTextField.fill = GridBagConstraints.BOTH;
		gbc_firstNameTextField.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_firstNameTextField.gridwidth = 3;
		gbc_firstNameTextField.gridx = 0;
		gbc_firstNameTextField.gridy = 1;
		
		GridBagConstraints gbc_lastNameTextField = new GridBagConstraints();
		gbc_lastNameTextField.fill = GridBagConstraints.BOTH;
		gbc_lastNameTextField.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_lastNameTextField.gridwidth = 3;
		gbc_lastNameTextField.gridx = 3;
		gbc_lastNameTextField.gridy = 1;
		
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.fill = GridBagConstraints.BOTH;
		gbc_usernameLabel.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_usernameLabel.gridwidth = 6;
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 2;
		
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.fill = GridBagConstraints.BOTH;
		gbc_usernameTextField.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_usernameTextField.gridwidth = 6;
		gbc_usernameTextField.gridx = 0;
		gbc_usernameTextField.gridy = 3;
		
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.fill = GridBagConstraints.BOTH;
		gbc_passwordLabel.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_passwordLabel.gridwidth = 3;
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 4;
		
		GridBagConstraints gbc_passwordLabel2 = new GridBagConstraints();
		gbc_passwordLabel2.fill = GridBagConstraints.BOTH;
		gbc_passwordLabel2.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_passwordLabel2.gridwidth = 3;
		gbc_passwordLabel2.gridx = 3;
		gbc_passwordLabel2.gridy = 4;
		
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_passwordField.gridwidth = 3;
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 5;
		
		GridBagConstraints gbc_passwordField2 = new GridBagConstraints();
		gbc_passwordField2.fill = GridBagConstraints.BOTH;
		gbc_passwordField2.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_passwordField2.gridwidth = 3;
		gbc_passwordField2.gridx = 3;
		gbc_passwordField2.gridy = 5;
		
		GridBagConstraints gbc_fiscalCodeLabel = new GridBagConstraints();
		gbc_fiscalCodeLabel.fill = GridBagConstraints.BOTH;
		gbc_fiscalCodeLabel.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_fiscalCodeLabel.gridwidth = 6;
		gbc_fiscalCodeLabel.gridx = 0;
		gbc_fiscalCodeLabel.gridy = 6;
		
		GridBagConstraints gbc_fiscalCodeTextField = new GridBagConstraints();
		gbc_fiscalCodeTextField.fill = GridBagConstraints.BOTH;
		gbc_fiscalCodeTextField.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_fiscalCodeTextField.gridwidth = 6;
		gbc_fiscalCodeTextField.gridx = 0;
		gbc_fiscalCodeTextField.gridy = 7;
		
		GridBagConstraints gbc_regionLabel = new GridBagConstraints();
		gbc_regionLabel.fill = GridBagConstraints.BOTH;
		gbc_regionLabel.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_regionLabel.gridwidth = 2;
		gbc_regionLabel.gridx = 0;
		gbc_regionLabel.gridy = 8;
		
		GridBagConstraints gbc_cityLabel = new GridBagConstraints();
		gbc_cityLabel.fill = GridBagConstraints.BOTH;
		gbc_cityLabel.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_cityLabel.gridwidth = 2;
		gbc_cityLabel.gridx = 2;
		gbc_cityLabel.gridy = 8;
		
		GridBagConstraints gbc_capLabel = new GridBagConstraints();
		gbc_capLabel.fill = GridBagConstraints.BOTH;
		gbc_capLabel.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_capLabel.gridwidth = 2;
		gbc_capLabel.gridx = 4;
		gbc_capLabel.gridy = 8;
		
		GridBagConstraints gbc_regionTextField = new GridBagConstraints();
		gbc_regionTextField.fill = GridBagConstraints.BOTH;
		gbc_regionTextField.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_regionTextField.gridwidth = 2;
		gbc_regionTextField.gridx = 0;
		gbc_regionTextField.gridy = 9;
		
		GridBagConstraints gbc_cityTextField = new GridBagConstraints();
		gbc_cityTextField.fill = GridBagConstraints.BOTH;
		gbc_cityTextField.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_cityTextField.gridwidth = 2;
		gbc_cityTextField.gridx = 2;
		gbc_cityTextField.gridy = 9;
		
		GridBagConstraints gbc_capTextField = new GridBagConstraints();
		gbc_capTextField.fill = GridBagConstraints.BOTH;
		gbc_capTextField.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_capTextField.gridwidth = 2;
		gbc_capTextField.gridx = 4;
		gbc_capTextField.gridy = 9;
		
		GridBagConstraints gbc_addressLabel = new GridBagConstraints();
		gbc_addressLabel.fill = GridBagConstraints.BOTH;
		gbc_addressLabel.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_addressLabel.gridwidth = 4;
		gbc_addressLabel.gridx = 0;
		gbc_addressLabel.gridy = 10;
		
		GridBagConstraints gbc_civicNumberLabel = new GridBagConstraints();
		gbc_civicNumberLabel.fill = GridBagConstraints.BOTH;
		gbc_civicNumberLabel.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_civicNumberLabel.gridwidth = 2;
		gbc_civicNumberLabel.gridx = 4;
		gbc_civicNumberLabel.gridy = 10;
		
		GridBagConstraints gbc_addressTextField = new GridBagConstraints();
		gbc_addressTextField.fill = GridBagConstraints.BOTH;
		gbc_addressTextField.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_addressTextField.gridwidth = 4;
		gbc_addressTextField.gridx = 0;
		gbc_addressTextField.gridy = 11;
		
		GridBagConstraints gbc_civicNumberTextField = new GridBagConstraints();
		gbc_civicNumberTextField.fill = GridBagConstraints.BOTH;
		gbc_civicNumberTextField.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_civicNumberTextField.gridwidth = 2;
		gbc_civicNumberTextField.gridx = 4;
		gbc_civicNumberTextField.gridy = 11;
		
		GridBagConstraints gbc_phoneNumberLabel = new GridBagConstraints();
		gbc_phoneNumberLabel.fill = GridBagConstraints.BOTH;
		gbc_phoneNumberLabel.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_phoneNumberLabel.gridwidth = 6;
		gbc_phoneNumberLabel.gridx = 0;
		gbc_phoneNumberLabel.gridy = 12;
		
		GridBagConstraints gbc_phoneNumberTextField = new GridBagConstraints();
		gbc_phoneNumberTextField.fill = GridBagConstraints.BOTH;
		gbc_phoneNumberTextField.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_phoneNumberTextField.gridwidth = 6;
		gbc_phoneNumberTextField.gridx = 0;
		gbc_phoneNumberTextField.gridy = 13;
		
		GridBagConstraints gbc_cellphoneNumberLabel = new GridBagConstraints();
		gbc_cellphoneNumberLabel.fill = GridBagConstraints.BOTH;
		gbc_cellphoneNumberLabel.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_cellphoneNumberLabel.gridwidth = 6;
		gbc_cellphoneNumberLabel.gridx = 0;
		gbc_cellphoneNumberLabel.gridy = 14;
		
		GridBagConstraints gbc_cellphoneNumberTextField = new GridBagConstraints();
		gbc_cellphoneNumberTextField.fill = GridBagConstraints.BOTH;
		gbc_cellphoneNumberTextField.insets = new Insets(0, spaceBetweenFields, 0, spaceBetweenFields);
		gbc_cellphoneNumberTextField.gridwidth = 6;
		gbc_cellphoneNumberTextField.gridx = 0;
		gbc_cellphoneNumberTextField.gridy = 15;
		
		formPanel.add(firstNameLabel, gbc_firstNameLabel);
		formPanel.add(lastNameLabel, gbc_lastNameLabel);
		formPanel.add(firstNameTextField, gbc_firstNameTextField);
		formPanel.add(lastNameTextField, gbc_lastNameTextField);
		formPanel.add(usernameLabel, gbc_usernameLabel);
		formPanel.add(usernameTextField, gbc_usernameTextField);
		formPanel.add(passwordLabel1, gbc_passwordLabel);
		formPanel.add(passwordLabel2, gbc_passwordLabel2);
		formPanel.add(password1Field, gbc_passwordField);
		formPanel.add(password2Field, gbc_passwordField2);
		formPanel.add(fiscalCodeLabel, gbc_fiscalCodeLabel);
		formPanel.add(fiscalCodeTextField, gbc_fiscalCodeTextField);
		formPanel.add(regionLabel, gbc_regionLabel);
		formPanel.add(cityLabel, gbc_cityLabel);
		formPanel.add(capLabel, gbc_capLabel);
		formPanel.add(regionComboBox, gbc_regionTextField);
		formPanel.add(cityTextField, gbc_cityTextField);
		formPanel.add(capTextField, gbc_capTextField);
		formPanel.add(addressLabel, gbc_addressLabel);
		formPanel.add(civicNumberLabel, gbc_civicNumberLabel);
		formPanel.add(addressTextField, gbc_addressTextField);
		formPanel.add(civicNumberTextField, gbc_civicNumberTextField);
		formPanel.add(phoneNumberLabel, gbc_phoneNumberLabel);
		formPanel.add(phoneNumberTextField, gbc_phoneNumberTextField);
		formPanel.add(cellphoneNumberLabel, gbc_cellphoneNumberLabel);
		formPanel.add(cellphoneNumberTextField, gbc_cellphoneNumberTextField);
		
		return new Scrollable(formPanel, true, false);
	}

	// --------------------------------------------------------------------------------------------
	
	private JPanel createButtonPanel() {
		
		registerButton.setPreferredSize(new Dimension((int) registerButton.getPreferredSize().getWidth() + 30, registerButtonHeight));
		
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setPreferredSize(new Dimension(registerButtonPanelWidth, registerButtonPanelHeight));
		buttonPanel.setBackground(Colors.WHITE);
		buttonPanel.add(this.registerButton, new GridBagConstraints());
		
		return buttonPanel;
	}

	// ============================================================================================
	
	public JTextField getFirstNameTextField() 			{ return this.firstNameTextField; }
	public JTextField getLastNameTextField() 			{ return this.lastNameTextField; }
	public JTextField getUsernameTextField() 			{ return this.usernameTextField; }
	public JPasswordField getPassword1Field() 			{ return this.password1Field; }
	public JPasswordField getPassword2Field() 			{ return this.password2Field; }
	public JTextField getFiscalCodeTextField()			{ return this.fiscalCodeTextField; }
	public JComboBox<String> getRegionComboBox() 		{ return this.regionComboBox; }
	public JTextField getCityTextField() 				{ return this.cityTextField; }
	public JTextField getCapTextField() 				{ return this.capTextField; }
	public JTextField getAddressTextField() 			{ return this.addressTextField; }
	public JTextField getCivicNumberTextField() 		{ return this.civicNumberTextField; }
	public JTextField getPhoneNumberTextField() 		{ return this.phoneNumberTextField; }
	public JTextField getCellphoneNumberTextField() 	{ return this.cellphoneNumberTextField; }
	public JButton getRegisterButton() 					{ return this.registerButton; }
	
	// ============================================================================================
	

	public void showErrorMessage(String message) {
		UIManager.put("OptionPane.background", Colors.WHITE);
		UIManager.put("Panel.background", Colors.WHITE);
		JOptionPane.showMessageDialog(new JFrame(), message, errorDialogTitle, JOptionPane.WARNING_MESSAGE);
	}
	
	@Override
	public void update(Observable obs, Object obj) {
		
	}
	
}
