package com.ghoulean.sudoku.generators;

import com.ghoulean.sudoku.Puzzle;

public abstract class AbstractGenerator {

    /**
     * Generates a puzzle
     * @return A puzzle
     */
    public abstract Puzzle generate();
}
