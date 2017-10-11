package it.univr.MusicValley.gui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.univr.MusicValley.data.Product;
import it.univr.MusicValley.factory.LabelFactory;
import it.univr.MusicValley.gui.components.HeaderPanel;
import it.univr.MusicValley.gui.components.MarginPanel;
import it.univr.MusicValley.gui.components.Scrollable;
import it.univr.MusicValley.gui.components.WrapLayout;
import it.univr.MusicValley.gui.controllers.ProductController;
import it.univr.MusicValley.gui.controllers.ResultController;
import it.univr.MusicValley.gui.models.ResultModel;
import it.univr.MusicValley.gui.views.components.ProductPreview;

public class ResultView extends JPanel implements Observer {
	
	private Map<String, Object> controllerMap;
	
	private HeaderPanel headerPanel = new HeaderPanel(null, true);
	private JPanel resultCenterPanel = new JPanel();
	private JButton backButton;
	
	private static final JLabel notFoundLabel = new LabelFactory("NotFound", "Prodotto non trovato", 35);
	
	// --------------------------------------------------------------------------------------------
	
	public ResultView(Map<String, Object> controllerMap) {
		
		this.controllerMap = controllerMap;
		backButton = headerPanel.getBackButton();
		resultCenterPanel.setBackground(Color.WHITE);
		
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		add(headerPanel, BorderLayout.NORTH);
		add(new Scrollable(new MarginPanel(resultCenterPanel, 0), true, false), BorderLayout.CENTER);
		
	}
	
	// ============================================================================================
	
	public void addController(){
		backButton.addActionListener((ResultController) controllerMap.get("ResultController"));
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void update(Observable obs, Object obj) {
		
		if (obs instanceof ResultModel) {
			ResultModel resultModel = (ResultModel) obs;
			
			headerPanel.getTitleLabel().setText(resultModel.getTitle().toUpperCase());
			resultCenterPanel.removeAll();
			
			if (resultModel.getResearchList().isEmpty()){
				resultCenterPanel.setLayout(new BorderLayout());
				resultCenterPanel.add(notFoundLabel, BorderLayout.CENTER);
				resultCenterPanel.setBorder(new EmptyBorder(0, 0, 100, 0));
			} else {
				resultCenterPanel.setLayout(new WrapLayout(FlowLayout.CENTER, 0, 0));
				for (Product product: resultModel.getResearchList()) {
					resultCenterPanel.add(new ProductPreview(product, ((ProductController) controllerMap.get("ProductController"))));
				}
			}
		}
	}

	// ============================================================================================
	
	public JButton getBackButton() { return this.backButton; }
	
	
}
