package com.ghoulean.sudoku;

import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.validators.AbstractValidator;

import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Set;

public class Puzzle implements Serializable {
    @NonNull @Getter private final Board startingBoard;
    @NonNull @Getter private final TokenSetType tokenSetType;
    @NonNull private final Set<Token> tokenSet;
    @NonNull @Getter private final Set<AbstractValidator> validators;

    /**
     * Encodes the gamestate of a Sudoku game. It holds an immutable copy of the starting position, the set of valid
     * tokens, and the set of validators that the solution will be tested against.
     *
     * Unlike "true" Sudoku puzzles, a Puzzle may have any number of solutions.
     *
     * @param startingBoard starting board
     * @param tokenSetType token set type corresponding to the set of valid tokens
     * @param validators set of validators
    */
    public Puzzle(final Board startingBoard,
                  final TokenSetType tokenSetType,
                  final Set<AbstractValidator> validators) {
        this.startingBoard = new Board(startingBoard);
        this.startingBoard.lock();
        this.tokenSetType = tokenSetType;
        this.tokenSet = TokenSet.getTokenSet(tokenSetType);
        this.validators = validators;
    }

    /**
     * Checks whether a location on the board is a part of the starting position; that is, the location is blank in the
     * starting position.
     * @param x x coordinate
     * @param y y coordinate
     * @return true if the token at that location is nonempty and false otherwise
     */
    public boolean isStarting(final int x, final int y) {
        return !this.startingBoard.get(x, y).equals(Token.BLANK);
    }

    /**
     * Checks whether a given board is a solution to the puzzle.
     * @param board Board to check
     * @return true if the board is a solution and false otherwise
     */
    public boolean isSolved(final Board board) {
        for (int i = 0; i < board.getWidth(); i += 1) {
            for (int j = 0; j < board.getHeight(); j += 1) {
                if (board.get(i, j).equals(Token.BLANK) || board.get(i, j).equals(Token.INVALID)) {
                    return false;
                }
            }
        }
        for (AbstractValidator validator: validators) {
            if (!validator.validate(board)) {
                return false;
            }
        }
        return true;
    }

}
