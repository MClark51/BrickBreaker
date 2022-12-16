import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener{
	//initial variables
	private boolean play = false;
	private int score = 0;
	private int totalBricks = 21;
	private Timer timer;
	private int delay = 2;
	private int playerX = 310;
	private int ballPosX = 120;
	private int ballPosY = 350;
	private int ballXDir = -1;
	private int ballYDir = -2;
	
	private MapGenerator map;
	
	/**
	 * Constructor for GamePlay
	 * Generates a new map and creates a timer
	 */
	public GamePlay() {
		map = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	/***
	 * Method to paint all map objects, bricks, ball, paddle, score, and border
	 * this method is continuously called
	 */
	public void paint(Graphics g) {
		//background
		g.setColor(Color.BLACK);
		g.fillRect(1, 1,  692,  592);
		//map
		map.draw((Graphics2D)g);
		//border
		g.setColor(Color.YELLOW);
		g.fillRect(0,  0,  3,  592);
		g.fillRect(0,  0,  692,  3);
		g.fillRect(691,  0,  3,  592);
		//paddle
		g.setColor(Color.GREEN);
		g.fillRect(playerX,  550,  100,  8);
		//ball
		g.setColor(Color.BLUE);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		//if the ball goes out of the border
		if(ballPosY > 570) {
			play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over, Score: " + score, 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
		}
		//game is over
		if (totalBricks <= 0) {
			play = false;
			ballXDir = 0;
			ballYDir = 0;
			g.setColor(Color.YELLOW);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won! Score: " + score, 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
		}
		
		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		if(play) {
			if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8)))
				ballYDir = -ballYDir;
			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j< map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle (ballPosX, ballPosY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0,  i,  j);
							totalBricks --;
							score += 5;
							
							if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) 
								ballXDir = -ballXDir;
							else 
								ballYDir = -ballYDir;
							break A;
						}
					}
				}
			}
			
			ballPosX += ballXDir;
			ballPosY += ballYDir;
			if (ballPosX < 0)
				ballXDir = -ballXDir;
			if (ballPosY < 0)
				ballYDir = -ballYDir;
			if (ballPosX > 670)
				ballXDir = -ballXDir;
		}
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) 
				playerX = 600;
			else 
				moveRight();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) 
				playerX = 10;
			else 
				moveLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			//starts game if enter is pressed
			if(!play) {
				play = true;
				ballPosX = 120;
				ballPosY = 350;
				ballXDir = -1;
				ballYDir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGenerator(3,7);
				
				repaint();
			}else{
                //keep playing
            }
		}
	}
	//Moves paddle to the right
	public void moveRight() {
		play = true;
		playerX += 20;
	}
	//Moves paddle to the left
	public void moveLeft() {
		play = true;
		playerX -= 20;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
        //Blank method because it is not needed, but required to be defined
    }

	@Override
	public void keyTyped(KeyEvent e) {
        //Blank method because it is not needed, but required to be defined
    }
	
}
