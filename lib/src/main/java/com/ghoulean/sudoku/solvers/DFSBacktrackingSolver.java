package com.ghoulean.sudoku.solvers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.ghoulean.sudoku.Puzzle;
import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.validators.AbstractValidator;

public class DFSBacktrackingSolver extends AbstractSolver<Board> {

    private static final int MAX_ITERATIONS_PER_SOLUTION = 999999;

    /**
     * Find solutions using DFS search, from left to right then up to down. If a solution is not found by
     * MAX_ITERATIONS_PER_SOLUTION iterations, terminate the search. The iteration count resets when a solution is
     * found.
     */
    @Override
    public Set<Board> solve(final Puzzle puzzle) {
        final Set<Board> solutions = new HashSet<>();

        Board solution = new Board(puzzle.getStartingBoard());
        final Set<Token> tokenSet = TokenSet.getTokenSet(puzzle.getTokenSetType());
        final List<Token> tokenList = new ArrayList<Token>(tokenSet);
        final Set<AbstractValidator> validators = puzzle.getValidators();
        List<Integer> blankX = new LinkedList<>();
        List<Integer> blankY = new LinkedList<>();
        for (int i = 0; i < solution.getWidth(); i += 1) {
            for (int j = 0; j < solution.getHeight(); j += 1) {
                if (!puzzle.isStarting(i, j)) {
                    blankX.add(i);
                    blankY.add(j);
                }
            }
        }

        solution = findSolution(tokenList, validators, solution, blankX, blankY, 0);
        while (solution != null) {
            final Board copySolution = new Board(solution);
            copySolution.lock();
            solutions.add(copySolution);
            solution = findSolution(tokenList, validators, solution, blankX, blankY, blankX.size() - 1);
        }
        return solutions;
    }

    private Board findSolution(final List<Token> tokenList,
                               final Set<AbstractValidator> validators,
                               final Board board,
                               final List<Integer> blankX,
                               final List<Integer> blankY,
                               final int startIndex) {
        int index = startIndex;
        int attempts = 0;
        boolean forceChange = true;
        while (index < blankX.size()) {
            if (attempts >= MAX_ITERATIONS_PER_SOLUTION) {
                return null;
            }
            attempts += 1;
            if (index < 0) {
                return null;
            }
            int x = blankX.get(index);
            int y = blankY.get(index);
            if (board.get(x, y).equals(Token.BLANK)) {
                board.set(x, y, tokenList.get(0));
            } else if (forceChange || this.isInViolation(board, validators)) {
                forceChange = false;
                int nextIndex = tokenList.indexOf(board.get(x, y)) + 1;
                Token nextToken;
                if (nextIndex >= tokenList.size()) {
                    index -= 1;
                    forceChange = true;
                    nextToken = Token.BLANK;
                } else {
                    nextToken = tokenList.get(nextIndex);
                }
                board.set(x, y, nextToken);
            } else {
                index += 1;
            }
        }
        return board;
    }
}
