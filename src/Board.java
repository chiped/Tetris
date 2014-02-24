
public class Board {
	public final static int HEIGHT = 20;
	public final static int WIDTH = 10;
	private int[][] boardMatrix;
	public Board() {
		boardMatrix = new int[HEIGHT][WIDTH];
	}
	public boolean store(Piece p) {
		int x = p.getxPos();
		int y = p.getyPos();
		try {
		for(int j = 0; j < Piece.PIECE_SIZE; j++)
			for(int i = 0; i < Piece.PIECE_SIZE; i++)
				if(p.getBlock(j, i)!=0)
					boardMatrix[y+j][x+i] = p.getBlock(j, i);
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}
	private void deleteLine(int line) {
		for(int j = line; j > 0; j--)
			boardMatrix[j] = boardMatrix[j-1];
		boardMatrix[0] = new int[WIDTH];
	}
	public int deletePossibleLines() {
		int deletedLines = 0;
		int j = HEIGHT-1;
		while(j>=0) {
			boolean deletePossible = true;
			for(int i = 0; i < WIDTH; i++)
				if(boardMatrix[j][i]==0) {
					deletePossible = false;
					break;
				}
			if(deletePossible) {
				deleteLine(j);
				deletedLines++;
			}
			else
				j--;
		}
		return deletedLines;
	}
	public boolean canMove(Piece p, int xDisp, int yDisp) {
		if(yDisp < 0) {
			p = p.rotatedPiece();
			yDisp = 0;
		}
		int x = p.getxPos() + xDisp;
		int y = p.getyPos() + yDisp;
		try {
			for(int j = 0; j < Piece.PIECE_SIZE; j++)
				for(int i = 0; i < Piece.PIECE_SIZE; i++)
					if(p.getBlock(j, i)!=0)
						if(boardMatrix[y+j][x+i] !=0 )
							return false;
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
		return true;
	}
	public int getBlock(int y, int x) {
		return boardMatrix[y][x];
	}
}
