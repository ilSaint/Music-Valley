/*
package it.univr.MusicValley.gui.components;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CardManager {
	
	private JPanel cardPanel;
	
	private List<String> viewHistory = new ArrayList<String>();
	private JPanel transitionPanel = new JPanel(null);
	
	private JLabel prevLabel = new JLabel();
	private JLabel currLabel = new JLabel();
	
	private ImageIcon prevImageIcon = new ImageIcon();
	private ImageIcon currImageIcon = new ImageIcon();
	
	private Timer timer;
	private int remainingPixels = 0;
	private static final int timerSpeed = 1;
	private static final int step = 10;
	
	// --------------------------------------------------------------------------------------------
	
	public CardManager(JPanel cardPanel) {
		this.cardPanel = cardPanel;
		
		this.cardPanel.add(transitionPanel, "TransitionPanel");
		viewHistory.add("TransitionPanel");
		
		prevLabel.setIcon(prevImageIcon);
		currLabel.setIcon(currImageIcon);
		
		transitionPanel.add(prevLabel);
		transitionPanel.add(currLabel);
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void addCard(JPanel card, final Enum<?> cardName) {
		cardPanel.add(card, cardName.toString());
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void goToCard(final Enum<?> cardName, boolean clearHistory) {
		
		if (clearHistory) {
			for (int i = viewHistory.size() - 1; i > 0; i--) {
				viewHistory.remove(i);
			}
		}
		
		viewHistory.add(cardName.toString());
		((CardLayout) cardPanel.getLayout()).show(cardPanel, cardName.toString());
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void goToPreviousCard() {
		startTransition();
	}
	
	// --------------------------------------------------------------------------------------------
	
	public boolean isSameCardAs(final Enum<?> cardName) {
		return viewHistory.get(viewHistory.size() - 1).equals(cardName.toString());
	}
	
	// --------------------------------------------------------------------------------------------
	
	public JPanel getCardPanel() {
		return cardPanel;
	}
	
	// ==========================================================================================================================================================
	
	private void startTransition() {
		
		for (String str : viewHistory) 
			System.out.println(str);
		System.out.println("--------");
		
		prevImageIcon.setImage(takeSnapshot((JPanel) cardPanel.getComponents()[viewHistory.size() - 2]));
		currImageIcon.setImage(takeSnapshot((JPanel) cardPanel.getComponents()[viewHistory.size() - 1]));
		
		int imageWidth = (int) prevLabel.getPreferredSize().getWidth();
		int imageHeight = (int) prevLabel.getPreferredSize().getHeight();
		
		prevLabel.setBounds(-imageWidth, 0, imageWidth, imageHeight);
		currLabel.setBounds(0, 0, imageWidth, imageHeight);
		
		((CardLayout) cardPanel.getLayout()).show(cardPanel, "TransitionPanel");
		
		timer = new Timer(timerSpeed, null);
		timer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if (remainingPixels < imageWidth) {
					
					remainingPixels += step;
					
					prevLabel.setBounds(-(imageWidth - remainingPixels), 0, (int) prevLabel.getPreferredSize().getWidth(), (int) prevLabel.getPreferredSize().getHeight());
					currLabel.setBounds(remainingPixels, 0, (int) currLabel.getPreferredSize().getWidth(), (int) currLabel.getPreferredSize().getHeight());
					
				} else {
					
					timer.stop();
					remainingPixels = 0;
					
					viewHistory.remove(viewHistory.size() - 1);
					((CardLayout) cardPanel.getLayout()).show(cardPanel, viewHistory.get(viewHistory.size() - 1));
					
					for (String str : viewHistory) 
						System.out.println(str);
					System.out.println("-----------------");
				}
				
				
			}
		});
		
		timer.start();
		
		
	}
	
	// --------------------------------------------------------------------------------------------
	
	private BufferedImage takeSnapshot(JPanel panel){
		
		BufferedImage bufferedImage = new BufferedImage(panel.getSize().width, panel.getSize().height, BufferedImage.TYPE_INT_RGB);
		panel.paint(bufferedImage.createGraphics());
		
		return bufferedImage;
	}
	
}
*/

package it.univr.MusicValley.gui.components;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class CardManager {
	
	private List<String> previousViewList = new ArrayList<String>();
	private JPanel cardPanel;
	
	// --------------------------------------------------------------------------------------------
	
	public CardManager(JPanel cardPanel) {
		this.cardPanel = cardPanel;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void addCard(JPanel card, final Enum<?> cardName) {
		cardPanel.add(card, cardName.toString());
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void goToCard(final Enum<?> cardName, boolean clearHistory) {
		
		if (clearHistory)
			previousViewList.clear();
		
		previousViewList.add(cardName.toString());
		((CardLayout) cardPanel.getLayout()).show(cardPanel, cardName.toString());
	}
	
	// --------------------------------------------------------------------------------------------
	
	public void goToPreviousCard() {
		previousViewList.remove(previousViewList.size() - 1);
		((CardLayout) cardPanel.getLayout()).show(cardPanel, previousViewList.get(previousViewList.size() - 1));
	//	takeSnapShot((JPanel) cardPanel.getComponents()[0]);
	}
	
	// --------------------------------------------------------------------------------------------
	
	public boolean isSameCardAs(final Enum<?> cardName) {
		return previousViewList.get(previousViewList.size() - 1).equals(cardName.toString());
	}

	// --------------------------------------------------------------------------------------------
	
	public JPanel getCardPanel() {
		return cardPanel;
	}

	// --------------------------------------------------------------------------------------------
	/*
	void takeSnapShot(JPanel panel){
		
		BufferedImage bufferedImage = new BufferedImage(panel.getSize().width, panel.getSize().height, BufferedImage.TYPE_INT_RGB);
		panel.paint(bufferedImage.createGraphics());
		
		
		File imageFile = new File("C:/database/asd" + "." + "png");
		try {
			imageFile.createNewFile();
			ImageIO.write(bufferedImage, "png", imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	*/
}
