
package it.univr.MusicValley.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.univr.MusicValley.gui.components.CardManager;
import it.univr.MusicValley.gui.controllers.*;
import it.univr.MusicValley.gui.models.*;
import it.univr.MusicValley.gui.views.*;

public class Gui extends JFrame {
	
	public static enum Views {
		MAIN("MainView"),
		SEARCH("SearchView"),
		CART("CartView"),
		GENRE("GenreView"),
		PRODUCT("ProductView"),
		RESULT("ResultView");
		
		private final String view;
		
		Views(String view) {
			this.view = view;
		}
		
		public String toString() {
			return view;
		}
	}
	
	private CardManager cardManager = new CardManager(new JPanel(new CardLayout()));
	
	public Gui() {
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String, Object> controllerMap = new HashMap<String, Object>();
		
		// ----------------------------------------------------------------------------------------
		
		// MODELS
		MainModel mainModel = new MainModel();																		
		GenreModel genreModel = new GenreModel();
		ProductModel productModel = new ProductModel();
		SearchModel searchModel = new SearchModel();
		ResultModel resultModel = new ResultModel();
		CartModel cartModel = new CartModel();
		
		// VIEWS
		HomeView homeView = new HomeView(controllerMap);
		MainView mainView = new MainView(controllerMap);
		GenreView genreView = new GenreView(controllerMap);
		ProductView productView = new ProductView(controllerMap);
		SearchView searchView = new SearchView(controllerMap);
		ResultView resultView = new ResultView(controllerMap);
		CartView cartView = new CartView(controllerMap);
		
		// CONTROLLERS
		HomeController homeController = new HomeController(cardManager, modelMap, homeView);
		MainController mainController = new MainController(cardManager, modelMap, mainView);
		GenreController genreController = new GenreController(cardManager, modelMap, genreView);
		ProductController productController = new ProductController(cardManager, modelMap, productView);
		SearchController searchController = new SearchController(cardManager, modelMap, searchView);
		ResultController resultController = new ResultController(cardManager, modelMap, resultView);
		CartController cartController = new CartController(cardManager, modelMap, cartView);
		
		// ----------------------------------------------------------------------------------------
		
		// CONTROLLER MAP
		controllerMap.put("HomeController", homeController);
		controllerMap.put("MainController", mainController);
		controllerMap.put("GenreController", genreController);
		controllerMap.put("ProductController", productController);
		controllerMap.put("SearchController", searchController);
		controllerMap.put("ResultController", resultController);
		controllerMap.put("CartController", cartController);
		
		// ADD CONTROLLER
		homeView.addController();
		mainView.addController();
		genreView.addController();
		productView.addController();
		searchView.addController();
		resultView.addController();
		cartView.addController();
		
		// OBSERVERS
		LoggedUser.getInstance().addObserver(homeView);
		mainModel.addObserver(mainView);
		genreModel.addObserver(genreView);
		productModel.addObserver(productView);
		searchModel.addObserver(searchView);
		resultModel.addObserver(resultView);
		cartModel.addObserver(cartView);
		
		// MODEL MAP
		modelMap.put("MainModel", mainModel);
		modelMap.put("GenreModel", genreModel);
		modelMap.put("ProductModel", productModel);
		modelMap.put("SearchModel", searchModel);
		modelMap.put("ResultModel", resultModel);
		modelMap.put("CartModel", cartModel);
		
		// CARDS
		cardManager.addCard(mainView, Views.MAIN);
		cardManager.addCard(genreView, Views.GENRE);
		cardManager.addCard(productView, Views.PRODUCT);
		cardManager.addCard(searchView, Views.SEARCH);
		cardManager.addCard(resultView, Views.RESULT);
		cardManager.addCard(cartView, Views.CART);
		
		// -----------------------------------------------------------------------------
		
		mainModel.initialize();
		cartModel.initialize();
		
		cardManager.goToCard(Views.MAIN, false);
		
		// -----------------------------------------------------------------------------
		
		add(homeView, BorderLayout.NORTH);
		add(cardManager.getCardPanel(), BorderLayout.CENTER);
		
	}
	
	// ============================================================================================
	
	public CardManager getCardManager() {
		return cardManager;
	}
	
}
