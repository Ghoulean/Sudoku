package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.pojo.boards.Board9x9;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.utils.BoardTestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GridValidator9x9Test {

    GridValidator9x9 gridValidator9x9;

    @BeforeEach                                         
    void setUp() {
        gridValidator9x9 = new GridValidator9x9(TokenSetType.DEFAULT9X9);
    }

    @Test
    void shouldReturnName() {
        Assertions.assertEquals(gridValidator9x9.getName(), "GridValidator9x9", "Should return class name");
    }

    @Test
    void shouldValidateSolvedBoard() {
        Assertions.assertTrue(gridValidator9x9.validate(BoardTestUtils.getSolvedBoard9x9()), "Should validate solved board");
    }

    @Test
    void shouldValidateUnsolvedBoard() {
        Assertions.assertTrue(gridValidator9x9.validate(BoardTestUtils.getUnsolvedBoard9x9()), "Should validate unsolved board");
    }

    @Test
    void shouldValidateUnsolvableValidBoard() {
        Assertions.assertTrue(gridValidator9x9.validate(BoardTestUtils.getUnsolvableValidBoard9x9()), "Should validate unsolvable but valid board");
    }

    @Test
    void shouldInvalidateInvalidBoards() {
        for (int i = 0; i <= 2; i += 1) {
            for (int j = 0; j <= 2; j += 1) {
                Assertions.assertFalse(gridValidator9x9.validate(buildInvalidBoard(i, j)), "Should invalidate invalid board with two sevens in subgrid: " + i + ", " + j);
            }
        }
    }

    private Board9x9 buildInvalidBoard(int xGrid, int yGrid) {
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
