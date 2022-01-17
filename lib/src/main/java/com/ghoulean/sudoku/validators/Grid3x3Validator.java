package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.tokens.TokenSetType;

import lombok.Getter;
import lombok.NonNull;

import java.util.LinkedList;
import java.util.Set;

public final class Grid3x3Validator extends AbstractValidator {

    private static final int SUBGRID_WIDTH = 3;
    private static final int SUBGRID_HEIGHT = 3;
    private static final int RIGHT_SUBGRID_OFFSET = 2;
    private static final int BOT_SUBGRID_OFFSET = 2;

    @NonNull @Getter private final TokenSetType tokenSetType;
    @NonNull @Getter private final Set<Token> tokenSet;

    /**
     * Enforces that every token is unique per 3x3 grid, where each grid is aligned on 3 tiles. On a standard 9x9 board,
     * this is the typical grid seen. On non-9x9 boards, if there is any remainder on the right and bottom edges, they
     * are ignored.
     * @param tokenSetType tokenset to use
     */
    public Grid3x3Validator(final TokenSetType tokenSetType) {
        this.tokenSetType = tokenSetType;
        this.tokenSet = TokenSet.getTokenSet(tokenSetType);
    }

    @Override
    public boolean validate(final Board board) {
        for (int i = RIGHT_SUBGRID_OFFSET; i < board.getWidth(); i += SUBGRID_WIDTH) {
            for (int j = BOT_SUBGRID_OFFSET; j < board.getHeight(); j += SUBGRID_HEIGHT) {
                if (!validateGrid(board, i - RIGHT_SUBGRID_OFFSET, i,
                                         j - BOT_SUBGRID_OFFSET, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateGrid(final Board board, final int leftX, final int rightX, final int topY, final int botY) {
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

    private LinkedList<Token> gather(final Board board, final int leftX, final int rightX, final int topY,
                                     final int botY) {
        final LinkedList<Token> gatheredTokens = new LinkedList<>();
        for (int i = leftX; i <= rightX; i += 1) {
            for (int j = topY; j <= botY; j += 1) {
                gatheredTokens.add(board.get(i, j));
            }
        }
        return gatheredTokens;
    }
}
