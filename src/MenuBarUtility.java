
// Import statements
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarUtility {

	// Method to create a menu bar
	public static JMenuBar createMenuBar(ActionListener actionListener, JMenuItem menuItemOpening,
			JMenuItem menuItemGame, JMenuItem menuItemCharacterSelect, JMenuItem menuItemQuit) {

		// Create menu bar
		JMenuBar menuBar = new JMenuBar();

		// Create menu
		JMenu menu = new JMenu("Menu");

		// Add ActionListener to menu items using the provided ActionListener
		menuItemOpening.addActionListener(actionListener);
		menuItemGame.addActionListener(actionListener);
		menuItemCharacterSelect.addActionListener(actionListener);
		menuItemQuit.addActionListener(actionListener);

		// Add menu items to the menu and separate them with separators
		menu.add(menuItemOpening);
		menu.addSeparator();
		menu.add(menuItemGame);
		menu.addSeparator();
		menu.add(menuItemCharacterSelect);
		menu.addSeparator();
		menu.add(menuItemQuit);

		// Add the menu to the menu bar
		menuBar.add(menu);

		// Return the created menu bar
		return menuBar;
	}

}
