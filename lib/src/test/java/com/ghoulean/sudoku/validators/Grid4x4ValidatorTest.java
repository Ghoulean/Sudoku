package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.boards.Board16x16;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.utils.BoardTestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class Grid4x4ValidatorTest {

    private Grid4x4Validator grid4x4Validator;

    @BeforeEach
    void setUp() {
        grid4x4Validator = new Grid4x4Validator(TokenSetType.DEFAULT16X16);
    }

    @Test
    void shouldReturnName() {
        Assertions.assertEquals(grid4x4Validator.getName(), "Grid4x4Validator", "Should return class name");
    }

    @Test
    void shouldValidateSolvedBoard() {
        Assertions.assertTrue(grid4x4Validator.validate(BoardTestUtils.getSolvedBoard16x16()),
            "Should validate solved board");
    }

    @Test
    void shouldValidateUnsolvedBoard() {
        Assertions.assertTrue(grid4x4Validator.validate(BoardTestUtils.getUnsolvedBoard16x16()),
            "Should validate unsolved board");
    }

    @Test
    void shouldInvalidateInvalidBoards() {
        for (int i = 0; i <= 3; i += 1) {
            for (int j = 0; j <= 3; j += 1) {
                Assertions.assertFalse(grid4x4Validator.validate(buildInvalidBoard(i, j)),
                    "Should invalidate invalid board with two sevens in subgrid: " + i + ", " + j);
            }
        }
    }

    private Board16x16 buildInvalidBoard(final int xGrid, final int yGrid) {
        final int xOffset1 = 0;
        final int yOffset1 = 2;
        final int xOffset2 = 3;
        final int yOffset2 = 1;

        final Board16x16 board = new Board16x16();
        board.set(4 * xGrid + xOffset1, 4 * yGrid + yOffset1, Token.SEVEN);
        board.set(4 * xGrid + xOffset2, 4 * yGrid + yOffset2, Token.SEVEN);

        return board;
    }
}
