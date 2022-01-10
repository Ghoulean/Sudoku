package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.pojo.boards.Board9x9;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;

import java.util.LinkedList;

public class RowValidator9x9 extends BaseValidator<Board9x9> {
    
    public RowValidator9x9(TokenSetType tokenSetType) {
        super(tokenSetType);
    }

    public boolean validate(Board9x9 board) {
        return validateRow(board, 0)
            && validateRow(board, 1)
            && validateRow(board, 2)
            && validateRow(board, 3)
            && validateRow(board, 4)
            && validateRow(board, 5)
            && validateRow(board, 6)
            && validateRow(board, 7)
            && validateRow(board, 8);
    }

    private boolean validateRow(Board9x9 board, int row) {
        LinkedList<Token> gathered = gather(board, row);
        for (int i = 0; i < board.getWidth(); i += 1) {
            Token t = board.get(i, row);
            if (t.equals(Token.BLANK) || t.equals(Token.INVALID)) {
                continue;
            }
            if (gathered.indexOf(t) != gathered.lastIndexOf(t)) {
                return false;
            }
        }
        return true;
    }

    private LinkedList<Token> gather(Board9x9 board, int row) {
        final LinkedList<Token> gatheredTokens = new LinkedList<>();
        for (int i = 0; i < board.getWidth(); i += 1) {
            gatheredTokens.add(board.get(i, row));
        }
        return gatheredTokens;
    }
}
