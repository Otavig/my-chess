package utils;

import java.util.List;
import java.util.ArrayList;

import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;

public class Board {
	public Square[][] squares;
	private int pWhite = 0;
	private int pBlack = 0;
	
	public Board() {
		squares = new Square[8][8];
		createBoard();
		piecePosition();
	}
	
	public boolean isInsideTheBoard(int row, int col) {
		return row >= 0 && row < 8 && col >= 0 && col < 8;
	}
	
	public void createBoard() {
		int cols = 8;
		int rows = 8;
		
		for(int i = 0; i < cols * rows; i++) {
			int col = i % cols;
			int row = i / cols;
			
			this.squares[row][col] = new Square(row, col);
		}
	}
	
	public void viewBoard() {
		for(int i = 0; i < squares.length; i++) {
			for(int j = 0; j < squares[0].length; j++) 
				System.out.print(squares[i][j].view() + " ");				
			
			System.out.println();				
		}	
	}
	
	public void piecePosition() {
		// Preto
		squares[7][0].setPiece(new Rook("black"));
		squares[7][7].setPiece(new Rook("black"));
		
		squares[7][1].setPiece(new Knight("black"));
		squares[7][6].setPiece(new Knight("black"));
		
		squares[7][2].setPiece(new Bishop("black"));
		squares[7][5].setPiece(new Bishop("black"));
		
		squares[7][3].setPiece(new King("black"));
		squares[7][4].setPiece(new Queen("black"));
		
		for(int i = 0; i < 8; i++) 
			squares[6][i].setPiece(new Pawn("black"));
		
		// Branco
		squares[0][0].setPiece(new Rook("white"));
		squares[0][7].setPiece(new Rook("white"));
		
		squares[0][1].setPiece(new Knight("white"));
		squares[0][6].setPiece(new Knight("white"));
		
		squares[0][2].setPiece(new Bishop("white"));
		squares[0][5].setPiece(new Bishop("white"));
		
		squares[0][3].setPiece(new King("white"));
		squares[0][4].setPiece(new Queen("white"));
		
		for(int i = 0; i < 8; i++) 
			squares[1][i].setPiece(new Pawn("white"));
	}
	
	public boolean addIfValid(List<Position> moves, int row, int col, String color) {
		if(!isInsideTheBoard(row,col)) return true;
		
		Square destiny = squares[row][col];
		Piece pieceInDestiny = destiny.getPiece();

		if(pieceInDestiny == null) {
			moves.add(new Position(row, col));
			return false;
		}
		
		if(!pieceInDestiny.getColor().equals(color)) {
			moves.add(new Position(row, col));
			return true;
		}
		
		return true;
	}
	
	public Piece getPiece(int row, int col) {
	    if (!isInsideTheBoard(row, col)) return null;
	    return squares[row][col].getPiece();
	}
	
	public List<Position> getValidMoves(int row, int col) {
	    Piece piece = getPiece(row, col);
	    if (piece == null) return new ArrayList<>();
	    return piece.getMovesValid(this, row, col, false);
	}
	
	public boolean movePiece(int row, int col, int newRow, int newCol, String color) {
	    if (!isInsideTheBoard(newRow, newCol)) return false;

	    Square from = squares[row][col];
	    Square to = squares[newRow][newCol];

	    Piece movingPiece = from.getPiece();

	    if (movingPiece == null) return false; 

	    // Detectar se foi roque
	    if (movingPiece instanceof King) {
	        King king = (King) movingPiece;
	        if (!king.getMoved() && Math.abs(newCol - col) == 2) {
	            // Roque detectado
	            if (newCol > col) {
	                // Roque pequeno (lado do rei)
	                Square rookFrom = squares[row][7];
	                Square rookTo = squares[row][col + 1];
	                rookTo.setPiece(rookFrom.getPiece());
	                rookFrom.setPiece(null);
	            } else {
	                // Roque grande (lado da dama)
	                Square rookFrom = squares[row][0];
	                Square rookTo = squares[row][col - 1];
	                rookTo.setPiece(rookFrom.getPiece());
	                rookFrom.setPiece(null);
	            }
	        }
	    }

	    movingPiece.markMoved();

	    Piece nextMoving = to.getPiece();

	    if (nextMoving != null)
	        setPiecesDie(color);

	    to.setPiece(movingPiece);
	    from.setPiece(null);

	    return true;
	}
	
	public int getPWhite() {
		return pWhite;
	}
	
	public int getPBlack() {
		return pBlack;
	}
	
	public void setPiecesDie(String color) {
		if(color == "white") pWhite += 1;
		else pBlack += 1;
	}

	public boolean isSquareUnderAttack(int row, int col, String color) {
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            Square square = squares[i][j];
	            if (square != null) {
	                Piece piece = square.getPiece();
	                if (piece != null && !piece.getColor().equals(color)) {
	                    List<Position> enemyMoves = piece.getMovesValid(this, i, j, true);
	                    for (Position pos : enemyMoves) {
	                        if (pos.getRow() == row && pos.getCol() == col) {
	                            return true;
	                        }
	                    }
	                }
	            }
	        }
	    }
	    return false;
	}
	
	public boolean isKingInCheck(String color) {
	    int kingRow = -1;
	    int kingCol = -1;

	    // Procurar o rei no tabuleiro
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            Square square = squares[i][j];
	            if (square != null) {
	                Piece piece = square.getPiece();
	                if (piece instanceof King && piece.getColor().equals(color)) {
	                    kingRow = i;
	                    kingCol = j;
	                    break;
	                }
	            }
	        }
	    }

	    // Se não encontrou o rei (algo muito errado)
	    if (kingRow == -1 || kingCol == -1) {
	        throw new IllegalStateException("Rei não encontrado no tabuleiro.");
	    }

	    // Verifica se a casa do rei está sob ataque
	    return isSquareUnderAttack(kingRow, kingCol, color);
	}

}
