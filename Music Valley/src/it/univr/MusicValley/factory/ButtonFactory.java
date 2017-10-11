package it.univr.MusicValley.factory;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.univr.MusicValley.utility.Colors;

public class ButtonFactory extends JButton {
	
	private static final long serialVersionUID = 1L;
	private final String buttonType;
	
	// --------------------------------------------------------------------------------------------
	
	public ButtonFactory(String buttonType, String buttonName) {
		super(buttonName);
		this.buttonType = buttonType;
		creatButton();
	}

	// --------------------------------------------------------------------------------------------
	
	private JButton creatButton() {
		switch (this.buttonType) {
			case "Home": 			return createHomeButton();
			case "Genre": 			return createGenreButton();
			case "Buy":				return createBuyButton();
			case "BuyBig":			return createBuyBigButton();
			case "Back":			return createBackButton();
			case "DoButton":		return createDoButton();
			case "RemoveButton":	return createRemoveButton();
			default: 				return null;
		}
	}

	// --------------------------------------------------------------------------------------------
	
	private JButton createHomeButton() {
		
		setBorderPainted(true);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setBackground(Colors.WHITE);
		setForeground(Colors.GRAY050);
		setFont(new Font("Titillium Web", Font.PLAIN, 18));
		setBorder(BorderFactory.createLineBorder(Colors.GRAY050, 1));
		
		this.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				if (getModel().isPressed()) {
					setBackground(Colors.RED238);
					setForeground(Colors.WHITE);
					setFont(new Font("Titillium Web", Font.BOLD, 17));
					setBorder(BorderFactory.createLineBorder(Colors.RED238, 1));
				} else if (getModel().isRollover()) {
					setBackground(Colors.RED238);
					setForeground(Colors.WHITE);
					setFont(new Font("Titillium Web", Font.BOLD, 18));
					setBorder(BorderFactory.createLineBorder(Colors.RED238, 1));
				} else {
					setBackground(Colors.WHITE);
					setForeground(Colors.GRAY050);
					setFont(new Font("Titillium Web", Font.PLAIN, 18));
					setBorder(BorderFactory.createLineBorder(Colors.GRAY050, 1));
				}
			}
		});
		
		return this;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JButton createGenreButton() {
		
		setFocusPainted(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setOpaque(true);
		setBackground(Colors.RED238);
		setForeground(Colors.WHITE);
		setFont(new Font("Titillium Web", Font.PLAIN, 15));
		setHorizontalAlignment(SwingConstants.LEFT);
		//setMargin(new Insets(0, 0, 0, 0));
		
		this.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				if (getModel().isPressed()) {
					setBackground(Colors.RED238);
					setForeground(Colors.WHITE);
					setFont(new Font("Titillium Web", Font.BOLD, 14));
				} else if (getModel().isRollover()) {
					setBackground(Colors.RED238);
					setForeground(Colors.WHITE);
					setFont(new Font("Titillium Web", Font.BOLD, 15));
				} else {
					setBackground(Colors.RED238);
					setForeground(Colors.WHITE);
					setFont(new Font("Titillium Web", Font.PLAIN, 15));
				}
			}
		});
		
		return this;
	}

	// --------------------------------------------------------------------------------------------

	private JButton createBuyButton() {

		String htmlFormat = "<html>%s <b>%s</b></html>";
		String[] splitString = this.getText().split(" ", 2);
		if (splitString.length != 1) {
			this.setText(String.format(htmlFormat, splitString[0], splitString[1]));
		}
		
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setBackground(Colors.RED238);
		setForeground(Colors.WHITE);
		setFont(new Font("Titillium Web", Font.PLAIN, 16));
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		//setPreferredSize(new Dimension(120, 20));
		//setHorizontalAlignment(SwingConstants.CENTER);
		
		this.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				if (getModel().isPressed()) {
					//setFont(new Font("Titillium Web", Font.BOLD, 15));
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				} else if (getModel().isRollover()) {
					//setFont(new Font("Titillium Web", Font.BOLD, 16));
					setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                	//setFont(new Font("Titillium Web", Font.PLAIN, 16));
                	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
			}
		});
		
		return this;
	}
	
	// --------------------------------------------------------------------------------------------

	private JButton createBuyBigButton() {
		
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setBackground(Colors.RED238);
		setForeground(Colors.WHITE);
		setFont(new Font("Titillium Web", Font.PLAIN, 20));
		setPreferredSize(new Dimension(140, 35));
		setHorizontalAlignment(SwingConstants.CENTER);
		
		this.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				if (getModel().isPressed()) {
					setFont(new Font("Titillium Web", Font.BOLD, 19));
				} else if (getModel().isRollover()) {
					setFont(new Font("Titillium Web", Font.BOLD, 20));
                } else {
                	setFont(new Font("Titillium Web", Font.PLAIN, 20));
                }
			}
		});
		
		return this;
	}
	
	// --------------------------------------------------------------------------------------------
	
	private JButton createBackButton() {
		
		final int pressedSize = 24;
		final int unpressedSize = 20;
		final File redIcon = new File("resources/other/go_back_red.png");
		final File whiteIcon = new File("resources/other/go_back_white.png");
		
		ImageIcon redUnpressed = null;
		try {
			redUnpressed = new ImageIcon(ImageIO.read(redIcon).getScaledInstance(pressedSize, pressedSize, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setBackground(Colors.WHITE);
		setIcon(redUnpressed);
		setBorder(new EmptyBorder(0, 17, 0, 17));
		
		this.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				if (getModel().isPressed()) {
					
					ImageIcon whitePressed = null;
					try {
						whitePressed = new ImageIcon(ImageIO.read(whiteIcon).getScaledInstance(unpressedSize, unpressedSize, Image.SCALE_SMOOTH));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					setBackground(Colors.RED238);
					setIcon(whitePressed);
					setBorder(new EmptyBorder(0, 19, 0, 19));
				} else if (getModel().isRollover()) {
					
					ImageIcon whiteUnpressed = null;
					try {
						whiteUnpressed = new ImageIcon(ImageIO.read(whiteIcon).getScaledInstance(pressedSize, pressedSize, Image.SCALE_SMOOTH));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					setBackground(Colors.RED238);
					setIcon(whiteUnpressed);
					setBorder(new EmptyBorder(0, 17, 0, 17));
				} else {
					
					ImageIcon redUnpressed = null;
					try {
						redUnpressed = new ImageIcon(ImageIO.read(redIcon).getScaledInstance(pressedSize, pressedSize, Image.SCALE_SMOOTH));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					setBackground(Colors.WHITE);
					setIcon(redUnpressed);
					setBorder(new EmptyBorder(0, 17, 0, 17));
					
				}
			}
		});
		
		return this;
	}

	// --------------------------------------------------------------------------------------------
	
	private JButton createDoButton() {
		
		setBackground(Colors.RED238);
		setForeground(Colors.WHITE);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setFont(new Font("Titillium Web", Font.PLAIN, 18));
		
		this.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				if (getModel().isPressed()) {
					setFont(new Font("Titillium Web", Font.BOLD, 17));
				} else if (getModel().isRollover()) {
					setFont(new Font("Titillium Web", Font.BOLD, 18));
                } else {
                	setFont(new Font("Titillium Web", Font.PLAIN, 18));
                }
			}
		});
		
		return this;		
	}

	// --------------------------------------------------------------------------------------------
	
	private JButton createRemoveButton() {
		
		final int pressedSize = 24;
		final int unpressedSize = 20;
		final File blackIcon = new File("resources/other/cancel_button_black.png");
		final File whiteIcon = new File("resources/other/cancel_button_white.png");
		
		ImageIcon redUnpressed = null;
		try {
			redUnpressed = new ImageIcon(ImageIO.read(blackIcon).getScaledInstance(pressedSize, pressedSize, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setRolloverEnabled(true);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setBackground(Colors.WHITE);
		setIcon(redUnpressed);
		setBorder(new EmptyBorder(0, 17, 0, 17));
		
		this.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				if (getModel().isPressed()) {
					
					ImageIcon whitePressed = null;
					try {
						whitePressed = new ImageIcon(ImageIO.read(whiteIcon).getScaledInstance(unpressedSize, unpressedSize, Image.SCALE_SMOOTH));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					setBackground(Colors.RED238);
					setIcon(whitePressed);
					setBorder(new EmptyBorder(0, 19, 0, 19));
				} else if (getModel().isRollover()) {
					
					ImageIcon whiteUnpressed = null;
					try {
						whiteUnpressed = new ImageIcon(ImageIO.read(whiteIcon).getScaledInstance(pressedSize, pressedSize, Image.SCALE_SMOOTH));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					setBackground(Colors.RED238);
					setIcon(whiteUnpressed);
					setBorder(new EmptyBorder(0, 17, 0, 17));
				} else {
					
					ImageIcon redUnpressed = null;
					try {
						redUnpressed = new ImageIcon(ImageIO.read(blackIcon).getScaledInstance(pressedSize, pressedSize, Image.SCALE_SMOOTH));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					setBackground(Colors.WHITE);
					setIcon(redUnpressed);
					setBorder(new EmptyBorder(0, 17, 0, 17));
					
				}
			}
		});
		
		return this;
	}
}
