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
	public List<Position> getMovesValid(Board board, int row, int col, boolean ignoreCheck) {
	    List<Position> moves = new ArrayList<>();

	    int[][] directions = {
	        {1, 0}, {-1, 0}, {0, 1}, {0, -1},
	        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
	    };

	    // Movimentos normais do Rei
	    for (int[] direction : directions) {
	        board.addIfValid(moves, row + direction[0], col + direction[1], this.color);
	    }

	    // Verificar possibilidade de Roque
	    if (!ignoreCheck) {
	        if (!moved && !board.isKingInCheck(this.color)) {
	            checkCastling(board, row, col, moves);
	        }
	    }

	    return moves;
	}

	private void checkCastling(Board board, int row, int col, List<Position> moves) {
	    // Roque pequeno (lado do rei)
	    if (canCastle(board, row, col, 7)) {
	        moves.add(new Position(row, col + 2));
	    }

	    // Roque grande (lado da dama)
	    if (canCastle(board, row, col, 0)) {
	        moves.add(new Position(row, col - 2));
	    }
	}

	private boolean canCastle(Board board, int row, int col, int rookCol) {
	    Piece rook = board.squares[row][rookCol] != null ? board.squares[row][rookCol].getPiece() : null;
	    
	    if (!(rook instanceof Rook) || ((Rook) rook).getMoved()) {
	        return false;
	    }

	    int step = rookCol == 0 ? -1 : 1;
	    int start = col + step;
	    int end = rookCol == 0 ? col - 1 : col + 1;

	    // Casas entre rei e torre devem estar vazias
	    for (int i = start; step > 0 ? i <= end : i >= end; i += step) {
	    	if (board.squares[row][i].getPiece() != null) {
	    	    return false;
	    	}
	    }

	    // Nenhuma casa que o rei passa pode estar sob ataque
	    int kingSteps = (rookCol == 0) ? 3 : 2;

	    for (int i = 0; i <= kingSteps; i++) {
	        int kingCol = col + (step * i);
	        if (board.isSquareUnderAttack(row, kingCol, this.color)) {
	            return false;
	        }
	    }
	    
	    return true;
	}
	
	@Override
    public void markMoved() {
        if (!moved) moved = true;
    }
	
	public boolean getMoved() { 
		return this.moved;
	}

}
