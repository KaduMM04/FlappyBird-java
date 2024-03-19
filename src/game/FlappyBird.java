package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.bird.Bird;
import game.pipes.Pipe;

public class FlappyBird extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private Integer boardWidth = 360;
	private Integer boardHeight = 640;
	
	//Images
	private Image backGroundImg;
	private Image birdImg;
	private Image topPipeImg;
	private Image bottomPipeImg;
	
	//Bird
	private Bird bird;
	
	
	
	//private ArrayList<Pipes> topPipes;
    //private ArrayList<Pipes> bottomPipes
	private ArrayList<Pipe> pipes;
	
    
	private Timer gameLoop;
	private Timer placesPipesTimer;
	
	public FlappyBird() {
		setPreferredSize(new Dimension(boardWidth, boardHeight));
		//setBackground(Color.blue);
		setFocusable(true);
		
		//load images
		backGroundImg = loadImage("/flappybirdbg.png");
	    Image birdImg = loadImage("/flappybird.png");
	    topPipeImg = loadImage("/toppipe.png");
	    bottomPipeImg = loadImage("/bottompipe.png");
	    
	    //topPipes = new ArrayList<>();
        // bottomPipes = new ArrayList<>();
	   
	    //bird
	    bird = new Bird(birdImg, this);
	    addKeyListener(bird);
		setFocusTraversalKeysEnabled(false);
		
		
		pipes = new ArrayList<>();
		//place pipes timer
		placesPipesTimer = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				placePipes();
			}
		});
		placesPipesTimer.start();
		
	    //game timer
	    gameLoop = new Timer(1000/60, this);
	    gameLoop.start();
	}
	
	public Integer getBoardWidth() {
		return boardWidth;
	}

	public void setBoardWidth(Integer boardWidth) {
		this.boardWidth = boardWidth;
	}

	public Integer getBoardHeight() {
		return boardHeight;        
	}

	public void setBoardHeight(Integer boardHeight) {
		this.boardHeight = boardHeight;
	}

	public Image getBackGroundImg() {
		return backGroundImg;
	}

	public void setBackGroundImg(Image backGroundImg) {
		this.backGroundImg = backGroundImg;
	}

	public Image getBirdImg() {
		return birdImg;
	}

	public void setBirdImg(Image birdImg) {
		this.birdImg = birdImg;
	}

	public Image getTopPipeImg() {
		return topPipeImg;
	}

	public void setTopPipeImg(Image topPipeImg) {
		this.topPipeImg = topPipeImg;
	}

	public Image getBottomPipeImg() {
		return bottomPipeImg;
	}

	public void setBottomPipeImg(Image bottomPipeImg) {
		this.bottomPipeImg = bottomPipeImg;
	}

	public Bird getBird() {
		return bird;
	}

	public void setBird(Bird bird) {
		this.bird = bird;
	}
	
/*	public ArrayList<Pipes> getTopPipes() {
        return this.topPipes;
    }

    // Método para obter a lista de tubos inferiores
    public ArrayList<Pipes> getBottomPipes() {
        return this.bottomPipes;
    }
	*/
	private Image loadImage(String path) {
	    URL url = getClass().getResource(path);
	    if (url == null) {
	        System.err.println("Imagem não encontrada: " + path);
	        return null;
	    }
	    return new ImageIcon(url).getImage();
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    draw(g);
	}
	
	public void draw(Graphics g) {
		System.out.println("draw");
		//backgroud
		g.drawImage(backGroundImg, 0, 0, boardWidth, boardHeight, null);
		
		//bird
		bird.draw(g);
		
		//pipes
		for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.drawPipe(g);
        }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
		if (bird.getGameOver()) {
			placesPipesTimer.stop();
			gameLoop.stop();
		}
	}
	
	
	 public void move() {
	       // Move the bird
	       bird.move();
	       // Move the Pipes
	       for (int i = 0; i < pipes.size(); i++) {
				Pipe pipe = pipes.get(i);
				pipe.setPipeX(pipe.getPipeX() + pipe.getVelocityX());
				
				if (collision(bird, pipe)) {
					bird.setGameOver(true);
				}
			}
	 }
	 
	 public void placePipes() {
	        int pipeWidth = 64; // Escalado por 1/6
	        int pipeHeight = 500;

	        // Altura aleatória para o tubo superior
	        int randomPipeY = (int) (0 - pipeHeight/4 - Math.random()*(pipeHeight/2));
	        int openingSpace = boardHeight / 4;

	        // Crie o tubo superior
	        Pipe topPipe = new Pipe(topPipeImg, boardWidth, randomPipeY, pipeWidth, pipeHeight, -4); // Defina o valor de velocityX aqui
	        pipes.add(topPipe);

	        // Crie o tubo inferior
	        Pipe bottomPipe = new Pipe(bottomPipeImg, boardWidth, topPipe.getPipeY() + pipeHeight + openingSpace, pipeWidth, pipeHeight, -4);
	        pipes.add(bottomPipe);
	    }
	
	 public boolean collision(Bird b, Pipe p) {
		 return b.getX() < p.getPipeX()+ p.getPipeWidth() &&   //b's top left corner doesn't reach p's top right corner
				b.getX() + b.getWidth() > p.getPipeX() &&      //b's top right corner doesn't passes p's top left corner
				b.getY() < p.getPipeY() + p.getPipeHeight() && //b's top left corner doesn't reach p's bottom left corner
				b.getY() + b.getHeight() > p.getPipeY();       //b's bottom left corner doesn't passes p's top left corner
	 }
}
