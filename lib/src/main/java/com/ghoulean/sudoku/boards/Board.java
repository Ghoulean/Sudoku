package com.ghoulean.sudoku.boards;

import com.ghoulean.sudoku.tokens.Token;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable {

    @Getter private final int width;
    @Getter private final int height;
    @Getter private transient boolean mutable;
    private final Token[][] board;

    /**
     * Encodes the state of a rectangular Sudoku board
     * @param width width of board
     * @param height height of board
     */
    public Board(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.mutable = true;
        this.board = new Token[this.width][this.height];
        for (int i = 0; i < this.width; i += 1) {
            for (int j = 0; j < this.height; j += 1) {
                set(i, j, Token.BLANK);
            }
        }
    }

    /**
     * Clones another Sudoku board. Mutability is NOT copied
     * @param board board to clone
     */
    public Board(final Board board) {
        this.width = board.getWidth();
        this.height = board.getHeight();
        this.mutable = true;
        this.board = new Token[this.width][this.height];
        for (int i = 0; i < this.width; i += 1) {
            for (int j = 0; j < this.height; j += 1) {
                set(i, j, board.get(i, j));
            }
        }
    }

    /**
     * Prevent the board from receiving any further modifications
     */
    public final void lock() {
        this.mutable = false;
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
     * Replaces the token at the specified location with the specified token, with (0, 0) being top left. Fails if
     * board is nonmutable
     * @param x x coordinate
     * @param y y coordinate
     * @param val Token to be stored at the specified location
     */
    public final void set(final int x, final int y, final Token val) {
        if (!isMutable()) {
            throw new IllegalStateException("Board is nonmutable");
        }
        this.board[x][y] = val;
    }

    /**
     * Checks whether or not every location on the board is filled
     * @return true if every location on the board is filled and false otherwise
     */
    public final boolean isFilled() {
        for (int i = 0; i < this.getWidth(); i += 1) {
            for (int j = 0; j < this.getHeight(); j += 1) {
                if (this.get(i, j).equals(Token.BLANK) || this.get(i, j).equals(Token.INVALID)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public final String toString() {
        String retval = "";
        for (int j = 0; j < this.getHeight(); j += 1) {
            for (int i = 0; i < this.getWidth(); i += 1) {
                retval += this.get(i, j).toString() + " ";
            }
            retval += "\n";
        }
        return retval;
    }

    @Override
    public final boolean equals(final Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Board)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        final Board otherBoard = (Board) other;
        if (this.getWidth() != otherBoard.getWidth()) {
            return false;
        }
        if (this.getHeight() != otherBoard.getHeight()) {
            return false;
        }
        for (int i = 0; i < this.getWidth(); i += 1) {
            for (int j = 0; j < this.getHeight(); j += 1) {
                if (!this.get(i, j).equals(otherBoard.get(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public final int hashCode() {
        return Arrays.deepHashCode(this.board);
    }
}
