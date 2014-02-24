import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;


public class Tetris extends Applet implements KeyListener{

	private static final int HEIGHT = 680, WIDTH = 640, BLOCK_SIZE = 32;
	private static final int X_OFFSET = 20, Y_OFFSET = 20, NEXTPIECEX = 360, NEXTPIECEY = 40;
	private static Piece currentPiece, nextPiece;
	private static Board board;
	private static int level = 1;
	private static double score;
	private static int lines;
	private final static Color[] colors = {Color.WHITE, Color.ORANGE, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.BLUE, Color.YELLOW, Color.RED};
	private static TetrisTimer timerTask;
	private static Timer timer;
	private static boolean gameOver = false;
	
	@Override
	public void init() {
		setSize(WIDTH, HEIGHT);
		currentPiece = new Piece();
		nextPiece = new Piece();
		board = new Board();
		timer = new Timer();
		timerTask = new TetrisTimer(this);
		timer.scheduleAtFixedRate(timerTask, 1000, 1000);
		setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(this);
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		g2d.setPaint(Color.GRAY);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		g2d.setPaint(Color.WHITE);
		g2d.drawString("Next Piece:", NEXTPIECEX, NEXTPIECEY-10);
		g2d.drawString("Score: "+(int)score, NEXTPIECEX, 390);
		g2d.drawString("Lines: "+lines, NEXTPIECEX, 430);
		g2d.drawString("Level: "+level, NEXTPIECEX, 470);
		g2d.drawString("Game developed by:", NEXTPIECEX, 550);
		g2d.drawString("Chinmay Pednekar", NEXTPIECEX+64, 590);
		g2d.drawString("ChiP™", NEXTPIECEX+64, 630);
		drawBoard(g2d);
		drawPiece(g2d);
		drawNextPiece(g2d);
		if(gameOver) {
			timerTask.cancel();
			g2d.setPaint(Color.GRAY);
			g2d.fillRect(X_OFFSET + 2*BLOCK_SIZE, Y_OFFSET + 8*BLOCK_SIZE, 6*BLOCK_SIZE, 4*BLOCK_SIZE);
			g2d.setPaint(Color.WHITE);
			g2d.drawString("GAME OVER", X_OFFSET + 3*BLOCK_SIZE, Y_OFFSET + 10*BLOCK_SIZE - 10);
			g2d.drawString("Score: "+(int)score, X_OFFSET + 3*BLOCK_SIZE, Y_OFFSET + 11*BLOCK_SIZE - 10);
			this.removeKeyListener(this.getKeyListeners()[0]);
		}

	}

	private void drawPiece(Graphics2D g2d) {
		int x = currentPiece.getxPos();
		int y = currentPiece.getyPos();
		for(int j=0; j<Piece.PIECE_SIZE; j++)
			for(int i=0; i<Piece.PIECE_SIZE; i++) {
				if(currentPiece.getBlock(j, i) !=0) {
					g2d.setPaint(colors[currentPiece.getBlock(j, i)]);
					g2d.fillRect(X_OFFSET+(x+i)*BLOCK_SIZE, Y_OFFSET+(y+j)*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
				}
			}
	}

	private void drawNextPiece(Graphics2D g2d) {
		for(int j=0; j<Piece.PIECE_SIZE; j++)
			for(int i=0; i<Piece.PIECE_SIZE; i++) {
				g2d.setPaint(colors[nextPiece.getBlock(j, i)]);
				g2d.fillRect(NEXTPIECEX+i*BLOCK_SIZE, NEXTPIECEY+j*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
			}
	}

	private void drawBoard(Graphics2D g2d) {
		for(int j=0; j<Board.HEIGHT; j++)
			for(int i=0; i<Board.WIDTH; i++) {
				g2d.setPaint(colors[board.getBlock(j, i)]);
				g2d.fillRect(X_OFFSET+i*BLOCK_SIZE, Y_OFFSET+j*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
			}
				
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_UP: if(board.canMove(currentPiece, 0, -1))
									currentPiece.rotate();
								 break;
			case KeyEvent.VK_DOWN: if(board.canMove(currentPiece, 0, 1)) {
										currentPiece.moveDown();
										score += 10 + level;
									}
									else {
										updateBoard();
									}
								   break;
			case KeyEvent.VK_LEFT: if(board.canMove(currentPiece, -1, 0))
										currentPiece.moveLeft();
			 						break;
			case KeyEvent.VK_RIGHT: if(board.canMove(currentPiece, 1, 0))
										currentPiece.moveRight();
			   						break;
			case KeyEvent.VK_SPACE: while(board.canMove(currentPiece, 0, 1)) {
										currentPiece.moveDown();
										score += 10 + level;
									}
									updateBoard();
									break;
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void timer() {
		if(board.canMove(currentPiece, 0, 1))
			currentPiece.moveDown();
		else {
			updateBoard();
		}
		repaint();
	}

	private void updateBoard() {
		board.store(currentPiece);
		currentPiece = nextPiece;
		nextPiece = new Piece();
		int num = board.deletePossibleLines();
		lines += num;
		score += ( (num-1)/10.0*num + num ) * (Board.WIDTH*10) + level*10;
		level = (int) (score/10000) + 1;
		if(!board.canMove(currentPiece, 0, 1)) {
			gameOver = true;
		}
	}

}
