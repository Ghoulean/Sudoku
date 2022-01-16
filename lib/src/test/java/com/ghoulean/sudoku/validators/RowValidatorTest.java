package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.boards.Board9x9;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.utils.BoardTestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class RowValidatorTest {

    private RowValidator rowValidator;

    @BeforeEach
    void setUp() {
        rowValidator = new RowValidator(TokenSetType.DEFAULT9X9);
    }

    @Test
    void shouldReturnName() {
        Assertions.assertEquals(rowValidator.getName(), "RowValidator", "Should return class name");
    }

    @Test
    void shouldValidateSolvedBoard() {
        Assertions.assertTrue(rowValidator.validate(BoardTestUtils.getSolvedBoard9x9()),
            "Should validate solved board");
    }

    @Test
    void shouldValidateUnsolvedBoard() {
        Assertions.assertTrue(rowValidator.validate(BoardTestUtils.getUnsolvedBoard9x9()),
            "Should validate unsolved board");
    }

    @Test
    void shouldValidateUnsolvableValidBoard() {
        Assertions.assertTrue(rowValidator.validate(BoardTestUtils.getUnsolvableValidBoard9x9()),
            "Should validate unsolvable but valid board");
    }

    @Test
    void shouldInvalidateInvalidBoard() {
        Assertions.assertFalse(rowValidator.validate(getInvalidBoard()),
            "Should invalidate invalid board with two sevens in 6th row");
    }

    private Board9x9 getInvalidBoard() {
        final Board9x9 board = new Board9x9();
        board.set(3, 5, Token.SEVEN);
        board.set(8, 5, Token.SEVEN);
        return board;
    }

}
