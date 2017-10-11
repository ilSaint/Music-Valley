package it.univr.MusicValley.gui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;

import it.univr.MusicValley.gui.Gui;
import it.univr.MusicValley.gui.components.CardManager;
import it.univr.MusicValley.gui.models.GenreModel;
import it.univr.MusicValley.gui.views.MainView;

public class MainController implements ActionListener {
	
	private CardManager cardManager;
	private Map<String, Object> modelMap;
	private MainView mainView;
	
	// --------------------------------------------------------------------------------------------
	
	public MainController(CardManager cardManager, Map<String, Object> modelMap, MainView mainView) {
		this.cardManager = cardManager;
		this.modelMap = modelMap;
		this.mainView = mainView;
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		for (JButton btn: this.mainView.getGenresButtons()) {
			if (btn.equals(ae.getSource())) {
				String genre = ((JButton) ae.getSource()).getText();
				
				((GenreModel) modelMap.get("GenreModel")).setGenreTitle(genre);
				((GenreModel) modelMap.get("GenreModel")).setProducts(genre);
				
				cardManager.goToCard(Gui.Views.GENRE, false);
				
				break;
			}
		}
		
	}
	
}
