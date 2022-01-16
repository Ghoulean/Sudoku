package com.ghoulean.sudoku.validators;

import java.io.Serializable;
import java.util.Set;

import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.tokens.TokenSetType;

import lombok.Getter;
import lombok.NonNull;

public abstract class AbstractValidator implements Serializable {

    @NonNull @Getter private final TokenSetType tokenSetType;
    @NonNull @Getter private final Set<Token> tokenSet;

    protected AbstractValidator(final TokenSetType tokenSetType) {
        this.tokenSetType = tokenSetType;
        this.tokenSet = TokenSet.getTokenSet(tokenSetType);
    }

    /**
     * Check the board for rule violations, and returns false if at least one is found.
     * @param board board to search
     * @return false if at least one rule violation is found, and true otherwise
     */
    public abstract boolean validate(Board board);

    /**
     * Returns the validator's name
     * @return the validator's name
     */
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
