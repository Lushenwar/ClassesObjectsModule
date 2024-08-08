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
 * This is the game class where the user will need to match 10 terms to 10 definitions 
 *
 * ADDED FEATURES
 * ==============
 * - Unmatching pairs will have a warning that they are wrong
 * - Matching pairs will disappear
 * - How to play telling the user how the game works
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
 * 
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class GamePage extends JFrame implements ActionListener {

	// JLabel for the game title
	private JLabel gameTitleLabel = new JLabel();

	// Button arrays for definitions and words
	private JButton[][] definitionButtonArray = new JButton[4][5];
	private JButton[][] wordButtonArray = new JButton[4][5];

	// TextArea arrays for definitions and words
	private JTextArea[][] definitionLabels = new JTextArea[4][5];
	private JTextArea[][] wordLabels = new JTextArea[4][5];

	// Background image for the game page
	private ImageIcon background = new ImageIcon("images/GameBackground.jpg");
	private Image scaledImage = background.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
	private ImageIcon scaledBackground = new ImageIcon(scaledImage);
	private JLabel gameBackground = new JLabel(scaledBackground);

	// Buttons for "How to Play" and "Back"
	private JButton howToPlayButton = new JButton("How to Play");
	private JButton backButton = new JButton("Back");

	// Menu items and menu bar
	private JMenuItem menuItemHome = new JMenuItem("JUST THE STORY");
	private JMenuItem menuItemGame = new JMenuItem("STORY AND SWORDS");
	private JMenuItem menuItemTest = new JMenuItem("DEATH MARCH");
	private JMenuItem menuItemQuit = new JMenuItem("QUIT");
	private JMenuBar menuBar = MenuBarUtility.createMenuBar(this, menuItemHome, menuItemGame, menuItemTest,
			menuItemQuit);

	// Arrays holding words and their corresponding definitions
	String[] words = { "Object", "Template Class", "Constructor", "Method", "State", "Behavior", "Class Variable",
			"Instance Variable", "Static Method", "Instance Method" };
	String[] definitions = { "Instance of a class", "Blueprint for objects", "Initializes new objects",
			"Function in a class", "Attributes of object", "Actions of object",
			"Variable shared among all instances of a class", "Variable specific to an instance of a class",
			"Method associated with the class, not instances", "Method associated with an instance of a class" };

	// Variables to keep track of selected definition and word
	private String selectedDefinition = null;
	private String selectedWord = null;
	private JButton selectedDefinitionButton = null;
	private JButton selectedWordButton = null;
	private int numberCorrect = 0;

	public GamePage() {
		generateTitle();
		generateButtons();
		fontSetup();
		frameSetup();
	}

	// Method to set up custom fonts
	private void fontSetup() {
		try {
			// Load the custom fonts
			File fontFileMason = new File("data/mason.ttf");
			Font customFontMason = Font.createFont(Font.TRUETYPE_FONT, fontFileMason);

			File fontFile = new File("data/font.ttf");
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

			// Register the fonts with the GraphicsEnvironment
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFontMason);
			ge.registerFont(customFont);

			// Set the fonts on the labels and buttons
			customFontMason = customFontMason.deriveFont(Font.BOLD, 14f); // set the font size
			customFont = customFont.deriveFont(Font.BOLD, 40f); // set the font size
			gameTitleLabel.setFont(customFont);

			// Apply the font to definition labels
			for (int row = 0; row < definitionLabels.length; row++) {
				for (int col = 0; col < definitionLabels[row].length; col++) {
					if (definitionLabels[row][col] != null) {
						definitionLabels[row][col].setFont(customFontMason);
					}
				}
			}
			// Apply the font to word labels
			for (int row = 0; row < wordLabels.length; row++) {
				for (int col = 0; col < wordLabels[row].length; col++) {
					if (wordLabels[row][col] != null) {
						wordLabels[row][col].setFont(customFontMason);
					}
				}
			}
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	// Method to generate the game title
	private void generateTitle() {
		gameTitleLabel.setText("STORY AND SWORDS");
		gameTitleLabel.setBounds(63, 63, 491, 56);
		gameTitleLabel.setForeground(Color.white);
		add(gameTitleLabel);

		// Configure and add "How to Play" button
		howToPlayButton.setBounds(1163, 63, 140, 40); // Adjust size and position as needed
		howToPlayButton.setBackground(Color.decode("#191C1D"));
		howToPlayButton.setForeground(Color.WHITE);
		howToPlayButton.setFont(gameTitleLabel.getFont().deriveFont(Font.PLAIN, 16f)); // Match the font of the title
		howToPlayButton.addActionListener(this);
		add(howToPlayButton);

		// Configure and add "Back" button
		backButton.setBounds(1000, 63, 140, 40); // Adjust size and position as needed
		backButton.setBackground(Color.decode("#191C1D"));
		backButton.setForeground(Color.WHITE);
		backButton.setFont(gameTitleLabel.getFont().deriveFont(Font.PLAIN, 16f)); // Match the font of the title
		backButton.addActionListener(this);
		add(backButton);
	}

	// Method to generate the buttons for the game
	private void generateButtons() {
		int rows = 4;
		int cols = 5;
		List<int[]> positions = generateRandomPositions(rows, cols);

		// Define grid layout properties
		int gridWidth = 214;
		int gridHeight = 60;
		int startX = 63; // starting X coordinate for the first button
		int startY = 192; // starting Y coordinate for the first button
		int spacing = 10; // spacing between buttons

		// Generate definition buttons
		for (int i = 0; i < 10; i++) {
			int[] pos = positions.get(i);
			int row = pos[0];
			int col = pos[1];

			definitionButtonArray[row][col] = new JButton();
			definitionButtonArray[row][col].setBounds(startX + (gridWidth + spacing) * col,
					startY + (gridHeight + spacing) * row, gridWidth, gridHeight);
			definitionButtonArray[row][col].setBackground(Color.decode("#191C1D"));
			definitionButtonArray[row][col].setLayout(new BorderLayout());
			definitionButtonArray[row][col].addActionListener(this);
			add(definitionButtonArray[row][col]);

			definitionLabels[row][col] = createTextArea(definitions[i]);
			definitionButtonArray[row][col].add(definitionLabels[row][col]);
		}

		// Generate word buttons
		for (int i = 0; i < 10; i++) {
			int[] pos = positions.get(i + 10);
			int row = pos[0];
			int col = pos[1];

			wordButtonArray[row][col] = new JButton();
			wordButtonArray[row][col].setBounds(startX + (gridWidth + spacing) * col,
					startY + (gridHeight + spacing) * row, gridWidth, gridHeight);
			wordButtonArray[row][col].setBackground(Color.decode("#191C1D"));
			wordButtonArray[row][col].setLayout(new BorderLayout());
			wordButtonArray[row][col].addActionListener(this);
			add(wordButtonArray[row][col]);

			wordLabels[row][col] = createTextArea(words[i]);
			wordButtonArray[row][col].add(wordLabels[row][col]);
		}
	}

	// Method to create and configure a JTextArea for a button
	private JTextArea createTextArea(String text) {
		JTextArea textArea = new JTextArea(text);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.decode("#191C1D"));
		textArea.setFont(new Font("Serif", Font.BOLD, 16));
		adjustFontSize(textArea);
		return textArea;
	}

	// Method to adjust the font size of a JTextArea to fit within its bounds
	private void adjustFontSize(JTextArea textArea) {
		Font font = textArea.getFont();
		int fontSize = font.getSize();
		while (textArea.getPreferredSize().width > textArea.getWidth() && fontSize > 10) {
			fontSize--;
			textArea.setFont(font.deriveFont((float) fontSize));
		}
	}

	// Method to generate a list of random positions for buttons
	private List<int[]> generateRandomPositions(int rows, int cols) {
		List<int[]> positions = new ArrayList<>();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				positions.add(new int[] { row, col });
			}
		}
		Collections.shuffle(positions);
		return positions;
	}

	// Method to set up the frame
	private void frameSetup() {
		// Sets title of the window
		setTitle("The Witcher: Story and Swords");

		// Sets the size of the window
		setSize(1366, 768);

		// Sets the layout manager to null for absolute positioning
		setLayout(null);

		// Sets the bounds for the background label
		gameBackground.setBounds(0, 0, 1366, 768);

		// Adds the background label to the content pane
		getContentPane().add(gameBackground);

		// Sets the layout manager to null again for absolute positioning
		setLayout(null);

		// Adds key and action listeners to the frame
		addActionListener(this);

		// Adds the menu bar to the frame
		setJMenuBar(menuBar);

		// Closes the window on exit
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Makes the window non-resizable
		setResizable(false);

		// Makes the window visible
		setVisible(true);
	}

	private void addActionListener(GamePage testPage) {
		// Empty method for action listener (if needed)
	}

	public void actionPerformed(ActionEvent event) {
		// Check if a definition button was pressed
		for (int row = 0; row < definitionButtonArray.length; row++) {
			for (int col = 0; col < definitionButtonArray[row].length; col++) {
				if (definitionButtonArray[row][col] != null && event.getSource() == definitionButtonArray[row][col]) {
					selectedDefinitionButton = definitionButtonArray[row][col];
					selectedDefinition = definitionLabels[row][col].getText();
					checkMatch();
					return;
				}
			}
		}

		// Check if a word button was pressed
		for (int row = 0; row < wordButtonArray.length; row++) {
			for (int col = 0; col < wordButtonArray[row].length; col++) {
				if (wordButtonArray[row][col] != null && event.getSource() == wordButtonArray[row][col]) {
					selectedWordButton = wordButtonArray[row][col];
					selectedWord = wordLabels[row][col].getText();
					checkMatch();
					return;
				}
			}
		}
		// If the how to play Button is clicked
		if (event.getSource() == howToPlayButton) {
			showHowToPlayDialog();
		}
		// If back button is pressed
		else if (event.getSource() == backButton) {
			returnToHomePage();
		}
		// If the menu item "Game" or "Start" button is clicked
		else if (event.getSource() == menuItemHome) {
			setVisible(false); // Hide the current frame
			new Lesson1();
		}
		// If the menu item "Character Selection" or gameButton is clicked
		else if (event.getSource() == menuItemGame) {
			setVisible(false); // Hide the current frame
			new GamePage();
		}
		// If the menu item "Opening" is clicked
		else if (event.getSource() == menuItemTest) {
			setVisible(false); // Hide the current frame
			new TestPage();
		}
		// If the menu item "Quit" is clicked
		else if (event.getSource() == menuItemQuit) {
			dispose(); // Close the application
		}
	}

	// Method to check if selected definition and word match
	private void checkMatch() {
		System.out.println("Selected Definition: " + selectedDefinition);
		System.out.println("Selected Word: " + selectedWord);

		if (selectedDefinition != null && selectedWord != null) {
			if (matches(selectedDefinition, selectedWord)) {
				selectedDefinitionButton.setVisible(false);
				selectedWordButton.setVisible(false);

				// Reset the selections
				selectedDefinition = null;
				selectedWord = null;
				selectedDefinitionButton = null;
				selectedWordButton = null;

				numberCorrect++;

				if (numberCorrect == 10) {
					showWinningMessage();
				}
			} else {
				// Reset the selections if they don't match
				JOptionPane.showMessageDialog(this, "Not a match, try again!", "Mismatch", JOptionPane.ERROR_MESSAGE);
				selectedDefinition = null;
				selectedWord = null;
			}
		}
	}

	// Method to show winning message and returns user to home page
	private void showWinningMessage() {
		JOptionPane.showMessageDialog(this, "You win, congratulations!", "Victory", JOptionPane.INFORMATION_MESSAGE);
		returnToHomePage();
	}

	// Method to show how to play instructions
	private void showHowToPlayDialog() {
		String instructions = "Welcome to the Matching Game!\n\n"
				+ "1. There are two sets of buttons: one for definitions and one for words.\n"
				+ "2. Click on a definition button to select a definition.\n"
				+ "3. Click on a word button to select a word.\n"
				+ "4. If the selected definition and word match, they will disappear.\n"
				+ "5. Continue matching until all pairs are found.\n\n" + "Good luck and have fun!";
		JOptionPane.showMessageDialog(this, instructions, "How to Play", JOptionPane.INFORMATION_MESSAGE);
	}

	// Method to return to the home page
	private void returnToHomePage() {
		this.dispose();
		new HomePage().setVisible(true);
	}

	// Method to check if selected definition and word match correctly
	private boolean matches(String selectedDefinition, String selectedWord) {
		// Checks if the definitions and words match up to the correct answers
		if ((selectedDefinition.equals("Blueprint for objects") && selectedWord.equals("Template Class"))
				|| (selectedDefinition.equals("Instance of a class") && selectedWord.equals("Object"))
				|| (selectedDefinition.equals("Initializes new objects") && selectedWord.equals("Constructor"))
				|| (selectedDefinition.equals("Function in a class") && selectedWord.equals("Method"))
				|| (selectedDefinition.equals("Attributes of object") && selectedWord.equals("State"))
				|| (selectedDefinition.equals("Actions of object") && selectedWord.equals("Behavior"))
				|| (selectedDefinition.equals("Variable shared among all instances of a class")
						&& selectedWord.equals("Class Variable"))
				|| (selectedDefinition.equals("Variable specific to an instance of a class")
						&& selectedWord.equals("Instance Variable"))
				|| (selectedDefinition.equals("Method associated with the class, not instances")
						&& selectedWord.equals("Static Method"))
				|| (selectedDefinition.equals("Method associated with an instance of a class")
						&& selectedWord.equals("Instance Method"))) {
			return true;
		}
		return false;
	}
}
