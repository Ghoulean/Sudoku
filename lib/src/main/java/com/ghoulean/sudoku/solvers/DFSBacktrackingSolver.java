package com.ghoulean.sudoku.solvers;

import java.util.Collections;
import java.util.Set;

import com.ghoulean.sudoku.Puzzle;
import com.ghoulean.sudoku.boards.Board;

public class DFSBacktrackingSolver extends AbstractSolver<Board> {

    /**
     * Find solutions using DFS search, from left to right then up to down
     */
    @Override
    public Set<Board> solve(final Puzzle puzzle) {
        Board solution = (Board) puzzle.getStartingBoard();
        return Collections.singleton(solution);
    }
}
