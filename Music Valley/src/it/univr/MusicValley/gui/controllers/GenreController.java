package it.univr.MusicValley.gui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import it.univr.MusicValley.gui.components.CardManager;
import it.univr.MusicValley.gui.views.GenreView;

public class GenreController implements ActionListener {
	
	private CardManager cardManager;
	//private Map<String, Object> modelMap;
	private GenreView genreView;
	
	// --------------------------------------------------------------------------------------------
	
	public GenreController(CardManager cardManager, Map<String, Object> modelMap, GenreView genreView) {
		this.cardManager = cardManager;
		//this.modelMap = modelMap;
		this.genreView = genreView;
	}

	// --------------------------------------------------------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == genreView.getBackButton())
			cardManager.goToPreviousCard();
	}
	
}
