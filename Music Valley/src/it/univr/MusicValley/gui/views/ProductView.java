package it.univr.MusicValley.gui.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import it.univr.MusicValley.data.Member;
import it.univr.MusicValley.data.Product;
import it.univr.MusicValley.factory.ButtonFactory;
import it.univr.MusicValley.factory.LabelFactory;
import it.univr.MusicValley.gui.components.HeaderPanel;
import it.univr.MusicValley.gui.components.MarginPanel;
import it.univr.MusicValley.gui.components.Scrollable;
import it.univr.MusicValley.gui.components.WrapLayout;
import it.univr.MusicValley.gui.controllers.ProductController;
import it.univr.MusicValley.gui.models.ProductModel;
import it.univr.MusicValley.utility.Colors;
import it.univr.MusicValley.utility.Utils;

public class ProductView extends JPanel implements Observer {
	
	private Map<String, Object> controllerMap;
	
	private Product product;
	
	private JButton backButton;
	private JButton buyButton = new ButtonFactory("BuyBig", "ACQUISTA");
	
	private HeaderPanel headerPanel = new HeaderPanel(null, true);
	private JLabel cover = new JLabel();
	private JLabel codeLabel = new LabelFactory("ProductViewContent", "", contentFontSize);
	private JLabel priceLabel = new LabelFactory("ProductViewContent", "", contentFontSize);
	private JLabel dateLabel = new LabelFactory("ProductViewContent", "", contentFontSize);
	private JLabel artistLabel = new LabelFactory("ProductViewContent", "", contentFontSize);
	private JLabel genreLabel = new LabelFactory("ProductViewContent", "", contentFontSize);
	private JLabel typeLabel = new LabelFactory("ProductViewContent", "", contentFontSize);
	private JLabel salesLabel = new LabelFactory("ProductViewContent", "", contentFontSize);
	private JLabel quantityLabel = new LabelFactory("ProductViewContent", "", contentFontSize);
	private JLabel descriptionLabel = new LabelFactory("ProductViewContent", "", contentFontSize);
	
	private JPanel partecipantsListPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 0, 0));
	private JPanel instrumentsListPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 0, 0));
	
	private String[] headerStrings = {"Nome", "Anno nascita", "Genere preferito", "Strumento"};
	private DefaultTableModel membersTableModel = new DefaultTableModel(null, headerStrings);
	private DefaultTableModel tracesTableModel = new DefaultTableModel();
	
	private static final int coverSize = 250;
	private static final int groupTitleFontSize = 25;
	private static final int contentFontSize = 20;
	private static final int shortInfoPanelWidth = 250;
	private static final int instrumentPanelWidth = 225;
	private static final int instrumentIconSize = 35;
	private static final int spaceBetweenFields = 20;
	private static final int spaceBetweenInfoGroups = 30;
	private static final int spaceBetweenRows = 35;

	// --------------------------------------------------------------------------------------------
	
	public ProductView(Map<String, Object> controllerMap) {
		
		this.controllerMap = controllerMap;
		backButton = headerPanel.getBackButton();
		
		setLayout(new BorderLayout());
		add(headerPanel, BorderLayout.NORTH);
		add(new Scrollable(new MarginPanel(createDataPanel()), true, false), BorderLayout.CENTER);
	}
	
	// --------------------------------------------------------------------------------------------
	
	public JPanel createDataPanel() {
		
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
		dataPanel.setBackground(Colors.GRAY);
		dataPanel.add(createCoverPanel());
		dataPanel.add(createBuyButtonPanel());
		dataPanel.add(createGeneralInfoPanel());
		dataPanel.add(createTracesPanel());
		dataPanel.add(createIstrumentsPanel());
		dataPanel.add(createDescriptionPanel());
		dataPanel.add(createPartecipantsPanel());
		dataPanel.add(createMembersPanel());
		dataPanel.add(Box.createVerticalGlue());
		
		return dataPanel;
	}

	// --------------------------------------------------------------------------------------------
	
	private JPanel createBuyButtonPanel() {
		
		JPanel buyButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		buyButtonPanel.setBackground(Colors.WHITE);
		buyButtonPanel.setBorder(new EmptyBorder(0, 0, spaceBetweenInfoGroups, 0));
		buyButtonPanel.add(buyButton);
		
		return buyButtonPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createCoverPanel() {
		
		cover.setPreferredSize(new Dimension(coverSize, coverSize));
		cover.setBorder(BorderFactory.createLineBorder(Colors.BLACK, 1));
		
		JPanel coverPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		coverPanel.setBackground(Colors.WHITE);
		coverPanel.setBorder(new EmptyBorder(0, 0, spaceBetweenInfoGroups, 0));
		coverPanel.add(cover);
		coverPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) coverPanel.getPreferredSize().getHeight()));
		
		return coverPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createGeneralInfoPanel() {
		
		JPanel generalInfoPanel = new JPanel(new WrapLayout(FlowLayout.LEFT, 0, 0));
		generalInfoPanel.setBackground(Colors.WHITE);
		generalInfoPanel.add(createShortInfoPanel("ARTISTA", artistLabel));
		generalInfoPanel.add(createShortInfoPanel("TIPO PRODOTTO", typeLabel));
		generalInfoPanel.add(createShortInfoPanel("GENERE", genreLabel));
		generalInfoPanel.add(createShortInfoPanel("DISPONIBILE DAL", dateLabel));
		generalInfoPanel.add(createShortInfoPanel("PREZZO", priceLabel));
		generalInfoPanel.add(createShortInfoPanel("VENDUTI", salesLabel));
		generalInfoPanel.add(createShortInfoPanel("DISPONIBILITÀ", quantityLabel));
		generalInfoPanel.add(createShortInfoPanel("CODICE", codeLabel));
		generalInfoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) generalInfoPanel.getPreferredSize().getHeight()));
		
		return generalInfoPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createTracesPanel() {
				
		JTable traceTable = new JTable();
		traceTable.setFont(new Font("Titillium Web", Font.PLAIN, 20));
		traceTable.setForeground(Colors.GRAY050);
		traceTable.setEnabled(false);
		traceTable.setShowGrid(false);
		traceTable.setRowHeight(spaceBetweenRows);
		//traceTable.getColumnModel().setColumnMargin(50);
		traceTable.setModel(tracesTableModel);
		
		JPanel tracesPanel = new JPanel(new BorderLayout());
		tracesPanel.setBackground(Colors.WHITE);
		tracesPanel.setBorder(new EmptyBorder(0, 0, spaceBetweenInfoGroups, 0));
		tracesPanel.add(new LabelFactory("ProductViewTitle", "TRACCE", groupTitleFontSize), BorderLayout.NORTH);
		tracesPanel.add(traceTable, BorderLayout.CENTER);
		tracesPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) tracesPanel.getPreferredSize().getHeight()));
		
		return tracesPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createIstrumentsPanel() {
		
		instrumentsListPanel.setBackground(Colors.WHITE);
		
		JPanel instrumentsPanel = new JPanel(new BorderLayout());
		instrumentsPanel.setBackground(Colors.WHITE);
		instrumentsPanel.setBorder(new EmptyBorder(0, 0, spaceBetweenInfoGroups, 0));
		instrumentsPanel.add(new LabelFactory("ProductViewTitle", "STRUMENTI USATI", groupTitleFontSize), BorderLayout.NORTH);
		instrumentsPanel.add(instrumentsListPanel, BorderLayout.SOUTH);
		instrumentsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) instrumentsPanel.getPreferredSize().getHeight()));
		
		return instrumentsPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createDescriptionPanel() {
		
		JPanel descriptionPanel = new JPanel(new BorderLayout());
		descriptionPanel.setBackground(Colors.WHITE);
		descriptionPanel.setBorder(new EmptyBorder(0, 0, spaceBetweenInfoGroups, 0));
		descriptionPanel.add(new LabelFactory("ProductViewTitle", "DESCRIZIONE", groupTitleFontSize), BorderLayout.NORTH);
		descriptionPanel.add(descriptionLabel, BorderLayout.SOUTH);
		descriptionPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) descriptionPanel.getPreferredSize().getHeight()));
		
		return descriptionPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createPartecipantsPanel() {
		
		partecipantsListPanel.setBackground(Colors.WHITE);
		
		JPanel partecipantsPanel = new JPanel(new BorderLayout());
		partecipantsPanel.setBackground(Colors.WHITE);
		partecipantsPanel.setBorder(new EmptyBorder(0, 0, spaceBetweenInfoGroups, 0));
		partecipantsPanel.add(new LabelFactory("ProductViewTitle", "PARTECIPANTI", groupTitleFontSize), BorderLayout.NORTH);
		partecipantsPanel.add(partecipantsListPanel, BorderLayout.SOUTH);
		partecipantsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) partecipantsPanel.getPreferredSize().getHeight()));
		
		return partecipantsPanel;
	}

	// --------------------------------------------------------------------------------------------
	
	private JPanel createMembersPanel() {
		
		membersTableModel.setColumnCount(4);
		
		JTable membersTable = new JTable(membersTableModel);
		membersTable.setFont(new Font("Titillium Web", Font.PLAIN, 20));
		membersTable.setForeground(Colors.GRAY050);
		membersTable.setEnabled(false);
		membersTable.setShowGrid(false);
		membersTable.setRowHeight(spaceBetweenRows);
		//membersTable.getColumnModel().setColumnMargin(50);
		
		JTableHeader tableHeader = membersTable.getTableHeader();
		for (int i = 0; i < headerStrings.length; i++) {
			TableColumn column = membersTable.getColumnModel().getColumn(i);
			column.setHeaderRenderer(new HeaderRenderer());
		}
		tableHeader.setBackground(new Colors(252, 252, 252));
		tableHeader.setEnabled(false);
		
		JPanel membersTablePanel = new JPanel(new BorderLayout());
		membersTablePanel.add(membersTable, BorderLayout.CENTER);
		membersTablePanel.add(membersTable.getTableHeader(), BorderLayout.NORTH);
		
		JPanel membersPanel = new JPanel(new BorderLayout());
		membersPanel.setBackground(Colors.WHITE);
		membersPanel.setBorder(new EmptyBorder(0, 0, spaceBetweenInfoGroups, 0));
		membersPanel.add(new LabelFactory("ProductViewTitle", "MEMBRI", groupTitleFontSize), BorderLayout.NORTH);
		membersPanel.add(membersTablePanel, BorderLayout.CENTER);
		membersPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int) membersPanel.getPreferredSize().getHeight()));
		
		return membersPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private class HeaderRenderer extends JLabel implements TableCellRenderer {
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean hasFocus, boolean isSelected, int row, int col) {
			setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth(), 45));
			setFont(new Font("Titillium Web", Font.PLAIN, 20));
			setForeground(Colors.GRAY050);
			setText(value.toString());
			setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
			return this;
		}
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createShortInfoPanel(String labelName, JLabel label) {
		
		JPanel shortInfoPanel = new JPanel(new BorderLayout());
		shortInfoPanel.setBackground(Colors.WHITE);
		shortInfoPanel.add(new LabelFactory("ProductViewTitle", labelName, groupTitleFontSize), BorderLayout.NORTH);
		shortInfoPanel.add(label, BorderLayout.SOUTH);
		shortInfoPanel.setBorder(new EmptyBorder(0, 0, spaceBetweenInfoGroups, spaceBetweenFields));
		shortInfoPanel.setPreferredSize(new Dimension(shortInfoPanelWidth, 100));
		
		return shortInfoPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createInstrumentPanel(String instrument) {
		
		JLabel instrumentIconLabel = new JLabel();
		try {
			instrumentIconLabel.setIcon(new ImageIcon(ImageIO.read(new File("resources/instruments/" + instrument + ".png"))
				.getScaledInstance(instrumentIconSize, instrumentIconSize, Image.SCALE_SMOOTH))
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JLabel instrumentString = new LabelFactory(
			"ProductViewContent", Utils.toUpperCaseFirstChar(instrument), contentFontSize
		);
		instrumentString.setBorder(new EmptyBorder(10, 15, 10, 50));
		
		JPanel instrumentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		instrumentPanel.setBackground(Colors.WHITE);
		instrumentPanel.setBorder(new EmptyBorder(0, 0, 5, 0));
		instrumentPanel.add(instrumentIconLabel);
		instrumentPanel.add(instrumentString);
		instrumentPanel.setPreferredSize(new Dimension(instrumentPanelWidth, (int) instrumentPanel.getPreferredSize().getHeight()));
		
		return instrumentPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JPanel createPartecipantPanel(String partecipant) {
		
		JLabel partecipantLabel = new LabelFactory("ProductViewContent", partecipant, contentFontSize);
		
		JPanel partecipantPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		partecipantPanel.setBackground(Colors.WHITE);
		partecipantPanel.add(partecipantLabel);
		partecipantPanel.setPreferredSize(new Dimension(
			(int) partecipantPanel.getPreferredSize().getWidth() + 80,
			(int) partecipantPanel.getPreferredSize().getHeight()
		));
		
		return partecipantPanel;
	}
	
	// ============================================================================================
	
	public void addController() {
		ProductController roductController = (ProductController) controllerMap.get("ProductController");
		
		backButton.addActionListener(roductController);
		buyButton.addActionListener(roductController);
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public void update(Observable obs, Object obj) {
		
		if (obs instanceof ProductModel) {
			product = ((ProductModel) obs).getProduct();
			
			headerPanel.getTitleLabel().setText(product.getTitle().toUpperCase());
			
			try {
				cover.setIcon(new ImageIcon(ImageIO.read(product.getCover()).getScaledInstance(coverSize, coverSize, Image.SCALE_SMOOTH)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			codeLabel.setText("" + product.getCode());
			priceLabel.setText("" + Utils.toDecimalFormat(product.getPrice()) + " €");
			dateLabel.setText("" + product.getDate());
			artistLabel.setText(product.getArtist());
			genreLabel.setText(product.getGenre());
			typeLabel.setText(product.getType());
			salesLabel.setText("" + product.getSales());
			quantityLabel.setText("" + product.getQuantity());
			
			descriptionLabel.setText(Utils.toUpperCaseFirstChar(product.getDescription()));
			
			// ---------------------------------------------------------
			
			int traces = product.getTraces().length;
			int counter = 0;
			int rowCounter = 0;
			int colCounter = 0;
			int cols = (int) Math.sqrt(traces);
			int rows = (int) Math.ceil(traces / (float) cols);

			tracesTableModel.setRowCount(0);
			tracesTableModel.setRowCount(rows);
			tracesTableModel.setColumnCount(cols);
			
			for (String str: product.getTraces()) {
				counter++;
				tracesTableModel.setValueAt("" + counter + ". " + str, rowCounter, colCounter);
				rowCounter++;
				if (counter >= rows * (colCounter + 1)) {
					colCounter++;
					rowCounter = 0;
				}
			}
			
			// ---------------------------------------------------------
			
			instrumentsListPanel.removeAll();
			for (String instrument : product.getInstruments())
				instrumentsListPanel.add(createInstrumentPanel(instrument));
			
			partecipantsListPanel.removeAll();
			for (String partecipant : product.getParticipants())
				partecipantsListPanel.add(createPartecipantPanel(partecipant));

			// ---------------------------------------------------------
			
			int members = product.getMembers().size();
			rowCounter = 0;
			colCounter = 0;
			
			membersTableModel.setRowCount(0);
			membersTableModel.setRowCount(members);

			for (Member member : product.getMembers()) {
				membersTableModel.setValueAt(Utils.toUpperCaseFirstCharOfString(member.getName()), rowCounter, colCounter++);
				membersTableModel.setValueAt(Utils.toUpperCaseFirstChar(member.getBirthDate()), rowCounter, colCounter++);
				membersTableModel.setValueAt(Utils.toUpperCaseFirstChar(member.getGenre()), rowCounter, colCounter++);
				membersTableModel.setValueAt(Utils.toUpperCaseFirstChar(member.getInstrument()), rowCounter, colCounter++);
				rowCounter++;
				colCounter = 0;
			}
		}
	}

	// ============================================================================================
	
	public JButton getGoBackButton()		{ return backButton; }
	public JButton getBuyButton() 			{ return buyButton; }
	public Product getVisualizedProduct() 	{ return product; }
	
}
