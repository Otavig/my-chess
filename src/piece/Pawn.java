package piece;

import java.util.ArrayList;
import java.util.List;

import utils.Board;
import utils.Position;

public class Pawn extends Piece {
	private int value = 1;
	private boolean moved = false;
	
	public Pawn(String color) {
		super(color);
	}
	
	@Override
	public String getSymbol() {
		return color.equals("white") ? "♟":"♙";
	}

	@Override
	public List<Position> getMovesValid(Board board, int row, int col, boolean ignoreCheck) {
	    List<Position> moves = new ArrayList<>();
	    int direction = color.equals("white") ? 1 : -1;

	    int nextRow = row + direction;

	    // 1 casa
	    if (board.isInsideTheBoard(nextRow, col) &&
	        board.getPiece(nextRow, col) == null) {
	        moves.add(new Position(nextRow, col));

	        // 2 casas 
	        int twoStepsRow = row + 2 * direction;
	        if (!moved && board.isInsideTheBoard(twoStepsRow, col) &&
	            board.getPiece(twoStepsRow, col) == null) {
	            moves.add(new Position(twoStepsRow, col));
	        }
	    }

	    // Diagonais 
	    for (int dCol = -1; dCol <= 1; dCol += 2) {
	        int diagCol = col + dCol;
	        if (board.isInsideTheBoard(nextRow, diagCol)) {
	            Piece target = board.getPiece(nextRow, diagCol);
	            if (target != null && !target.getColor().equals(this.color)) {
	                moves.add(new Position(nextRow, diagCol));
	            }
	        }
	    }

	    return moves;
	}
	
	@Override
    public void markMoved() {
        if (!moved) moved = true;
    }
	
	public boolean getMoved() { 
		return this.moved;
	}
}
