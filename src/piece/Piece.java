package piece;

import java.util.List;

import utils.Board;
import utils.Position;

public abstract class Piece {
	protected String color;
	
	public Piece(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return this.color;
	}
	
	public abstract String getSymbol();
	public abstract List<Position> getMovesValid(Board board, int row, int col, boolean ignoreCheck);
	public void markMoved() {}
}
