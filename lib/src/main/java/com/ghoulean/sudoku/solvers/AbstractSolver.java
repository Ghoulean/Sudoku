package com.ghoulean.sudoku.solvers;

import java.util.Set;

import com.ghoulean.sudoku.Puzzle;
import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.validators.AbstractValidator;

public abstract class AbstractSolver<T extends Board> {
    /**
     * Attempt to find solution(s) to a puzzle. Whether or not all possible solutions are found is left to the
     * implementor.
     * @param puzzle puzzle to solve
     * @return solutions found using method by implementor
     */
    public abstract Set<T> solve(Puzzle puzzle);

    /**
     * Helper method that checks whether a board is in an invalid state
     * @param board board
     * @param validators set of validators to check against
     * @return false if any validator fails validation and true otherwise
     */
    protected boolean isInViolation(final Board board,
                                    final Set<AbstractValidator> validators) {
        for (AbstractValidator validator: validators) {
            if (!validator.validate(board)) {
                return false;
            }
        }
        return true;
    }
}
