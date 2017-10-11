package it.univr.MusicValley;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.univr.MusicValley.gui.Gui;
import it.univr.MusicValley.gui.models.Database;
import it.univr.MusicValley.gui.models.LoggedUser;

public class MusicValley {
	
	private static final File logoImage = new File("resources/logo/logo_small.png");
	
	// -------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		
		Database.getInstance();
		LoggedUser.getInstance();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MusicValley();
			}
		});
		
		/* file check ----------------------------------------- */
		/*
		Set<String> hashSet = new HashSet<String>();
		for (Product product : Database.getInstance().getDatabase()) {
			String[] instrumentis = product.getInstruments();
			for(String instrument : instrumentis)
				hashSet.add(instrument);
		}
		List<String> sortedList = new ArrayList<String>(hashSet);
		Collections.sort(sortedList);
		
		for (String str : sortedList)
			System.out.println(str);
		System.out.println("------------------");
		for (String str : sortedList) {
			File f = new File("resources/instruments/" + str + ".png");
			if(!f.exists()) { 
			    System.out.println("File " + str + ".png NON TROVATO");
			}
		}
		*/
		/* ---------------------------------------------------- */
		
	}
	
	// -------------------------------------------------------------------------------------

	private MusicValley() {
		setLookAndFeel();
		initialize();
	}

	// -------------------------------------------------------------------------------------
	
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	// -------------------------------------------------------------------------------------
	
	private void initialize() {
		
		Gui programWindow = new Gui();
		programWindow.setTitle("Music Valley");
		programWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		programWindow.setMinimumSize(new Dimension(1100, 700));
		programWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		programWindow.pack();
		
		try {
			programWindow.setIconImage(ImageIO.read(logoImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		programWindow.setVisible(true);
	}
	
}
