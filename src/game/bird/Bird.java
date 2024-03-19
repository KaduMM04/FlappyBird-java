package game.bird;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.FlappyBird;

public class Bird implements KeyListener{
	
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private Image img;
	
	//logicgame
	private Integer velocityY;
	private Integer gravity; 
	
	private Boolean gameOver = false;
	
	private FlappyBird flappyBird;
	
	public Bird(Image img, FlappyBird flappyBird) {
		this.img = img;
		this.setFlappyBird(flappyBird);
		
		 // Set initial position and size of the bird
		this.x = flappyBird.getBoardWidth() / 8;
		this.y = flappyBird.getBoardHeight() / 2;
		this.width = 34;
		this.height = 24;
		this.velocityY = 0;
		this.gravity = 1;
	}

	public int getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}
	
	 public Integer getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(Integer velocityY) {
		this.velocityY = velocityY;
	}
	
	public FlappyBird getFlappyBird() {
		return flappyBird;
	}

	public void setFlappyBird(FlappyBird flappyBird) {
		this.flappyBird = flappyBird;
	}
	
	public Boolean getGameOver() {
		return gameOver;
	}

	public void setGameOver(Boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public void draw(Graphics g) {
	        g.drawImage(img, x, y, width, height, null);
	    }
	 
	public void move() {
		//bird
		 velocityY += gravity; 
		 y += velocityY;
		 y = Math.max(y, 0);
		 
		 if (y > flappyBird.getHeight()) {
			 gameOver = true;
		 }
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			velocityY = -9;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}
