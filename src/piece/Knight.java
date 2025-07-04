package piece;

import java.util.ArrayList;
import java.util.List;

import utils.Board;
import utils.Position;

public class Knight extends Piece{
	private int value = 3;
	
	public Knight(String color) {
		super(color);
	}
	
	@Override
	public String getSymbol() {
		return color.equals("white") ? "♞":"♘";
	}

	@Override
	public List<Position> getMovesValid(Board board, int row, int col, boolean ignoreCheck) {
		List<Position> moves = new ArrayList<>();
		
		if(row < 8 && row >= 0 && col < 8 && col >= 0) {
			// Direita cima
			board.addIfValid(moves, row-2, col+1, this.color);
			board.addIfValid(moves, row-1, col+2, this.color);
			
			// Esquerda cima
			board.addIfValid(moves, row-2, col-1, this.color);
			board.addIfValid(moves, row-1, col-2, this.color);
			
			// Esquerda baixo
			board.addIfValid(moves, row+2, col-1, this.color);
			board.addIfValid(moves, row+1, col-2, this.color);
			
			// Direita baixo
			board.addIfValid(moves, row+2, col+1, this.color);
			board.addIfValid(moves, row+1, col+2, this.color);
		}
		
		return moves;
	}
}
