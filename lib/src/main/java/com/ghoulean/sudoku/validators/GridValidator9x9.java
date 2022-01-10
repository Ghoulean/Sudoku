package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.pojo.boards.Board9x9;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;

import java.util.LinkedList;

public class GridValidator9x9 extends BaseValidator<Board9x9> {
    
    public GridValidator9x9(TokenSetType tokenSetType) {
        super(tokenSetType);
    }

    public boolean validate(Board9x9 board) {
        return validateGrid(board, 0, 2, 0, 2)
            && validateGrid(board, 0, 2, 3, 5)
            && validateGrid(board, 0, 2, 6, 8)
            && validateGrid(board, 3, 5, 0, 2)
            && validateGrid(board, 3, 5, 3, 5)
            && validateGrid(board, 3, 5, 6, 8)
            && validateGrid(board, 6, 8, 0, 2)
            && validateGrid(board, 6, 8, 3, 5)
            && validateGrid(board, 6, 8, 6, 8);
    }

    private boolean validateGrid(Board9x9 board, int leftX, int rightX, int topY, int botY) {
        final LinkedList<Token> gathered = gather(board, leftX, rightX, topY, botY);
        for (int i = leftX; i <= rightX; i += 1) {
            for (int j = topY; j <= botY; j += 1) {
                Token t = board.get(i, j);
                if (t.equals(Token.BLANK) || t.equals(Token.INVALID)) {
                    continue;
                }
                if (gathered.indexOf(t) != gathered.lastIndexOf(t)) {
                    return false;
                }
            }
        }
        return true;
    }

    private LinkedList<Token> gather(Board9x9 board, int leftX, int rightX, int topY, int botY) {
        final LinkedList<Token> gatheredTokens = new LinkedList<>();
        for (int i = leftX; i <= rightX; i += 1) {
            for (int j = topY; j <= botY; j += 1) {
                gatheredTokens.add(board.get(i, j));
            }
        }
        return gatheredTokens;
    }
}
