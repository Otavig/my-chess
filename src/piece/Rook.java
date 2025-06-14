package piece;

import java.util.List;
import java.util.ArrayList;

import utils.Board;
import utils.Position;

public class Rook extends Piece {
	private int value = 5;
	private boolean moved = false;
	
	public Rook(String color) {
		super(color);
	}
	
	@Override
	public String getSymbol() {
		return color.equals("white") ? "♜":"♖";
	}

	@Override
	public List<Position> getMovesValid(Board board, int row, int col, boolean ignoreCheck) {
		List<Position> moves = new ArrayList<Position>();
		
		int[][] directions = {
			    {1, 0},    // Baixo
			    {-1, 0},   // Cima
			    {0, 1},    // Direita
			    {0, -1}    // Esquerda
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
	
	@Override
    public void markMoved() {
        if (!moved) moved = true;
    }
	
	public boolean getMoved() { 
		return this.moved;
	}
	
}
