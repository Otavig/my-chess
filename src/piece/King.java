package piece;

import java.util.ArrayList;
import java.util.List;

import utils.Board;
import utils.Position;

public class King extends Piece {
	private int value = 3;
	private boolean moved = false;
	
	public King(String color) {
		super(color);
	}
	
	@Override
	public String getSymbol() {
		return color.equals("white") ? "♚":"♔";
	}

	@Override
	public List<Position> getMovesValid(Board board, int row, int col) {
		List<Position> moves = new ArrayList<>();
	    int[][] directions = {
		        {1, 0},   // Baixo
		        {-1, 0},  // Cima
		        {0, 1},   // Direita
		        {0, -1},  // Esquerda
		        {1, 1},   // Direita Baixo
		        {1, -1},  // Esquerda Baixo
		        {-1, 1},  // Direita Cima
		        {-1, -1}  // Esquerda Cima
		};
	    
	    for(int[] direction : directions) 
	    	board.addIfValid(moves, row+direction[0], col+direction[1], this.color);
	    
		return moves;
	}
}
