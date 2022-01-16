package com.ghoulean.sudoku.boards;

import com.ghoulean.sudoku.tokens.Token;

import lombok.Getter;

import java.io.Serializable;

public class Board implements Serializable {

    @Getter private final int width;
    @Getter private final int height;
    private final Token[][] board;

    /**
     * Encodes the state of a rectangular Sudoku board
     * @param width width of board
     * @param height height of board
     */
    public Board(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.board = new Token[this.width][this.height];
        for (int i = 0; i < this.width; i += 1) {
            for (int j = 0; j < this.height; j += 1) {
                set(i, j, Token.BLANK);
            }
        }
    }

    /**
     * Clones another Sudoku board
     * @param board board to clone
     */
    public Board(final Board board) {
        this.width = board.getWidth();
        this.height = board.getHeight();
        this.board = new Token[this.width][this.height];
        for (int i = 0; i < this.width; i += 1) {
            for (int j = 0; j < this.height; j += 1) {
                set(i, j, board.get(i, j));
            }
        }
    }

    /**
     * Returns the token at the specified location, with (0, 0) being top left
     * @param x x coordinate
     * @param y y coordinate
     * @return the token at the specified location
     */
    public final Token get(final int x, final int y) {
        return board[x][y];
    }

    /**
     * Replaces the token at the specified location with the specified token, with (0, 0) being top left
     * @param x x coordinate
     * @param y y coordinate
     * @param val Token to be stored at the specified location
     */
    public final void set(final int x, final int y, final Token val) {
        this.board[x][y] = val;
    }
}
