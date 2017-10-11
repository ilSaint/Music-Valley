package it.univr.MusicValley.gui.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;

import it.univr.MusicValley.data.Product;
import it.univr.MusicValley.gui.components.HeaderPanel;
import it.univr.MusicValley.gui.components.Scrollable;
import it.univr.MusicValley.gui.components.WrapLayout;
import it.univr.MusicValley.gui.controllers.GenreController;
import it.univr.MusicValley.gui.controllers.ProductController;
import it.univr.MusicValley.gui.models.GenreModel;
import it.univr.MusicValley.gui.views.components.ProductPreview;
import it.univr.MusicValley.utility.Colors;

public class GenreView extends JPanel implements Observer {
	
	private Map<String, Object> controllerMap;
	
	private JButton backButton;
	private HeaderPanel headerPanel = new HeaderPanel(null, true);
	private JPanel genreAlbumsListPanel = new JPanel(new WrapLayout(FlowLayout.CENTER, 0, 0));
	
	// --------------------------------------------------------------------------------------------
	
	public GenreView(Map<String, Object> controllerMap) {
		this.controllerMap = controllerMap;
		backButton = headerPanel.getBackButton();
		genreAlbumsListPanel.setBackground(Colors.WHITE);
		
		setLayout(new BorderLayout());
		add(headerPanel, BorderLayout.NORTH);
		add(new Scrollable(genreAlbumsListPanel, true, false), BorderLayout.CENTER);
	}
	
	// ============================================================================================
	
	public void addController() {
		backButton.addActionListener((GenreController) controllerMap.get("GenreController"));
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void update(Observable obs, Object obj) {
		
		if (obs instanceof GenreModel) {
			GenreModel genreModel = ((GenreModel) obs);
			
			headerPanel.getTitleLabel().setText(genreModel.getGenreTitle().toUpperCase());
			genreAlbumsListPanel.removeAll();
			
			for (Product product: genreModel.getGenreProducts()) {
				genreAlbumsListPanel.add(new ProductPreview(product, ((ProductController) controllerMap.get("ProductController"))));
			}
		}
	}

	// ============================================================================================
	
	public JButton getBackButton() { return backButton; }
	
}
