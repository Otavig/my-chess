package utils;

import piece.Piece;

public class Square {
	private int row;
	private int col;
	private boolean white;
	private Piece piece;
	
	public Square(int row, int col) {
		this.row = row;
		this.col = col;
		this.white = (row + col) % 2 == 0; // 1 é branco | 0 é preto
	}
	
	public Square() {}
	
	public String view() {
		String state = white ? "1" : "0";
		return (piece != null) ? piece.getSymbol() : state;
	}
	
	
	// Getters e Setters
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isWhite() {
		return white;
	}

	public void setWhite(boolean white) {
		this.white = white;
	}
}
