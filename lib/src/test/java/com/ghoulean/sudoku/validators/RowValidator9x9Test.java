package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.pojo.boards.Board9x9;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.utils.BoardTestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RowValidator9x9Test {

    RowValidator9x9 rowValidator9x9;

    @BeforeEach                                         
    void setUp() {
        rowValidator9x9 = new RowValidator9x9(TokenSetType.DEFAULT9X9);
    }

    @Test
    void shouldReturnName() {
        Assertions.assertEquals(rowValidator9x9.getName(), "RowValidator9x9", "Should return class name");
    }

    @Test
    void shouldValidateSolvedBoard() {
        Assertions.assertTrue(rowValidator9x9.validate(BoardTestUtils.getSolvedBoard9x9()), "Should validate solved board");
    }

    @Test
    void shouldValidateUnsolvedBoard() {
        Assertions.assertTrue(rowValidator9x9.validate(BoardTestUtils.getUnsolvedBoard9x9()), "Should validate unsolved board");
    }

    @Test
    void shouldValidateUnsolvableValidBoard() {
        Assertions.assertTrue(rowValidator9x9.validate(BoardTestUtils.getUnsolvableValidBoard9x9()), "Should validate unsolvable but valid board");
    }

    @Test
    void shouldInvalidateInvalidBoard() {
        Assertions.assertFalse(rowValidator9x9.validate(getInvalidBoard()), "Should invalidate invalid board with two sevens in 6th row");
    }

    private Board9x9 getInvalidBoard() {
        final Board9x9 board = new Board9x9();
        board.set(3, 5, Token.SEVEN);
        board.set(8, 5, Token.SEVEN);
        return board;
    }

}
