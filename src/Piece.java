import java.util.Random;


public class Piece {

	private int xPos, yPos;
	private int type, rotation;
	final static int PIECE_SIZE = 4;
	private final static int[][][][] PIECES = {
											//I type
											{
												{
													{0,1,0,0},
													{0,1,0,0},
													{0,1,0,0},
													{0,1,0,0}				
												},
												{
													{0,0,0,0},
													{1,1,1,1},
													{0,0,0,0},
													{0,0,0,0}				
												},
												{
													{0,1,0,0},
													{0,1,0,0},
													{0,1,0,0},
													{0,1,0,0}				
												},
												{
													{0,0,0,0},
													{1,1,1,1},
													{0,0,0,0},
													{0,0,0,0}				
												}
											},
											//Z type
											{
												{
													{0,0,0,0},
													{2,2,0,0},
													{0,2,2,0},
													{0,0,0,0}				
												},
												{
													{0,2,0,0},
													{2,2,0,0},
													{2,0,0,0},
													{0,0,0,0}				
												},
												{
													{0,0,0,0},
													{2,2,0,0},
													{0,2,2,0},
													{0,0,0,0}				
												},
												{
													{0,2,0,0},
													{2,2,0,0},
													{2,0,0,0},
													{0,0,0,0}				
												}
											},
											//Z reversed
											{
												{
													{0,0,0,0},
													{0,3,3,0},
													{3,3,0,0},
													{0,0,0,0}				
												},
												{
													{3,0,0,0},
													{3,3,0,0},
													{0,3,0,0},
													{0,0,0,0}				
												},
												{
													{0,0,0,0},
													{0,3,3,0},
													{3,3,0,0},
													{0,0,0,0}				
												},
												{
													{3,0,0,0},
													{3,3,0,0},
													{0,3,0,0},
													{0,0,0,0}				
												}
											},
											//T type
											{
												{
													{0,4,0,0},
													{4,4,4,0},
													{0,0,0,0},
													{0,0,0,0}				
												},
												{
													{0,4,0,0},
													{0,4,4,0},
													{0,4,0,0},
													{0,0,0,0}				
												},
												{
													{0,0,0,0},
													{4,4,4,0},
													{0,4,0,0},
													{0,0,0,0}				
												},
												{
													{0,4,0,0},
													{4,4,0,0},
													{0,4,0,0},
													{0,0,0,0}				
												}
											},
											//L type
											{
												{
													{0,5,0,0},
													{0,5,0,0},
													{0,5,5,0},
													{0,0,0,0}				
												},
												{
													{0,0,0,0},
													{5,5,5,0},
													{5,0,0,0},
													{0,0,0,0}				
												},
												{
													{5,5,0,0},
													{0,5,0,0},
													{0,5,0,0},
													{0,0,0,0}				
												},
												{
													{0,0,5,0},
													{5,5,5,0},
													{0,0,0,0},
													{0,0,0,0}				
												}
											},
											//L reversed
											{
												{
													{0,6,0,0},
													{0,6,0,0},
													{6,6,0,0},
													{0,0,0,0}				
												},
												{
													{6,0,0,0},
													{6,6,6,0},
													{0,0,0,0},
													{0,0,0,0}				
												},
												{
													{0,6,6,0},
													{0,6,0,0},
													{0,6,0,0},
													{0,0,0,0}				
												},
												{
													{0,0,0,0},
													{6,6,6,0},
													{0,0,6,0},
													{0,0,0,0}				
												}
											},
											//Box type
											{
												{
													{0,0,0,0},
													{0,7,7,0},
													{0,7,7,0},
													{0,0,0,0}				
												},
												{
													{0,0,0,0},
													{0,7,7,0},
													{0,7,7,0},
													{0,0,0,0}				
												},
												{
													{0,0,0,0},
													{0,7,7,0},
													{0,7,7,0},
													{0,0,0,0}				
												},
												{
													{0,0,0,0},
													{0,7,7,0},
													{0,7,7,0},
													{0,0,0,0}				
												}
											}
		
									};
	private static int[][][] startPos = {
											//I type
											{
												{3,0},
												{3,-1},
												{3,0},
												{3,-1}
											},
											//Z type
											{
												{3,-1},
												{3,0},
												{3,-1},
												{3,0}
											},
											//Z reversed
											{
												{3,-1},
												{3,0},
												{3,-1},
												{3,0}
											},
											//T type
											{
												{3,0},
												{3,0},
												{3,-1},
												{3,0}
											},
											//L type
											{
												{3,0},
												{3,-1},
												{4,0},
												{4,0}
											},
											//L reversed
											{
												{4,0},
												{3,0},
												{3,0},
												{3,-1},
											},
											//Box type
											{
												{3,-1},
												{3,-1},
												{3,-1},
												{3,-1}
											}
										};
	public Piece () {
		Random r = new Random();
		type = r.nextInt(7);
		rotation = r.nextInt(4);
		yPos = startPos[type][rotation][1];
		xPos = startPos[type][rotation][0];
	}
	public Piece(Piece p) {
		xPos = p.getxPos();
		yPos = p.getyPos();
		type = p.getType();
		rotation = p.getRotation();
	}
	public int getxPos() {
		return xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public int getType() {
		return type;
	}
	public int getRotation() {
		return rotation;
	}
	public static int getPieceSize() {
		return PIECE_SIZE;
	}
	public int[][] getPiece() {
		return PIECES[type][rotation];
	}
	public int getBlock(int x, int y) {
		return PIECES[type][rotation][x][y];
	}
	public void moveLeft() {
		xPos--;
	}
	public void moveRight() {
		xPos++;
	}
	public void moveDown() {
		yPos++;
	}
	public void rotate() {
		rotation = (rotation+1)%4;
	}
	public Piece rotatedPiece() {
		Piece q = new Piece(this);
		q.rotate();
		return q;
	}
}
