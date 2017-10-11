package it.univr.MusicValley.gui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import it.univr.MusicValley.gui.components.CardManager;
import it.univr.MusicValley.gui.views.ResultView;

public class ResultController implements ActionListener {
	
	private CardManager cardManager;
	//private Map<String, Object> modelMap;
	private ResultView resultView;
	
	// --------------------------------------------------------------------------------------------
	
	public ResultController(CardManager cardManager, Map<String, Object> modelMap, ResultView resultView) {
		this.cardManager = cardManager;
		//this.modelMap = modelMap;
		this.resultView = resultView;
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == resultView.getBackButton())
			cardManager.goToPreviousCard();
	}
	
}
