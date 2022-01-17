package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.tokens.TokenSetType;

import lombok.Getter;
import lombok.NonNull;

import java.util.LinkedList;
import java.util.Set;

public final class RowValidator extends AbstractValidator {

    @NonNull @Getter private final TokenSetType tokenSetType;
    @NonNull @Getter private final Set<Token> tokenSet;

    /**
     * Enforces that every token is unique per row. In other words, every token appears at most once in every row of the
     * board
     * @param tokenSetType tokenset to use
     */
    public RowValidator(final TokenSetType tokenSetType) {
        this.tokenSetType = tokenSetType;
        this.tokenSet = TokenSet.getTokenSet(tokenSetType);
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
