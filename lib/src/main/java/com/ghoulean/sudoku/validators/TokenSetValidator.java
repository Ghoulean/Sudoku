package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;

public final class TokenSetValidator extends AbstractValidator {

    /**
     * Enforces that the board only contains tokens from the specified tokenset
     * @param tokenSetType tokenset to use
     */
    public TokenSetValidator(final TokenSetType tokenSetType) {
        super(tokenSetType);
    }

    @Override
    public boolean validate(final Board board) {
        for (int i = 0; i < board.getWidth(); i += 1) {
            for (int j = 0; j < board.getHeight(); j += 1) {
                final Token t = board.get(i, j);
                if (t.equals(Token.BLANK) || t.equals(Token.INVALID)) {
                    continue;
                }
                if (!getTokenSet().contains(t)) {
                    return false;
                }
            }
        }
        return true;
    }
}
