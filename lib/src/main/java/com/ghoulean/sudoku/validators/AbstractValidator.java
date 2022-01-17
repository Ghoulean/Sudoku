package com.ghoulean.sudoku.validators;

import java.io.Serializable;

import com.ghoulean.sudoku.boards.Board;
public abstract class AbstractValidator implements Serializable {
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
