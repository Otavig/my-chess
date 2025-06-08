package chess;

import java.util.List;
import java.util.Scanner;

import utils.Board;
import utils.Position;
import piece.Piece;

public class Main {
    public static void main(String[] args) {
    	Scanner initialGame = new Scanner(System.in);
        Board chess = new Board();

        chess.viewBoard();
  
        while(true) {
        	System.out.println("Digite a linha: ");
        	int row = initialGame.nextInt();
        	System.out.println("Digite a coluna: ");
            int col = initialGame.nextInt();

            Piece piece = chess.getPiece(row, col);

            if (piece != null) {
                System.out.println("\nPeça na posição (" + row + ", " + col + "): " + piece.getSymbol());

                List<Position> moves = chess.getValidMoves(row, col);
                System.out.println("Movimentos válidos:");

                for (Position pos : moves) {
                    System.out.println("-> (" + pos.getRow() + ", " + pos.getCol() + ")");
                }
            } else {
                System.out.println("Nenhuma peça na posição (" + row + ", " + col + ")");
            }
        }
    }
}
