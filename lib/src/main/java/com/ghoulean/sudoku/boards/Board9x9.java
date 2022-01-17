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

    /** Hide unused constructor */
    private Board9x9(final int width, final int height) {
        super(WIDTH, HEIGHT);
    }
}
