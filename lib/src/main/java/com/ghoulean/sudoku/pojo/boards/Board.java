package com.ghoulean.sudoku.pojo.boards;

import com.ghoulean.sudoku.tokens.Token;

import lombok.Getter;

import java.io.Serializable;

public class Board implements Serializable {

    @Getter private final int width;
    @Getter private final int height;
    @Getter private final Token[][] board;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Token[width][height];
        for (int i = 0; i < this.width; i += 1) {
            for (int j = 0; j < this.height; j += 1) {
                this.board[i][j] = Token.BLANK;
            }
        }
    }

    protected Board(Board board) {
        this.width = board.getWidth();    
        this.height = board.getHeight();
        this.board = new Token[this.width][];
        for(int i = 0; i < this.width; i++) {
          this.board[i] = new Token[height];
          System.arraycopy(board.getBoard()[i], 0, this.board[i], 0, height);
        }
    }

    public Token get(int x, int y) {
        if (x < 0 || x >= this.width) {
            return Token.INVALID;
        }
        if (y < 0 || y >= this.height) {
            return Token.INVALID;
        }
        return board[x][y];
    }

    public boolean set(int x, int y, Token val) {
        if (x < 0 || x >= this.width) {
            return false;
        }
        if (y < 0 || y >= this.height) {
            return false;
        }
        this.board[x][y] = val;
        return true;
    }
}
