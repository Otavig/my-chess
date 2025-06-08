package piece;

import java.util.List;
import java.util.ArrayList;

import utils.Board;
import utils.Position;

public class Rook extends Piece {
	private int value = 5;
	
	public Rook(String color) {
		super(color);
	}
	
	@Override
	public String getSymbol() {
		return color.equals("white") ? "♜":"♖";
	}

	@Override
	public List<Position> getMovesValid(Board board, int row, int col) {
		List<Position> moves = new ArrayList<Position>();
		
		boolean conditional = true;
		
		// Baixo
		for(int i = row+1; i<8 && conditional; i++)
			conditional = !board.addIfValid(moves, i, col, this.color);
		
		// Cima
		conditional = true;
		for(int i = row-1; i>=0 && conditional; i--)
			conditional = !board.addIfValid(moves, i, col, this.color);
		
	    // Direita (coluna aumenta)
		conditional = true;
		for(int j = col+1; j<8 && conditional; j++)
			conditional = !board.addIfValid(moves, row, j, this.color);
		
		// Esquerda (coluna diminui)
		conditional = true;
		for(int j = col-1; j>=0 && conditional; j++)
			conditional = !board.addIfValid(moves, row, j, this.color);
		
		return moves;
	}
}
