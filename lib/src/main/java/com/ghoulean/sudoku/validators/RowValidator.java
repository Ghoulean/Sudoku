package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;

import java.util.LinkedList;

public final class RowValidator extends AbstractValidator {

    /**
     * Enforces that every token is unique per row. In other words, every token appears at most once in every row of the
     * board
     * @param tokenSetType tokenset to use
     */
    public RowValidator(final TokenSetType tokenSetType) {
        super(tokenSetType);
    }

    @Override
    public boolean validate(final Board board) {
        for (int i = 0; i < board.getHeight(); i += 1) {
            if (!validateRow(board, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateRow(final Board board, final int row) {
        final LinkedList<Token> gathered = gather(board, row);
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

    private LinkedList<Token> gather(final Board board, final int row) {
        final LinkedList<Token> gatheredTokens = new LinkedList<>();
        for (int i = 0; i < board.getWidth(); i += 1) {
            gatheredTokens.add(board.get(i, row));
        }
        return gatheredTokens;
    }
}
