package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;

import java.util.LinkedList;

public final class ColumnValidator extends AbstractValidator {

    /**
     * Enforces that every token is unique per column. In other words, every token appears at most once in every column
     * of the board
     * @param tokenSetType tokenset to use
     */
    public ColumnValidator(final TokenSetType tokenSetType) {
        super(tokenSetType);
    }

    @Override
    public boolean validate(final Board board) {
        for (int i = 0; i < board.getWidth(); i += 1) {
            if (!validateColumn(board, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateColumn(final Board board, final int column) {
        final LinkedList<Token> gathered = gather(board, column);
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

    private LinkedList<Token> gather(final Board board, final int column) {
        final LinkedList<Token> gatheredTokens = new LinkedList<>();
        for (int i = 0; i < board.getHeight(); i += 1) {
            gatheredTokens.add(board.get(column, i));
        }
        return gatheredTokens;
    }
}
