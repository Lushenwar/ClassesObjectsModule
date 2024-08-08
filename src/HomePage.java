/*
 * NAME
 * ====
 * Ryan Qi
 * 
 * DATE
 * =====
 * June 14 Friday
 * 
 * COURSE CODE
 * ============
 * ICS3U1-04
 * 
 * TITLE
 * ======
 * FSP Project
 * 
 * DESCRIPTION
 * ============
 * This is the home page where the user will be able to explore objects and classes
 * 
 * ADDED FEATURES
 * ==============
 * - Witcher themed activity difficulty levels are the different lessons, game and test
 * 
 * AREAS OF CONCERN
 * ================
 * N/A	
 * 
 * OTHERE REQUIRED DOCUMENTATION
 * ===============================
 * https://stackoverflow.com/questions/5652344/how-can-i-use-a-custom-font-in-java
 * https://www.w3schools.com/java/java_arraylist.asp
 * https://mkyong.com/swing/java-swing-how-to-make-a-simple-dialog/
 * https://www.geeksforgeeks.org/java-awt-flowlayout/
 * https://stackoverflow.com/questions/64817678/how-to-call-derivefont-correctly-after-font-createfont-in-java
 * https://mkyong.com/swing/java-swing-how-to-make-a-confirmation-dialog/
 * https://stackoverflow.com/questions/6567870/what-does-swingutilities-invokelater-do
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class HomePage extends JFrame implements ActionListener {

	// GUI elements
	private JLabel objectLabel = new JLabel("OBJECTS");
	private JLabel classLabel = new JLabel("CLASSES");
	private static JPanel buttonPanel = new JPanel();

	// create new ImageIcon for image
	private ImageIcon background = new ImageIcon("images/HomeBackground.jpg");
	// Calculate the scaled size while preserving aspect ratio
	private Image scaledImage = background.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
	// Create a new ImageIcon with the scaled image
	private ImageIcon scaledBackground = new ImageIcon(scaledImage);
	// Declare JLabel for storing image icons
	private JLabel homeBackground = new JLabel(scaledBackground);
	// create new ImageIcon for image
	private ImageIcon text = new ImageIcon("images/WitcherTexts.png");
	// Create JLabel for storing image icon
	private JLabel homeText = new JLabel(text);
	// create new ImageIcon for image
	private ImageIcon claw = new ImageIcon("images/Claw.png");
	// Create JLabel for storing image icon
	private JLabel homeClaw = new JLabel(claw);

	// Create menu items
	private JMenuItem menuItemHome = new JMenuItem("JUST THE STORY");
	private JMenuItem menuItemGame = new JMenuItem("STORY AND SWORDS");
	private JMenuItem menuItemTest = new JMenuItem("DEATH MARCH");
	private JMenuItem menuItemQuit = new JMenuItem("QUIT");
	private JMenuBar menuBar = MenuBarUtility.createMenuBar(this, menuItemHome, menuItemGame, menuItemTest,
			menuItemQuit);

	// GUI elements
	private JButton lessonButton = new JButton("JUST THE STORY");
	private JButton gameButton = new JButton("STORY AND SWORDS");
	private JButton testButton = new JButton("DEATH MARCH");

	// Constructor Method
	public HomePage() {
		buttonPanelSetup();
		frameSetup();
	}

	private void buttonPanelSetup() {

		try {
			// Step 1: Load the font from the .ttf file
			File fontFileMason = new File("data/mason.ttf");
			Font customFontMason = Font.createFont(Font.TRUETYPE_FONT, fontFileMason);

			File fontFile = new File("data/font.ttf");
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

			// Step 2: Register the font with the GraphicsEnvironment (optional)
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFontMason);
			ge.registerFont(customFont);

			// Step 3: Set the font on the JLabel
			customFontMason = customFontMason.deriveFont(Font.BOLD, 16f); // set the font size
			customFont = customFont.deriveFont(Font.BOLD, 20f); // set the font size
			objectLabel.setFont(customFont);
			classLabel.setFont(customFont);
			lessonButton.setFont(customFontMason);
			gameButton.setFont(customFontMason);
			testButton.setFont(customFontMason);

		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		// Set bounds and layout for the buttonPanel
		buttonPanel.setBounds(150, 0, 250, 768);
		buttonPanel.setBackground(Color.decode("#191C1D"));
		buttonPanel.setLayout(null);

		// Set bounds for Labels
		homeText.setBounds(12, 12, 225, 96);
		homeClaw.setBounds(buttonPanel.getWidth() / 2 - 35, 60, 70, 131);
		objectLabel.setBounds(10, 100, 100, 40);
		classLabel.setBounds(155, 100, 100, 40);

		// Set button background colors
		lessonButton.setBackground(Color.decode("#191C1D"));
		gameButton.setBackground(Color.decode("#191C1D"));
		testButton.setBackground(Color.decode("#191C1D"));

		// Set foreground colors for labels and buttons
		lessonButton.setForeground(Color.WHITE);
		gameButton.setForeground(Color.WHITE);
		testButton.setForeground(Color.WHITE);
		objectLabel.setForeground(Color.decode("#dde6e6"));
		classLabel.setForeground(Color.decode("#dde6e6"));

		// Set bounds for labels and buttons
		lessonButton.setBounds(buttonPanel.getWidth() / 2 - 100, 200, 210, 50);
		gameButton.setBounds(buttonPanel.getWidth() / 2 - 100, 275, 210, 50);
		testButton.setBounds(buttonPanel.getWidth() / 2 - 100, 350, 210, 50);

		// Add action listeners for buttons
		lessonButton.addActionListener(this);
		gameButton.addActionListener(this);
		testButton.addActionListener(this);

		buttonPanel.add(homeText);
		buttonPanel.add(homeClaw);
		buttonPanel.add(objectLabel);
		buttonPanel.add(classLabel);

		// Add labels and buttons to the buttonPanel
		buttonPanel.add(lessonButton);
		buttonPanel.add(gameButton);
		buttonPanel.add(testButton);
	}

	private void frameSetup() {
		// Set title, size, and layout
		setTitle("The Witcher: Objects and Classes");
		setSize(1366, 768);
		setLayout(null);

		// Set bounds for the background label
		homeBackground.setBounds(0, 0, 1366, 768);

		// Add the background label to the content pane
		getContentPane().add(homeBackground);

		// Add the button panel
		homeBackground.add(buttonPanel);

		// Add MenuBar
		setJMenuBar(menuBar);

		// Set default close operation and frame properties
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	// Method to handle action events
	public void actionPerformed(ActionEvent event) {
		// If the menu item "Lesson" button is clicked
		if (event.getSource() == menuItemHome || event.getSource() == lessonButton) {
			setVisible(false); // Hide the current frame
			new Lesson1();
		}
		// If the menu item "Game" or gameButton is clicked
		else if (event.getSource() == menuItemGame || event.getSource() == gameButton) {
			setVisible(false); // Hide the current frame
			new GamePage();
		}
		// If the menu item "Test" is clicked
		else if (event.getSource() == menuItemTest || event.getSource() == testButton) {
			setVisible(false); // Hide the current frame
			new TestPage();
		}
		// If the menu item "Quit" is clicked
		else if (event.getSource() == menuItemQuit) {
			dispose(); // Close the application
		}
	}
}
