package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.pojo.boards.Board9x9;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;

import java.util.LinkedList;

public class ColumnValidator9x9 extends BaseValidator<Board9x9> {
    
    public ColumnValidator9x9(TokenSetType tokenSetType) {
        super(tokenSetType);
    }

    public boolean validate(Board9x9 board) {
        return validateColumn(board, 0)
            && validateColumn(board, 1)
            && validateColumn(board, 2)
            && validateColumn(board, 3)
            && validateColumn(board, 4)
            && validateColumn(board, 5)
            && validateColumn(board, 6)
            && validateColumn(board, 7)
            && validateColumn(board, 8);
    }

    private boolean validateColumn(Board9x9 board, int column) {
        LinkedList<Token> gathered = gather(board, column);
        for (int i = 0; i < board.getHeight(); i += 1) {
            Token t = board.get(column, i);
            if (t.equals(Token.BLANK) || t.equals(Token.INVALID)) {
                continue;
            }
            if (gathered.indexOf(t) != gathered.lastIndexOf(t)) {
                return false;
            }
        }
        return true;
    }

    private LinkedList<Token> gather(Board9x9 board, int column) {
        final LinkedList<Token> gatheredTokens = new LinkedList<>();
        for (int i = 0; i < board.getHeight(); i += 1) {
            gatheredTokens.add(board.get(column, i));
        }
        return gatheredTokens;
    }
}
