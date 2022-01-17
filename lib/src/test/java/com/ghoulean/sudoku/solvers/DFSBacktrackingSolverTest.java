package com.ghoulean.sudoku.solvers;

import java.util.Set;

import com.ghoulean.sudoku.Puzzle;
import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.utils.BoardTestUtils;
import com.ghoulean.sudoku.utils.ValidatorTestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class DFSBacktrackingSolverTest {

    private DFSBacktrackingSolver dfsBacktrackingSolver;

    @BeforeEach
    void setUp() {
        dfsBacktrackingSolver = new DFSBacktrackingSolver();
    }

    @Test
    void shouldSolveBoard() {
        Puzzle puzzle = new Puzzle(BoardTestUtils.getUnsolvedBoard9x9(),
            TokenSetType.DEFAULT9X9,
            ValidatorTestUtils.getDefault9x9Validators(TokenSetType.DEFAULT9X9));
        Set<Board> solutions = dfsBacktrackingSolver.solve(puzzle);
        Assertions.assertEquals(solutions.size(), 1);
        Board solution = solutions.iterator().next();
        Assertions.assertTrue(solution.isFilled());
        Assertions.assertEquals(solution, BoardTestUtils.getSolvedBoard9x9());
    }

}
