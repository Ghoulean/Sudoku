package com.ghoulean.sudoku;

import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.validators.AbstractValidator;

import lombok.Getter;
import lombok.NonNull;

import java.util.Set;

public class Puzzle {
    @NonNull @Getter private final Board startingBoard;
    @NonNull @Getter private final Board currentBoard;
    @NonNull @Getter private final TokenSetType tokenSetType;
    @NonNull private final Set<Token> tokenSet;
    @NonNull @Getter private final Set<AbstractValidator> validators;

    /**
     * Encodes the gamestate of a Sudoku game. It holds the starting position, the set of valid tokens, and the set of
     * validators that the solution will be tested against.
     * @param startingBoard starting board
     * @param tokenSetType token set type corresponding to the set of valid tokens
     * @param validators set of validators
    */
    public Puzzle(final Board startingBoard,
                  final TokenSetType tokenSetType,
                  final Set<AbstractValidator> validators) {
        this.startingBoard = startingBoard;
        this.currentBoard = new Board(startingBoard);
        this.tokenSetType = tokenSetType;
        this.tokenSet = TokenSet.getTokenSet(tokenSetType);
        this.validators = validators;
    }
}
