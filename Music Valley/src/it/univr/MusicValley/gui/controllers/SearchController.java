package it.univr.MusicValley.gui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Map;
import javax.swing.AbstractButton;

import it.univr.MusicValley.gui.Gui;
import it.univr.MusicValley.gui.components.CardManager;
import it.univr.MusicValley.gui.models.ResultModel;
import it.univr.MusicValley.gui.views.SearchView;

public class SearchController implements ActionListener{

	private CardManager cardManager;
	private Map<String, Object> modelMap;
	private SearchView searchView;
	
	// --------------------------------------------------------------------------------------------
	
	public SearchController(CardManager cardManager, Map<String, Object> modelMap, SearchView searchView) {
		this.cardManager = cardManager;
		this.modelMap = modelMap;
		this.searchView = searchView;
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == searchView.getSearchButton()) {
			
			String radioButtonText = null;
			
			for (Enumeration<AbstractButton> buttons = searchView.getButtonGroup().getElements(); buttons.hasMoreElements(); ) {
				AbstractButton button = buttons.nextElement();
	            
	            if (button.isSelected())
	            	radioButtonText = button.getText();
			}
			
			((ResultModel) modelMap.get("ResultModel")).setResultList(
				searchView.getTitle().getText().trim(),
				Integer.parseInt(searchView.getMinPriceComboBox().getSelectedItem().toString()),
				Integer.parseInt(searchView.getMaxPriceComboBox().getSelectedItem().toString()),
				radioButtonText
			);
			
			cardManager.goToCard(Gui.Views.RESULT, false);
		}
	}
	
}