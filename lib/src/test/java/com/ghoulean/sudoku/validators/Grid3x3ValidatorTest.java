package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.boards.Board9x9;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.utils.BoardTestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class Grid3x3ValidatorTest {

    private Grid3x3Validator grid3x3Validator;

    @BeforeEach
    void setUp() {
        grid3x3Validator = new Grid3x3Validator(TokenSetType.DEFAULT9X9);
    }

    @Test
    void shouldReturnName() {
        Assertions.assertEquals(grid3x3Validator.getName(), "Grid3x3Validator", "Should return class name");
    }

    @Test
    void shouldValidateSolvedBoard() {
        Assertions.assertTrue(grid3x3Validator.validate(BoardTestUtils.getSolvedBoard9x9()),
            "Should validate solved board");
    }

    @Test
    void shouldValidateUnsolvedBoard() {
        Assertions.assertTrue(grid3x3Validator.validate(BoardTestUtils.getUnsolvedBoard9x9()),
            "Should validate unsolved board");
    }

    @Test
    void shouldValidateUnsolvableValidBoard() {
        Assertions.assertTrue(grid3x3Validator.validate(BoardTestUtils.getUnsolvableValidBoard9x9()),
            "Should validate unsolvable but valid board");
    }

    @Test
    void shouldInvalidateInvalidBoards() {
        for (int i = 0; i <= 2; i += 1) {
            for (int j = 0; j <= 2; j += 1) {
                Assertions.assertFalse(grid3x3Validator.validate(buildInvalidBoard(i, j)),
                    "Should invalidate invalid board with two sevens in subgrid: " + i + ", " + j);
            }
        }
    }

    private Board9x9 buildInvalidBoard(final int xGrid, final int yGrid) {
        final int xOffset1 = 1;
        final int yOffset1 = 0;
        final int xOffset2 = 0;
        final int yOffset2 = 2;

        final Board9x9 board = new Board9x9();
        board.set(3 * xGrid + xOffset1, 3 * yGrid + yOffset1, Token.SEVEN);
        board.set(3 * xGrid + xOffset2, 3 * yGrid + yOffset2, Token.SEVEN);

        return board;
    }
}
