import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class Lesson3 extends JFrame implements ActionListener {

	// Label for the title of the lesson
	private JLabel titleLabel = new JLabel("Lesson 3: Advanced OOP Concepts");

	// Text area for displaying the lesson content
	private JTextArea lessonContent = new JTextArea();

	// Buttons for navigating to the next lesson or going back
	private JButton nextButton = new JButton("Next");
	private JButton backButton = new JButton("Back");

	// Create new ImageIcon for background image
	private ImageIcon background = new ImageIcon("images/LessonBackground.jpg");

	// Calculate the scaled size while preserving aspect ratio
	private Image scaledImage = background.getImage().getScaledInstance(1366, 768, Image.SCALE_SMOOTH);

	// Create a new ImageIcon with the scaled image
	private ImageIcon scaledBackground = new ImageIcon(scaledImage);

	// Declare JLabel for storing the background image
	private JLabel lessonBackground = new JLabel(scaledBackground);

	// Constructor to set up the lesson page
	public Lesson3() {
		// Set the layout manager to null for absolute positioning
		setLayout(null);

		// Set up the fonts
		fontSetup();

		// Set up the frame properties
		frameSetup();
	}

	// Method to create the content panel
	private void createContentPanel() {
		// Set the title color to white
		titleLabel.setForeground(Color.decode("#191C1D"));

		// Set the lesson content text
		lessonContent.setText("In this lesson, we will discuss some advanced object-oriented programming concepts.\n\n"
				+ "1. Static Method: A method associated with the class, not instances.\n"
				+ "   Example: class Math {\n" + "                static int add(int a, int b) {\n"
				+ "                    return a + b;\n" + "                }\n" + "            }\n"
				+ "            Math.add(5, 10);\n\n"
				+ "2. Instance Method: A method associated with an instance of a class.\n" + "   Example: class Car {\n"
				+ "                void start() {\n" + "                    System.out.println(\"Car started.\");\n"
				+ "                }\n" + "            }\n" + "            Car myCar = new Car();\n"
				+ "            myCar.start();\n\n"
				+ "3. Class Variable: A variable shared among all instances of a class.\n"
				+ "4. Instance Variable: A variable specific to an instance of a class.\n\n"
				+ "Now you have a good understanding of the fundamental and advanced concepts in object-oriented programming.");

		// Set the lesson content color to white and make it non-editable
		lessonContent.setForeground(Color.white);
		lessonContent.setOpaque(false);
		lessonContent.setEditable(false);

		// Set the properties of the next button
		nextButton.setBackground(Color.decode("#191C1D"));
		nextButton.setForeground(Color.WHITE);
		nextButton.addActionListener(this);

		// Set the properties of the back button
		backButton.setBackground(Color.decode("#191C1D"));
		backButton.setForeground(Color.WHITE);
		backButton.addActionListener(this);

		// Create the content panel and set its properties
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(133, 84, 1100, 600);
		contentPanel.setBackground(Color.decode("#191C1D"));
		contentPanel.setLayout(new BorderLayout());

		// Add the title and lesson content to the content panel
		contentPanel.add(titleLabel, BorderLayout.NORTH);
		contentPanel.add(new JScrollPane(lessonContent), BorderLayout.CENTER);

		// Create the button panel and add the navigation buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.setOpaque(false);
		buttonPanel.add(backButton);
		buttonPanel.add(nextButton);

		// Add the button panel to the content panel
		contentPanel.add(buttonPanel, BorderLayout.SOUTH);

		// Add the content panel to the lesson background
		lessonBackground.add(contentPanel);
	}

	// Method to set up fonts
	private void fontSetup() {
		try {
			// Load the custom fonts from files
			File fontFileMason = new File("data/mason.ttf");
			Font customFontMason = Font.createFont(Font.TRUETYPE_FONT, fontFileMason);

			File fontFile = new File("data/font.ttf");
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

			// Register the custom fonts with the graphics environment
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFontMason);
			ge.registerFont(customFont);

			// Set the font sizes
			customFontMason = customFontMason.deriveFont(Font.BOLD, 14f);
			customFont = customFont.deriveFont(Font.BOLD, 40f);

			// Apply the fonts to the components
			titleLabel.setFont(customFont);
			lessonContent.setFont(customFontMason);
			nextButton.setFont(customFontMason);
			backButton.setFont(customFontMason);

		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	// Method to set up the frame properties
	private void frameSetup() {
		// Set the title of the window
		setTitle("Lesson 3: Advanced OOP Concepts");

		// Set the size of the window
		setSize(1366, 768);

		// Set the layout manager to null for absolute positioning
		setLayout(null);

		// Set the bounds for the background label
		lessonBackground.setBounds(0, 0, 1366, 768);

		// Add the background label to the content pane
		getContentPane().add(lessonBackground);

		// Create and add the content panel
		createContentPanel();

		// Set default close operation to exit the application
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Prevent resizing of the window
		setResizable(false);

		// Make the window visible
		setVisible(true);
	}

	// Handle button click events
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == backButton) {
			// Go back to the previous lesson when back button is clicked
			new Lesson2();
			dispose();
		} else if (event.getSource() == nextButton) {
			// Display a congratulations message when next button is clicked
			JOptionPane.showMessageDialog(this,
					"Congrats on completing this course! Consider doing the activity or game to test your knowledge.",
					"Congratulations", JOptionPane.INFORMATION_MESSAGE);

			// Go back to the home page
			new HomePage();
			dispose();
		}
	}

	// Main method to run the Lesson3 frame
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Lesson3());
	}
}
