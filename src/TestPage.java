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
 * This is the test page where the user will be able to answer multiple choice questions about the lesson.
 * 
 * ADDED FEATURES
 * ==============
 * - Percentage is given at the end as well as suggestions
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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class TestPage extends JFrame implements ActionListener {

	// Declare components
	private JLabel testTitleLabel = new JLabel();
	private JButton submitButton = new JButton("Submit");
	private JButton backButton = new JButton("Back");

	// Create ImageIcon for the background image
	private ImageIcon background = new ImageIcon("images/GamePage.jpg");
	// Calculate the scaled size while preserving aspect ratio
	private Image scaledImage = background.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);
	// Create a new ImageIcon with the scaled image
	private ImageIcon scaledBackground = new ImageIcon(scaledImage);
	// Declare JLabel for storing image icons
	private JLabel homeBackground = new JLabel(scaledBackground);

	// Create menu items
	private JMenuItem menuItemHome = new JMenuItem("JUST THE STORY");
	private JMenuItem menuItemGame = new JMenuItem("STORY AND SWORDS");
	private JMenuItem menuItemTest = new JMenuItem("DEATH MARCH");
	private JMenuItem menuItemQuit = new JMenuItem("QUIT");
	// Create the menu bar
	private JMenuBar menuBar = MenuBarUtility.createMenuBar(this, menuItemHome, menuItemGame, menuItemTest,
			menuItemQuit);

	// Array holding the questions related to object-oriented programming
	String[] questions = { "1. Which of the following is an instance of a class?",
			"2. Classes and objects can be used interchangeably in object-oriented programming.",
			"3. Which of the following initializes new objects?", "4. Which of the following is a function in a class?",
			"5. Which of the following describes the attributes of an object?",
			"6. Which of the following describes the actions of an object?",
			"7. Which of the following is a variable shared among all instances of a class?",
			"8. Which of the following is a variable specific to an instance of a class?",
			"9. Which of the following is a method associated with the class, not instances?",
			"10. Which of the following is a method associated with an instance of a class?",
			"11. Which of the following is a blueprint for objects?" };

	// Array holding the multiple-choice options for each question
	String[][] options = { { "a) Object", "b) Template Class", "c) Constructor", "d) Method", "e) None of the above" },
			{ "a) True", "b) False" },
			{ "a) Object", "b) Template Class", "c) Constructor", "d) Method", "e) None of the above" },
			{ "a) Object", "b) Template Class", "c) Constructor", "d) Method", "e) None of the above" },
			{ "a) State", "b) Behavior", "c) Class Variable", "d) Instance Variable", "e) None of the above" },
			{ "a) State", "b) Behavior", "c) Class Variable", "d) Instance Variable", "e) None of the above" },
			{ "a) Object", "b) Template Class", "c) Class Variable", "d) Instance Variable", "e) None of the above" },
			{ "a) Object", "b) Template Class", "c) Constructor", "d) Instance Variable", "e) None of the above" },
			{ "a) Static Method", "b) Instance Method", "c) Constructor", "d) Method", "e) None of the above" },
			{ "a) Static Method", "b) Instance Method", "c) Constructor", "d) Method", "e) None of the above" },
			{ "a) Object", "b) Template Class", "c) Constructor", "d) Method", "e) None of the above" } };

	// Array holding the correct answers for each question
	char[] answers = { 'a', 'b', 'c', 'd', 'a', 'b', 'c', 'd', 'a', 'b', 'b' };
	// Array of JRadioButtons for each option of each question
	JRadioButton[][] optionButtons = new JRadioButton[questions.length][];
	// Array of ButtonGroups to group the JRadioButtons for each question
	ButtonGroup[] buttonGroups = new ButtonGroup[questions.length];

	public TestPage() {
		// Set layout manager
		setLayout(new BorderLayout());
		// Initialize fonts
		fontSetup();
		// Setup frame properties
		frameSetup();
	}

	private JPanel createTitlePanel() {
		// Set the title of the test
		testTitleLabel.setText("DEATH MARCH");
		testTitleLabel.setForeground(Color.white);

		// Style the submit button
		submitButton.setBackground(Color.decode("#191C1D"));
		submitButton.setForeground(Color.WHITE);
		submitButton.addActionListener(this);

		// Style the back button
		backButton.setBackground(Color.decode("#191C1D"));
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(this);

		// Create a panel for the title and buttons
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		titlePanel.setOpaque(false);
		titlePanel.add(testTitleLabel);
		titlePanel.add(backButton);
		titlePanel.add(submitButton);

		return titlePanel;
	}

	private JScrollPane createScrollPane() {
		// Create a panel to hold the questions and options
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(false);

		// Loop through each question
		for (int i = 0; i < questions.length; i++) {
			JPanel questionPanel = new JPanel();
			questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
			questionPanel.setOpaque(false);
			questionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			// Add the question label
			JLabel questionLabel = new JLabel(questions[i]);
			questionLabel.setForeground(Color.white);
			questionPanel.add(questionLabel);

			// Create a button group for the options of this question
			buttonGroups[i] = new ButtonGroup();
			optionButtons[i] = new JRadioButton[options[i].length];

			// Loop through each option
			for (int j = 0; j < options[i].length; j++) {
				// Create a radio button for the option
				optionButtons[i][j] = new JRadioButton(options[i][j]);
				optionButtons[i][j].setForeground(Color.white);
				optionButtons[i][j].setBackground(Color.decode("#191C1D"));
				buttonGroups[i].add(optionButtons[i][j]);
				questionPanel.add(optionButtons[i][j]);
			}

			panel.add(questionPanel);
		}

		// Create a scroll pane for the panel of questions and options
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(1100, 600));
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setOpaque(false);

		// Increase scroll speed
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		return scrollPane;
	}

	private void fontSetup() {
		try {
			// Load custom fonts from files
			File fontFileMason = new File("data/mason.ttf");
			Font customFontMason = Font.createFont(Font.TRUETYPE_FONT, fontFileMason);

			File fontFile = new File("data/font.ttf");
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

			// Register the fonts with the graphics environment
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFontMason);
			ge.registerFont(customFont);

			// Set font sizes for labels and buttons
			customFontMason = customFontMason.deriveFont(Font.BOLD, 14f);
			customFont = customFont.deriveFont(Font.BOLD, 40f);
			testTitleLabel.setFont(customFont);
			submitButton.setFont(customFontMason);
			backButton.setFont(customFontMason);

		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private void frameSetup() {
		// Set the title of the window
		setTitle("Witcher Objects and Classes");
		// Set the size of the window
		setSize(1366, 768);
		// Use a border layout for the frame
		setLayout(new BorderLayout());

		// Add the menu bar
		setJMenuBar(menuBar);

		// Create a layered pane to hold the background and main content
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1366, 768);

		// Set bounds for the background label
		homeBackground.setBounds(0, 0, 1366, 768);
		// Add the background label to the layered pane at the lowest layer
		layeredPane.add(homeBackground, Integer.valueOf(0));

		// Create the main panel for the questions and options
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBounds(133, 84, 1100, 600);
		mainPanel.setBackground(Color.decode("#191C1D"));
		mainPanel.setOpaque(true);

		// Add the title panel and scroll pane to the main panel
		mainPanel.add(createTitlePanel(), BorderLayout.NORTH);
		mainPanel.add(createScrollPane(), BorderLayout.CENTER);

		// Add the main panel to the layered pane at a higher layer
		layeredPane.add(mainPanel, Integer.valueOf(1));

		// Add the layered pane to the frame
		add(layeredPane);

		// Set default close operation
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Make the window non-resizable
		setResizable(false);
		// Make the window visible
		setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		// Handle back button click
		if (event.getSource() == backButton) {
			returnToHomePage();
		}
		// Handle submit button click
		else if (event.getSource() == submitButton) {
			calculateScore();
		}
		// Handle menu item clicks
		else if (event.getSource() == menuItemHome) {
			setVisible(false); // Hide the current frame
			new Lesson1();
		} else if (event.getSource() == menuItemGame) {
			setVisible(false); // Hide the current frame
			new GamePage();
		} else if (event.getSource() == menuItemTest) {
			setVisible(false); // Hide the current frame
			new TestPage();
		} else if (event.getSource() == menuItemQuit) {
			dispose(); // Close the application
		}
	}

	private void calculateScore() {
		int score = 0;

		// Loop through each question to check the selected answers
		for (int i = 0; i < questions.length; i++) {
			for (int j = 0; j < options[i].length; j++) {
				if (optionButtons[i][j].isSelected() && optionButtons[i][j].getText().charAt(0) == answers[i]) {
					score++;
				}
			}
		}

		// Calculate the percentage score
		double percentage = (score / (double) questions.length) * 100;
		String formattedPercentage = String.format("%.2f", percentage);

		// Show a message based on the score
		if (percentage >= 70) {
			JOptionPane.showMessageDialog(this,
					"Congratulations! You scored " + formattedPercentage + "%. You can continue.", "Quiz Result",
					JOptionPane.INFORMATION_MESSAGE);
			// Add code to continue to the next part of the application
		} else {
			int choice = JOptionPane.showConfirmDialog(this,
					"You scored " + formattedPercentage + "%. Would you like to go back to the lesson or the game?",
					"Quiz Result", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choice == JOptionPane.YES_OPTION) {
				returnToHomePage(); // Go back to the lesson
			}
		}
	}

	private void returnToHomePage() {
		// Dispose the current frame and return to home screen
		this.dispose();
		new HomePage().setVisible(true);
	}

	public static void main(String[] args) {
		// Create and show the test page
		SwingUtilities.invokeLater(() -> new TestPage());
	}
}
