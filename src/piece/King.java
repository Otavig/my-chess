package piece;

import java.util.List;

import utils.Board;
import utils.Position;

public class King extends Piece {
	private int value = 3;
	
	public King(String color) {
		super(color);
	}
	
	@Override
	public String getSymbol() {
		return color.equals("white") ? "♚":"♔";
	}

	@Override
	public List<Position> getMovesValid(Board board, int row, int col) {
		return null;
	}
}
