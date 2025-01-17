package com.game.chess.ui;

import com.game.chess.board.Board;
import com.game.chess.pieces.Piece;
import com.game.chess.pieces.Position;
import com.game.chess.pieces.enums.Color;

import static java.util.Objects.isNull;

public class VisualImpl implements Visual {

    private final Board board;

    public VisualImpl(Board board) {
        this.board = board;
    }

    public void print() {
        System.out.println("   a  b  c  d  e  f  g  h");
        for (int row = 7; row >= 0; row--) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPieceByPosition(new Position(row, col));
                String backgroundColor = (row + col) % 2 == 0 ? "48;5;252" : "48;5;110";

                if (isNull(piece)) {
                    System.out.print("\u001B[" + backgroundColor + "m   \u001B[0m");
                } else {
                    String pieceColor = piece.getColor() == Color.WHITE ? "97" : "30";
                    String symbol = piece.getSymbol();
                    System.out.print("\u001B[" + backgroundColor + ";" + pieceColor + "m " + symbol + " \u001B[0m");
                }
            }
            System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");
    }
}
