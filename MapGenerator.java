import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	/**
	 * MapGenerator constructor that creates a game map with the given dimensions
	 * @param row
	 * @param col
	 */
	public MapGenerator(int row, int col) {
		map = new int[row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j=0; j<map[0].length; j++) {
				map[i][j] = 1;
			}
		}
		
		brickWidth = 540 / col;
		brickHeight = 150/row;
	}
	/**
	 * Method that draws the bricks of a map onto the screen
	 * @param g
	 */
	public void draw(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j=0; j<map[0].length; j++) {
				if(map[i][j] > 0)
				{
					g.setColor(Color.white);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.BLACK);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
				}
			}
		}
	}
	/**
	 * method to set the value of a brick
	 * It is either hit (0) or unhit (1)
	 * @param value
	 * @param row
	 * @param col
	 */
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}
}