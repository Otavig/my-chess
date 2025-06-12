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
        String round = "white";
  
        while(true) {
        	// Renderizar o tabuleiro
        	chess.viewBoard();
        	
        	System.out.printf("Vez do %s %n", round.equals("white") ? "white" : "black");
        	
        	System.out.println("Digite a linha: ");
        	int row = initialGame.nextInt();
        	System.out.println("Digite a coluna: ");
            int col = initialGame.nextInt();

            Piece piece = chess.getPiece(row, col);

            if (piece == null) {
                System.out.println("Nenhuma peça na posição (" + row + ", " + col + ")");
                continue;
            }

            if (!piece.getColor().equals(round)) {
                System.out.println("Cor inválida! É a vez da cor: " + round);
                continue;
            }

            System.out.println("\nPeça na posição (" + row + ", " + col + "): " + piece.getSymbol());
            List<Position> moves = chess.getValidMoves(row, col);

            if (moves.size() == 0) {
                System.out.println("Não há movimentos válidos para essa peça!");
                continue;
            }

            System.out.println("Movimentos válidos:");
            for (Position pos : moves) {
                System.out.println("-> (" + pos.getRow() + ", " + pos.getCol() + ")");
            }
            
            boolean repeat = false;
            do {
            	System.out.println("Qual posição você quer mover..");
                System.out.print("Digite a linha: ");
                int newRow = initialGame.nextInt();
                System.out.print("Digite a coluna: ");
                int newCol = initialGame.nextInt();
                
//                if(newRow == 190|| newCol == 190) { System.out.printf("B: %s  -  W: %s",  chess.getPBlack(), chess.getPWhite());} 

                // Verifica se a nova posição é válida
                boolean moveValid = false;
                for(int i = 0; i < moves.size() && !moveValid; i++) {
                	if (moves.get(i).getRow() == newRow && moves.get(i).getCol() == newCol) 
                        moveValid = true;
                }

                if (moveValid) {
                	round = round.equals("white") ? "black" : "white"; 
                    repeat = chess.movePiece(row, col, newRow, newCol, round);
                } else {
                    System.out.println("Movimento inválido!");
                }
            } while(!repeat);
            
        }
    }
}
