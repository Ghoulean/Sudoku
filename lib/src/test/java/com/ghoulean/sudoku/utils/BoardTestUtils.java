package com.ghoulean.sudoku.utils;

import com.ghoulean.sudoku.boards.Board9x9;
import com.ghoulean.sudoku.tokens.Token;

import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class BoardTestUtils {

    public static Board9x9 getBlankBoard9x9() {
        return new Board9x9();
    }

    public static Board9x9 getUnsolvedBoard9x9() {
        Board9x9 board = new Board9x9();
        board.set(1, 0, Token.TWO);
        board.set(5, 0, Token.FOUR);
        board.set(6, 0, Token.THREE);
        board.set(0, 1, Token.NINE);
        board.set(4, 1, Token.TWO);
        board.set(8, 1, Token.EIGHT);
        board.set(3, 2, Token.SIX);
        board.set(5, 2, Token.NINE);
        board.set(7, 2, Token.FIVE);
        board.set(8, 3, Token.ONE);
        board.set(1, 4, Token.SEVEN);
        board.set(2, 4, Token.TWO);
        board.set(3, 4, Token.FIVE);
        board.set(5, 4, Token.THREE);
        board.set(6, 4, Token.SIX);
        board.set(7, 4, Token.EIGHT);
        board.set(0, 5, Token.SIX);
        board.set(1, 6, Token.EIGHT);
        board.set(3, 6, Token.TWO);
        board.set(5, 6, Token.FIVE);
        board.set(0, 7, Token.ONE);
        board.set(4, 7, Token.NINE);
        board.set(8, 7, Token.THREE);
        board.set(2, 8, Token.NINE);
        board.set(3, 8, Token.EIGHT);
        board.set(7, 8, Token.SIX);
        return board;
    }

    public static Board9x9 getSolvedBoard9x9() {
        Board9x9 board = new Board9x9();
        board.set(0, 0, Token.EIGHT);
        board.set(1, 0, Token.TWO);
        board.set(2, 0, Token.SEVEN);
        board.set(3, 0, Token.ONE);
        board.set(4, 0, Token.FIVE);
        board.set(5, 0, Token.FOUR);
        board.set(6, 0, Token.THREE);
        board.set(7, 0, Token.NINE);
        board.set(8, 0, Token.SIX);
        board.set(0, 1, Token.NINE);
        board.set(1, 1, Token.SIX);
        board.set(2, 1, Token.FIVE);
        board.set(3, 1, Token.THREE);
        board.set(4, 1, Token.TWO);
        board.set(5, 1, Token.SEVEN);
        board.set(6, 1, Token.ONE);
        board.set(7, 1, Token.FOUR);
        board.set(8, 1, Token.EIGHT);
        board.set(0, 2, Token.THREE);
        board.set(1, 2, Token.FOUR);
        board.set(2, 2, Token.ONE);
        board.set(3, 2, Token.SIX);
        board.set(4, 2, Token.EIGHT);
        board.set(5, 2, Token.NINE);
        board.set(6, 2, Token.SEVEN);
        board.set(7, 2, Token.FIVE);
        board.set(8, 2, Token.TWO);
        board.set(0, 3, Token.FIVE);
        board.set(1, 3, Token.NINE);
        board.set(2, 3, Token.THREE);
        board.set(3, 3, Token.FOUR);
        board.set(4, 3, Token.SIX);
        board.set(5, 3, Token.EIGHT);
        board.set(6, 3, Token.TWO);
        board.set(7, 3, Token.SEVEN);
        board.set(8, 3, Token.ONE);
        board.set(0, 4, Token.FOUR);
        board.set(1, 4, Token.SEVEN);
        board.set(2, 4, Token.TWO);
        board.set(3, 4, Token.FIVE);
        board.set(4, 4, Token.ONE);
        board.set(5, 4, Token.THREE);
        board.set(6, 4, Token.SIX);
        board.set(7, 4, Token.EIGHT);
        board.set(8, 4, Token.NINE);
        board.set(0, 5, Token.SIX);
        board.set(1, 5, Token.ONE);
        board.set(2, 5, Token.EIGHT);
        board.set(3, 5, Token.NINE);
        board.set(4, 5, Token.SEVEN);
        board.set(5, 5, Token.TWO);
        board.set(6, 5, Token.FOUR);
        board.set(7, 5, Token.THREE);
        board.set(8, 5, Token.FIVE);
        board.set(0, 6, Token.SEVEN);
        board.set(1, 6, Token.EIGHT);
        board.set(2, 6, Token.SIX);
        board.set(3, 6, Token.TWO);
        board.set(4, 6, Token.THREE);
        board.set(5, 6, Token.FIVE);
        board.set(6, 6, Token.NINE);
        board.set(7, 6, Token.ONE);
        board.set(8, 6, Token.FOUR);
        board.set(0, 7, Token.ONE);
        board.set(1, 7, Token.FIVE);
        board.set(2, 7, Token.FOUR);
        board.set(3, 7, Token.SEVEN);
        board.set(4, 7, Token.NINE);
        board.set(5, 7, Token.SIX);
        board.set(6, 7, Token.EIGHT);
        board.set(7, 7, Token.TWO);
        board.set(8, 7, Token.THREE);
        board.set(0, 8, Token.TWO);
        board.set(1, 8, Token.THREE);
        board.set(2, 8, Token.NINE);
        board.set(3, 8, Token.EIGHT);
        board.set(4, 8, Token.FOUR);
        board.set(5, 8, Token.ONE);
        board.set(6, 8, Token.FIVE);
        board.set(7, 8, Token.SIX);
        board.set(8, 8, Token.SEVEN);
        return board;
    }

    // Unsolvable by standard Sudoku rules, but technically still valid
    public static Board9x9 getUnsolvableValidBoard9x9() {
        Board9x9 board = new Board9x9();
        board.set(7, 0, Token.FIVE);
        board.set(8, 1, Token.FOUR);
        board.set(8, 3, Token.FIVE);
        board.set(7, 4, Token.FOUR);
        board.set(2, 7, Token.FIVE);
        board.set(2, 8, Token.FOUR);
        board.set(4, 8, Token.FIVE);
        board.set(5, 7, Token.FOUR);
        return board;
    }

}
