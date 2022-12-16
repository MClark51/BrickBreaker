import javax.swing.JFrame;

public class Main {

	/**
	 * Main method
	 * Creates the JFrame object to be displayed
	 * Creates a new gameplay
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame obj = new JFrame();
		GamePlay gameplay = new GamePlay();
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Breakout");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
	}
}
