package com.ghoulean.sudoku.validators;

import com.ghoulean.sudoku.boards.Board9x9;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.utils.BoardTestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class TokenSetValidatorTest {

    private TokenSetValidator tokenSetValidator;

    @BeforeEach
    void setUp() {
        tokenSetValidator = new TokenSetValidator(TokenSetType.DEFAULT9X9);
    }

    @Test
    void shouldReturnName() {
        Assertions.assertEquals(tokenSetValidator.getName(), "TokenSetValidator", "Should return class name");
    }

    @Test
    void shouldValidateSolvedBoard() {
        Assertions.assertTrue(tokenSetValidator.validate(BoardTestUtils.getSolvedBoard9x9()),
            "Should validate solved board");
    }

    @Test
    void shouldValidateUnsolvedBoard() {
        Assertions.assertTrue(tokenSetValidator.validate(BoardTestUtils.getUnsolvedBoard9x9()),
            "Should validate unsolved board");
    }

    @Test
    void shouldInvalidateBoardWithBadTokens() {
        Assertions.assertFalse(tokenSetValidator.validate(getInvalidBoard()),
            "Should invalidate invalid board with tokens not in the default 9x9 tokenset");
    }

    private Board9x9 getInvalidBoard() {
        final Board9x9 board = new Board9x9();
        board.set(6, 2, Token.SEVEN);
        board.set(3, 6, Token.A);
        return board;
    }

}
