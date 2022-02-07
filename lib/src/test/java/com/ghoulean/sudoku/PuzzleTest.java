package com.ghoulean.sudoku;

import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.utils.BoardTestUtils;
import com.ghoulean.sudoku.utils.ValidatorTestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PuzzleTest {

    @Test
    void shouldValidateStartingPositions() {
        Puzzle puzzle = new Puzzle(BoardTestUtils.getUnsolvedBoard9x9(),
                                   TokenSetType.DEFAULT9X9,
                                   ValidatorTestUtils.getDefault9x9Validators(TokenSetType.DEFAULT9X9));
        Assertions.assertFalse(puzzle.isStarting(4, 4));
        Assertions.assertFalse(puzzle.isStarting(7, 0));
        Assertions.assertTrue(puzzle.isStarting(0, 7));
    }

    @Test
    void shouldValidateSolvedPuzzle() {
        Puzzle puzzle = new Puzzle(BoardTestUtils.getBlankBoard9x9(),
                                   TokenSetType.DEFAULT9X9,
                                   ValidatorTestUtils.getDefault9x9Validators(TokenSetType.DEFAULT9X9));
        Assertions.assertTrue(puzzle.isSolved(BoardTestUtils.getSolvedBoard9x9()));
    }

    @Test
    void shouldInvalidateUnsolvedPuzzle() {
        Puzzle puzzle = new Puzzle(BoardTestUtils.getBlankBoard9x9(),
                                   TokenSetType.DEFAULT9X9,
                                   ValidatorTestUtils.getDefault9x9Validators(TokenSetType.DEFAULT9X9));
        Assertions.assertFalse(puzzle.isSolved(BoardTestUtils.getUnsolvedBoard9x9()));
    }
}
