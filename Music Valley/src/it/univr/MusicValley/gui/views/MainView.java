package it.univr.MusicValley.gui.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import it.univr.MusicValley.data.Product;
import it.univr.MusicValley.factory.ButtonFactory;
import it.univr.MusicValley.factory.LabelFactory;
import it.univr.MusicValley.gui.components.Scrollable;
import it.univr.MusicValley.gui.components.WrapLayout;
import it.univr.MusicValley.gui.controllers.MainController;
import it.univr.MusicValley.gui.controllers.ProductController;
import it.univr.MusicValley.gui.models.Database;
import it.univr.MusicValley.gui.models.MainModel;
import it.univr.MusicValley.gui.views.components.ProductPreview;
import it.univr.MusicValley.utility.Colors;

public class MainView extends JPanel implements Observer {
	
	private Map<String, Object> controllerMap;
	
	private String[] genresList  = Database.getInstance().getGenreList();
	private JButton[] genresButtons = new JButton[genresList.length];
	private JPanel topListsPanel = new JPanel();
	
	private static final int labelFontSize = 18;
	
	/*private static final String evidenceTitle = "IN EVIDENZA";
	private static final int evidenceElementsToView = 5;
	private static final int evidenceCoverWidth = 805;
	private static final int evidenceCoverHeight = 280;*/
	
	private static final String genresTitle = "GENERI";
	private static final int buttonWidth = 110;
	private static final int buttonHeight = 30;
	
	// --------------------------------------------------------------------------------------------
	
	public MainView(Map<String, Object> controllerMap) {
		this.controllerMap = controllerMap;
		
		setLayout(new BorderLayout());
		//add(createEvidencePanel(), BorderLayout.NORTH);
		add(createGenrePanel(), BorderLayout.WEST);
		add(createTopListsPanel(), BorderLayout.CENTER);
	}
	
	// --------------------------------------------------------------------------------------------
	
	/*private JPanel createEvidencePanel() {
		
		JLabel evidencePanelLable = new LabelFactory("Main", evidenceTitle, labelFontSize);
		evidencePanelLable.setOpaque(true);
		evidencePanelLable.setBackground(Colors.WHITE);
		
		JPanel evidenceCoversPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		evidenceCoversPanel.setBackground(Colors.WHITE);
		
		for (int i = 0; i < evidenceElementsToView; i++) {
			try {
				Image leftEvidenceCoverImage = ImageIO.read(new File("resources/other/evidence_cover.png")).getScaledInstance(evidenceCoverWidth, evidenceCoverHeight, Image.SCALE_SMOOTH);
				JLabel leftEvidenceCoverLabel = new JLabel(new ImageIcon(leftEvidenceCoverImage));
				leftEvidenceCoverLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
				evidenceCoversPanel.add(leftEvidenceCoverLabel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(evidencePanelLable, BorderLayout.NORTH);
		topPanel.add(evidenceCoversPanel, BorderLayout.CENTER);
		
		return topPanel;
	}*/
	
	// --------------------------------------------------------------------------------------------
	
	private JScrollPane createGenrePanel() {
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBackground(Colors.RED238);
		buttonPanel.setBorder(new EmptyBorder(buttonHeight/2, 25, buttonHeight/2, 0));
		
		for (int i = 0, size = genresList.length; i < size; i++) {
			genresButtons[i] = new ButtonFactory("Genre", genresList[i]);
			genresButtons[i].setMargin(new Insets(20, 0, 20, 20));
			genresButtons[i].setPreferredSize(new Dimension(buttonWidth, buttonHeight));
			buttonPanel.add(genresButtons[i]);
		}
		
		JPanel basePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
		basePanel.setBackground(Colors.WHITE);
		basePanel.add(buttonPanel);
		
		JPanel genrePanel = new JPanel(new BorderLayout());
		genrePanel.setBackground(Colors.WHITE);
		genrePanel.add(new LabelFactory("Main", genresTitle, labelFontSize), BorderLayout.NORTH);
		genrePanel.add(basePanel, BorderLayout.CENTER);
		
		return new Scrollable(genrePanel, true, false);
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JScrollPane createTopListsPanel() {

		topListsPanel.setLayout(new BoxLayout(topListsPanel, BoxLayout.Y_AXIS));
		topListsPanel.setBackground(Colors.WHITE);
		
		topListsPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				/*
				for (Component component : topListsPanel.getComponents()) {
					component.setMaximumSize(new Dimension((int) topListsPanel.getWidth(), (int) component.getPreferredSize().getHeight()));
				}
				*/
				topListsPanel.revalidate();
			}
		});
		
		return new Scrollable(topListsPanel, true, false);
	}
	
	// ============================================================================================
	
	public void addController() {
		MainController mainController = (MainController) controllerMap.get("MainController");
		
		for (int i = 0, size = genresList.length; i < size; i++) {
			genresButtons[i].addActionListener(mainController);
		}
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void update(Observable observable, Object object) {
		
		if (observable instanceof MainModel) {
			MainModel mainModel = (MainModel) observable;
			String objectString = (String) object;
			
			if (objectString.equals("LastReleasesList")) {
				for (Component component : topListsPanel.getComponents())
					if (component.getName().equals("Last"))
						topListsPanel.remove(component);
				topListsPanel.add(addListPanel("Last", mainModel.getLastReleasesListTitle(), mainModel.getLastReleasesList()));
				
			} else if (objectString.equals("BestAlbumList")) {
				for (Component component : topListsPanel.getComponents())
					if (component.getName().equals("Best"))
						topListsPanel.remove(component);
				topListsPanel.add(addListPanel("Best", mainModel.getBestAlbumsListTitle(), mainModel.getBestAlbumsList()));
				
			} else if (objectString.equals("userStateChanged")) {
				if (mainModel.getUsernameListTitle().equals("") || mainModel.getUsernameListTitle().equals(null))
					topListsPanel.remove(0);
				else
					topListsPanel.add(addListPanel("User", mainModel.getUsernameListTitle(), mainModel.getUserPersonalizedList()), 0);
			}
		}
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel addListPanel(String panelName, String listName, List<Product> list) {
		
		JLabel listTitleLable = new LabelFactory("Main", listName, labelFontSize);
		
		JPanel productsPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 0, 0));
		productsPanel.setBackground(Colors.WHITE);
		for (Product product : list)
			productsPanel.add(new ProductPreview(product, ((ProductController) controllerMap.get("ProductController"))));
		
		JPanel finalPanel = new JPanel(new BorderLayout());
		finalPanel.setName(panelName);
		finalPanel.setBackground(Colors.WHITE);
		finalPanel.add(listTitleLable, BorderLayout.NORTH);
		finalPanel.add(productsPanel, BorderLayout.SOUTH);
		
		return finalPanel;
	}	

	// ============================================================================================
	
	public JButton[] getGenresButtons() { return genresButtons; }
	
}
