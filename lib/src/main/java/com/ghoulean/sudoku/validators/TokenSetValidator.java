package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.tokens.TokenSetType;

import lombok.Getter;
import lombok.NonNull;

import java.util.Set;

public final class TokenSetValidator extends AbstractValidator {

    @NonNull @Getter private final TokenSetType tokenSetType;
    @NonNull @Getter private final Set<Token> tokenSet;
    /**
     * Enforces that the board only contains tokens from the specified tokenset
     * @param tokenSetType tokenset to use
     */
    public TokenSetValidator(final TokenSetType tokenSetType) {
        this.tokenSetType = tokenSetType;
        this.tokenSet = TokenSet.getTokenSet(tokenSetType);
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
