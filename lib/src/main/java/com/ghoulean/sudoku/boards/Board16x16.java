package com.ghoulean.sudoku.boards;

public class Board16x16 extends Board {

    /** Width of a 16x16 board */
    public static final int WIDTH = 16;
    /** Height of a 16x16 board */
    public static final int HEIGHT = 16;

    /** Constructor for 16x16 board */
    public Board16x16() {
        super(WIDTH, HEIGHT);
    }

    /** Create copy of a 16x16 board */
    public Board16x16(final Board16x16 board) {
        super(board);
    }
}
