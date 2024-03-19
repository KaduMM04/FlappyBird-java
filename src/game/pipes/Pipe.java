package game.pipes;

import java.awt.Graphics;
import java.awt.Image;

import game.FlappyBird;

public class Pipe {
	
	private Integer pipeX;
	private Integer pipeY;
	private Integer pipeWidth;
	private Integer pipeHeight;
	private Integer velocityX ;
	private Image img;
	
	private FlappyBird flappyBird;
	
	private boolean passed = false;
	
	public Pipe(Image img, Integer x, Integer y, Integer width, Integer height, Integer velocityX) {
	    this.img = img;
	    this.pipeX = x;
	    this.pipeY = y;
	    this.pipeWidth = width;
	    this.pipeHeight = height;
	    this.velocityX = velocityX; // Atribuir valor inicial a velocityX
	}


	public Pipe(Image img, Integer boardWidth, int randomTopPipeY, Integer integer) {
		this.img = img;
	}

	public Integer getPipeX() {
		return pipeX;
	}

	public void setPipeX(Integer pipeX) {
		this.pipeX = pipeX;
	}

	public Integer getPipeY() {
		return pipeY;
	}

	public void setPipeY(Integer pipeY) {
		this.pipeY = flappyBird.getBoardHeight();
	}

	public Integer getPipeWidth() {
		return pipeWidth;
	}

	public void setPipeWidth(Integer pipeWidth) {
		this.pipeWidth = pipeWidth;
	}

	public Integer getPipeHeight() {
		return pipeHeight;
	}

	public void setPipeHeight(Integer pipeHeight) {
		this.pipeHeight = pipeHeight;
	}

	public FlappyBird getFlappyBird() {
		return flappyBird;
	}

	public void setFlappyBird(FlappyBird flappyBird) {
		this.flappyBird = flappyBird;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	
	public Integer getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(Integer velocityX) {
		this.velocityX = velocityX;
	}
	
	public void drawPipe(Graphics g) {
		  g.drawImage(img, pipeX, pipeY, pipeWidth, pipeHeight, null);
    }
	
	 public void movePipe() {
		  int newPipeX = pipeX + velocityX;
		  pipeX = newPipeX;
	 }
}
