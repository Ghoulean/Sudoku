package com.ghoulean.sudoku.boards;

import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.utils.BoardTestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    void shouldThrowExceptionOnModifyingNonmutableBoard() {
        final Board board = BoardTestUtils.getUnsolvedBoard9x9();
        board.lock();
        Assertions.assertThrows(IllegalStateException.class, () -> board.set(0, 0, Token.BLANK));
    }

    @Test
    void shouldEqualWhenCloning() {
        final Board board = BoardTestUtils.getUnsolvedBoard9x9();
        final Board boardClone = new Board(board);
        Assertions.assertEquals(board, boardClone);
        Assertions.assertEquals(board.hashCode(), boardClone.hashCode());
    }

    @Test
    void shouldEqualDespiteMutability() {
        final Board board = BoardTestUtils.getUnsolvedBoard9x9();
        final Board boardClone = new Board(board);
        boardClone.lock();
        Assertions.assertTrue(board.isMutable());
        Assertions.assertFalse(boardClone.isMutable());
        Assertions.assertEquals(board, boardClone);
        Assertions.assertEquals(board.hashCode(), boardClone.hashCode());
    }

    @Test
    void shouldEqualItself() {
        final Board board = BoardTestUtils.getUnsolvedBoard9x9();
        Assertions.assertEquals(board, board);
    }

    @Test
    void shouldBeMutableOnInstantiation() {
        final Board board = BoardTestUtils.getUnsolvedBoard9x9();
        final Board boardClone = new Board(board);
        Assertions.assertTrue(boardClone.isMutable());
    }

    @Test
    void shouldNotBeEqualWhenBoardsAreDifferent() {
        Assertions.assertNotEquals(BoardTestUtils.getUnsolvedBoard9x9(), BoardTestUtils.getSolvedBoard9x9());
        Assertions.assertNotEquals(new Board9x9(), new Board16x16());
        Assertions.assertNotEquals(new Board(9, 9), new Board(9, 12));
        Assertions.assertNotEquals(new Board(9, 9), null);
        Assertions.assertNotEquals(new Board(9, 9), TokenSet.getBlankTokenSet());
    }

    @Test
    void shouldBeUnfilledWhenBlankExists() {
        Board board = BoardTestUtils.getSolvedBoard9x9();
        Assertions.assertTrue(board.isFilled());
        board.set(0, 0, Token.BLANK);
        Assertions.assertFalse(board.isFilled());
        board.set(0, 0, Token.INVALID);
        Assertions.assertFalse(board.isFilled());
    }
}
