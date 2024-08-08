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
 * This is the Lesson page where the user will be able to learn all the information
 * 
 * ADDED FEATURES
 * ==============
 * - Each lesson is a new class
 * - After finishing all lessons, user recommended to take test and game
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LessonPage extends JFrame implements ActionListener {

	// Buttons for navigating to next and previous lessons
	protected JButton nextButton = new JButton("Next");
	protected JButton backButton = new JButton("Back");

	// Label to display the title of the lesson
	private JLabel lessonTitleLabel = new JLabel();

	// Create new ImageIcon for background image
	private ImageIcon background = new ImageIcon("images/LessonBackground.jpg");

	// Calculate the scaled size of the image while preserving aspect ratio
	private Image scaledImage = background.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);

	// Create a new ImageIcon with the scaled image
	private ImageIcon scaledBackground = new ImageIcon(scaledImage);

	// Declare JLabel for storing the background image
	private JLabel lessonBackground = new JLabel(scaledBackground);

	// Constructor to set up the lesson page
	public LessonPage(String title) {
		// Set the title of the window
		setTitle(title);

		// Set the size of the window
		setSize(1366, 768);

		// Set the layout manager to BorderLayout
		setLayout(new BorderLayout());

		// Set up the background image
		lessonBackground.setBounds(0, 0, 1366, 768);

		// Create a layered pane to hold the background and main panels
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1366, 768);

		// Add the background image to the layered pane at the lowest layer
		layeredPane.add(lessonBackground, Integer.valueOf(0));

		// Set up the main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBounds(133, 84, 1100, 600);
		mainPanel.setBackground(Color.decode("#191C1D"));
		mainPanel.setOpaque(true);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		// Set up the title label
		lessonTitleLabel.setText(title);
		lessonTitleLabel.setForeground(Color.WHITE);
		lessonTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lessonTitleLabel.setHorizontalAlignment(JLabel.CENTER);

		// Set up the navigation panel for next and back buttons
		JPanel navPanel = new JPanel();
		navPanel.setOpaque(false);
		navPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// Add action listeners to the buttons
		backButton.addActionListener(this);
		nextButton.addActionListener(this);

		// Add buttons to the navigation panel
		navPanel.add(backButton);
		navPanel.add(nextButton);

		// Add components to the main panel
		mainPanel.add(lessonTitleLabel, BorderLayout.NORTH);
		mainPanel.add(createContentPanel(), BorderLayout.CENTER);
		mainPanel.add(navPanel, BorderLayout.SOUTH);

		// Add the main panel to the layered pane at a higher layer
		layeredPane.add(mainPanel, Integer.valueOf(1));

		// Add the layered pane to the frame
		add(layeredPane);

		// Set default close operation to exit the application
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Prevent resizing of the window
		setResizable(false);

		// Make the window visible
		setVisible(true);
	}

	// Method to create the content panel, to be overridden in subclasses
	protected JPanel createContentPanel() {
		// This will be overridden in subclasses to provide specific content
		return new JPanel();
	}

	@Override
	// Handle button clicks, to be overridden in subclasses for specific actions
	public void actionPerformed(ActionEvent e) {
		// This will be overridden in subclasses for specific navigation
	}

	public static void main(String[] args) {
		// Run the Lesson1 frame on the event dispatch thread
		SwingUtilities.invokeLater(() -> new Lesson1());
	}
}
