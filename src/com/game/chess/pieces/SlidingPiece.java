package com.game.chess.pieces;


import com.game.chess.board.Board;
import com.game.chess.pieces.enums.Color;

public abstract class SlidingPiece extends Piece {

    SlidingPiece(Board board, Color color, Position position) {
        super(board, color, position);
    }

    protected boolean isValidLinearMove(Position destPosition) {
        return isMovingInLine(destPosition) && isPathClear(destPosition)
                && isDestinationAvailable(destPosition);
    }

    protected boolean isValidDiagonalMove(Position destPosition) {

        Board board = getBoard();
        if (board.pieceExistsAt(destPosition) && board.isPieceColor(destPosition, getColor())) {
            return false;
        }

        Position curPosition = getPosition();
        int dCol = Math.abs(destPosition.getCol() - curPosition.getCol());
        int dRow = Math.abs(destPosition.getRow() - curPosition.getRow());

        if (dCol != dRow) {
            return false;
        }

        return isPathClear(destPosition) && isDestinationAvailable(destPosition);
    }

    private boolean isPathClear(Position destPosition) {
        Position curPosition = getPosition();
        int stepCol = Integer.compare(destPosition.getCol(), curPosition.getCol());
        int stepRow = Integer.compare(destPosition.getRow(), curPosition.getRow());

        int col = curPosition.getCol() + stepCol;
        int row = curPosition.getRow() + stepRow;

        while (col != destPosition.getCol() || row != destPosition.getRow()) {
            if (getBoard().pieceExistsAt(new Position(row, col))) {
                return false;
            }
            col += stepCol;
            row += stepRow;
        }
        return true;
    }

    private boolean isMovingInLine(Position destPosition) {
        Position curPosition = getPosition();
        return destPosition.getCol() == curPosition.getCol() || destPosition.getRow() == curPosition.getRow();
    }
}