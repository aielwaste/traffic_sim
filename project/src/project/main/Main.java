package project.main;

import project.ui.PopupUI;
import project.ui.UI;

/**
 * A static class to demonstrate the visualization aspect of
 * simulation.
 */
public class Main {
	public static void main(String[] args) {
		UI ui = new PopupUI();
		
		Control control = new Control(ui);
		control.run();
	}
}