package com.ghoulean.sudoku.validators;

import java.io.Serializable;
import java.util.Set;

import com.ghoulean.sudoku.pojo.boards.Board;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.tokens.TokenSetType;

import lombok.NonNull;

public abstract class BaseValidator<T extends Board> implements Serializable {
    
    @NonNull final protected TokenSetType tokenSetType;
    @NonNull final protected Set<Token> tokenSet;

    protected BaseValidator(TokenSetType tokenSetType) {
        this.tokenSetType = tokenSetType;
        this.tokenSet = TokenSet.getTokenSet(tokenSetType);
    }

    public abstract boolean validate(T board);

    public String getName() {
        return this.getClass().getSimpleName();
    }
}
