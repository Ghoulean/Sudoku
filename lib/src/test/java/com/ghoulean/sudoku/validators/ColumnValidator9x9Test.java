package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.pojo.boards.Board9x9;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.utils.BoardTestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ColumnValidator9x9Test {

    ColumnValidator9x9 columnValidator9x9;

    @BeforeEach                                         
    void setUp() {
        columnValidator9x9 = new ColumnValidator9x9(TokenSetType.DEFAULT9X9);
    }

    @Test
    void shouldReturnName() {
        Assertions.assertEquals(columnValidator9x9.getName(), "ColumnValidator9x9", "Should return class name");
    }

    @Test
    void shouldValidateSolvedBoard() {
        Assertions.assertTrue(columnValidator9x9.validate(BoardTestUtils.getSolvedBoard9x9()), "Should validate solved board");
    }

    @Test
    void shouldValidateUnsolvedBoard() {
        Assertions.assertTrue(columnValidator9x9.validate(BoardTestUtils.getUnsolvedBoard9x9()), "Should validate unsolved board");
    }

    @Test
    void shouldValidateUnsolvableValidBoard() {
        Assertions.assertTrue(columnValidator9x9.validate(BoardTestUtils.getUnsolvableValidBoard9x9()), "Should validate unsolvable but valid board");
    }

    @Test
    void shouldInvalidateInvalidBoard() {
        Assertions.assertFalse(columnValidator9x9.validate(getInvalidBoard()), "Should invalidate invalid board with two sevens in 4th column");
    }

    private Board9x9 getInvalidBoard() {
        final Board9x9 board = new Board9x9();
        board.set(3, 2, Token.SEVEN);
        board.set(3, 6, Token.SEVEN);
        return board;
    }

}
