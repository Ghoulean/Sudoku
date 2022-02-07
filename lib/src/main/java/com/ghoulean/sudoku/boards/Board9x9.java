package com.ghoulean.sudoku.boards;

public class Board9x9 extends Board {

    /** Width of a 9x9 board */
    public static final int WIDTH = 9;
    /** Height of a 9x9 board */
    public static final int HEIGHT = 9;

    /** Constructor for 9x9 board */
    public Board9x9() {
        super(WIDTH, HEIGHT);
    }

    /** Create copy of a 9x9 board */
    public Board9x9(final Board9x9 board) {
        super(board);
    }

    /** Create copy of a board that is 9x9*/
    public Board9x9(final Board board) {
        super(board);
        if (board.getWidth() != WIDTH || board.getHeight() != HEIGHT) {
            throw new IllegalArgumentException("Board is not 9x9");
        }
    }
}
