
package it.univr.MusicValley.gui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.Enumeration;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import it.univr.MusicValley.factory.ButtonFactory;
import it.univr.MusicValley.factory.LabelFactory;
import it.univr.MusicValley.factory.TextFieldFactory;
import it.univr.MusicValley.gui.components.HeaderPanel;
import it.univr.MusicValley.gui.controllers.SearchController;
import it.univr.MusicValley.gui.models.SearchModel;
import it.univr.MusicValley.utility.Colors;

public class SearchView extends JPanel implements Observer {
	
	private Map<String, Object> controllerMap;
	
	private JPanel headerPanel = new HeaderPanel("RICERCA", false);
	private JTextField toSearchTextField = new TextFieldFactory("Input", null, fieldTextFieldFontSize, 50);
	private JRadioButton artistRadioButton = new JRadioButton("Artista");
	private JRadioButton albumRadioButton = new JRadioButton("Album");
	private JRadioButton genreRadioButton = new JRadioButton("Genere"); 
	private JRadioButton componentRadioButton = new JRadioButton("Partecipante");
	private ButtonGroup searchGroup = new ButtonGroup();
	private JComboBox<Integer> minPriceComboBox = new JComboBox<Integer>(priceValues);
	private JComboBox<Integer> maxPriceComboBox = new JComboBox<Integer>(priceValues);
	private JButton searchButton = new ButtonFactory("DoButton", "CERCA");
	
	private static final Integer[] priceValues = {0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
	private static final int fieldTextFieldFontSize = 15;
	private static final int fieldHeight = 35;
	private static final int fieldLabelFontSize = 16;
	
	private static final String toResearchString = "Da ricercare";
	private static final String minPriceString = "Prezzo min.";
	private static final String maxPriceString = "Prezzo max.";
	
	// --------------------------------------------------------------------------------------------
	
	public SearchView(Map<String, Object> controllerMap) {
		
		this.controllerMap = controllerMap;
		
		setLayout(new BorderLayout());
		add(headerPanel, BorderLayout.NORTH);
		add(createSearchBoxPanel(), BorderLayout.CENTER);
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createSearchBoxPanel() {
		
		JPanel searchBoxPanel = new JPanel();
		searchBoxPanel.setLayout(new BoxLayout(searchBoxPanel, BoxLayout.Y_AXIS));
		searchBoxPanel.setBackground(Colors.WHITE);
		searchBoxPanel.add(createToSearchPanel());
		searchBoxPanel.add(createSearchRadioPanel());
		searchBoxPanel.add(createSearchPricePanel());
		searchBoxPanel.add(createSearchButtonPanel());
		
		JPanel searchGridPanel = new JPanel(new GridBagLayout());
		searchGridPanel.setBackground(Colors.WHITE);
		searchGridPanel.add(searchBoxPanel);
		searchGridPanel.setBorder(new EmptyBorder(0, 0, 100, 0));
		
		return searchGridPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createToSearchPanel() {
		
		toSearchTextField.setPreferredSize(new Dimension(500, fieldHeight));
		
		JLabel toSearchLabel = new LabelFactory("Form", toResearchString, fieldLabelFontSize);
		toSearchLabel.setHorizontalAlignment(JLabel.LEFT);
		
		JPanel toSearchSouthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
		toSearchSouthPanel.setBackground(Color.WHITE);
		toSearchSouthPanel.add(toSearchTextField);
		
		JPanel toSearchPanel = new JPanel(new BorderLayout());
		toSearchPanel.setBackground(Color.WHITE);
		toSearchPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
		toSearchPanel.add(toSearchLabel, BorderLayout.NORTH);
		toSearchPanel.add(toSearchSouthPanel, BorderLayout.SOUTH);
		toSearchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) toSearchPanel.getPreferredSize().getHeight()));
		
		return toSearchPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createSearchRadioPanel() {
		
		artistRadioButton.setBackground(Colors.WHITE);
		albumRadioButton.setBackground(Colors.WHITE);
		genreRadioButton.setBackground(Color.WHITE);
		componentRadioButton.setBackground(Color.WHITE);
		
		artistRadioButton.setFont(new Font("Titillium Web", Font.PLAIN, fieldLabelFontSize));
		albumRadioButton.setFont(new Font("Titillium Web", Font.PLAIN, fieldLabelFontSize));
		genreRadioButton.setFont(new Font("Titillium Web", Font.PLAIN, fieldLabelFontSize));
		componentRadioButton.setFont(new Font("Titillium Web", Font.PLAIN, fieldLabelFontSize));
		
		artistRadioButton.setFocusable(false);
		albumRadioButton.setFocusable(false);
		genreRadioButton.setFocusable(false);
		componentRadioButton.setFocusable(false);
		
		searchGroup.add(artistRadioButton);
		searchGroup.add(albumRadioButton);
		searchGroup.add(genreRadioButton);
		searchGroup.add(componentRadioButton);
		
		artistRadioButton.setSelected(true);
		
		JPanel searchRadioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
		searchRadioPanel.setBackground(Color.WHITE);
		searchRadioPanel.add(artistRadioButton);
		searchRadioPanel.add(albumRadioButton);
		searchRadioPanel.add(genreRadioButton);
		searchRadioPanel.add(componentRadioButton);
		searchRadioPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) searchRadioPanel.getPreferredSize().getHeight()));
		
		return searchRadioPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createSearchPricePanel() {
		
		minPriceComboBox.setPreferredSize(new Dimension(100, fieldHeight));
		minPriceComboBox.setFont(new Font("Titillium Web", Font.PLAIN, 16));
		
		maxPriceComboBox.setFont(new Font("Titillium Web", Font.PLAIN, 16));
		maxPriceComboBox.setPreferredSize(new Dimension(100, fieldHeight));
		
		JLabel minPriceLabel = new LabelFactory("Form", minPriceString, fieldLabelFontSize);
		minPriceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel minPriceSouthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		minPriceSouthPanel.setBackground(Color.WHITE);
		minPriceSouthPanel.add(minPriceComboBox);
		
		JPanel minPricePanel = new JPanel(new BorderLayout());
		minPricePanel.setBackground(Color.WHITE);
		minPricePanel.add(minPriceLabel, BorderLayout.NORTH);
		minPricePanel.add(minPriceSouthPanel, BorderLayout.SOUTH);
		
		JLabel maxPriceLabel = new LabelFactory("Form", maxPriceString, fieldLabelFontSize);
		maxPriceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel maxPriceSouthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		maxPriceSouthPanel.setBackground(Color.WHITE);
		maxPriceSouthPanel.add(maxPriceComboBox);

		JPanel maxPricePanel = new JPanel(new BorderLayout());
		maxPricePanel.setBackground(Color.WHITE);
		maxPricePanel.add(maxPriceLabel, BorderLayout.NORTH);
		maxPricePanel.add(maxPriceSouthPanel, BorderLayout.SOUTH);
		
		JPanel searchPricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
		searchPricePanel.setBackground(Color.WHITE);
		searchPricePanel.add(minPricePanel);
		searchPricePanel.add(maxPricePanel);
		searchPricePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) searchPricePanel.getPreferredSize().getHeight()));
		
		return searchPricePanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createSearchButtonPanel() {
		
		searchButton.setPreferredSize(new Dimension((int) searchButton.getPreferredSize().getWidth() + 40, 40));
		
		JPanel searchButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
		searchButtonPanel.add(searchButton);
		searchButtonPanel.setBackground(Color.WHITE);
		searchButtonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) searchButtonPanel.getPreferredSize().getHeight()));
		
		return searchButtonPanel;
	}
	
	// ============================================================================================
	
	public void addController(){
		searchButton.addActionListener((SearchController) controllerMap.get("SearchController"));
	}
	
	//---------------------------------------------------------------------------------------------
	
	@Override
	public void update(Observable obs, Object obj) {
		
		if (obs instanceof SearchModel) {
			SearchModel searchModel = (SearchModel) obs;
			
			toSearchTextField.setText(searchModel.getToSearch());
			minPriceComboBox.setSelectedItem(searchModel.getMinPrice());
			maxPriceComboBox.setSelectedItem(searchModel.getMaxPrice());
			
			for (Enumeration<AbstractButton> buttons = searchGroup.getElements(); buttons.hasMoreElements(); ) {
				AbstractButton button = buttons.nextElement();
	            
	            if (button.getText().equals(searchModel.getSelectedRadioButton()))
	            	button.setSelected(true);
			}	
		}
	}
	
	// ============================================================================================
	
	public JTextField getTitle()					{ return toSearchTextField; }
	public ButtonGroup getButtonGroup()				{ return searchGroup; }
	public JComboBox<Integer> getMinPriceComboBox()	{ return minPriceComboBox; }
	public JComboBox<Integer> getMaxPriceComboBox()	{ return maxPriceComboBox; }
	public JButton getSearchButton()				{ return searchButton; }
	
}
