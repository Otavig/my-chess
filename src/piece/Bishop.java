package piece;

import java.util.ArrayList;
import java.util.List;

import utils.Board;
import utils.Position;

public class Bishop extends Piece {
	private int value = 3;
	
	public Bishop(String color) {
		super(color);
	}
	
	@Override
	public String getSymbol() {
		return color.equals("white") ? "♝":"♗";
	}

	@Override
	public List<Position> getMovesValid(Board board, int row, int col, boolean ignoreCheck) {
	    List<Position> moves = new ArrayList<>();
	    
	    int[][] directions = {
	    	    {1, 1},   // Direita Baixo
	    	    {1, -1},  // Esquerda Baixo
	    	    {-1, 1},  // Direita Cima
	    	    {-1, -1}  // Esquerda Cima
	    	};

	    for (int[] dir : directions) {
	    	int i = row + dir[0];
	    	int j = col + dir[1];
	    	boolean close = true;

	    	while (i >= 0 && i < 8 && j >= 0 && j < 8 && close) {
	    		close = !board.addIfValid(moves, i, j, this.color);
	    	    i += dir[0];
	    	    j += dir[1];
	    	}
	    }
	    
	    return moves;
	}
}
