package com.ghoulean.sudoku.solvers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.ghoulean.sudoku.Puzzle;
import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.validators.AbstractValidator;

public class DFSBacktrackingSolver extends AbstractSolver<Board> {

    private static final int MAX_ITERATIONS = 999999;

    /**
     * Find solutions using DFS search, from left to right then up to down
     */
    @Override
    public Set<Board> solve(final Puzzle puzzle) {
        final Board solution = findSolution(puzzle);
        if (solution == null) {
            return Collections.emptySet();
        }
        return Collections.singleton(solution);
    }

    private Board findSolution(final Puzzle puzzle) {
        final Set<Token> tokenSet = TokenSet.getTokenSet(puzzle.getTokenSetType());
        final List<Token> tokenList = new ArrayList<Token>(tokenSet);
        final Board board = new Board(puzzle.getStartingBoard());
        final Set<AbstractValidator> validators = puzzle.getValidators();

        List<Integer> blankX = new LinkedList<>();
        List<Integer> blankY = new LinkedList<>();
        for (int i = 0; i < board.getWidth(); i += 1) {
            for (int j = 0; j < board.getHeight(); j += 1) {
                if (!puzzle.isStarting(i, j)) {
                    blankX.add(i);
                    blankY.add(j);
                }
            }
        }
        int i = 0;
        int attempts = 0;
        boolean forceChange = false;
        while (i < blankX.size()) {
            if (attempts >= MAX_ITERATIONS) {
                System.out.println(board.toString());
                return null;
            }
            attempts += 1;
            if (i < 0) {
                return null;
            }
            int x = blankX.get(i);
            int y = blankY.get(i);
            if (board.get(x, y).equals(Token.BLANK)) {
                board.set(x, y, tokenList.get(0));
            } else if (forceChange || this.isInViolation(board, validators)) {
                forceChange = false;
                int nextIndex = tokenList.indexOf(board.get(x, y)) + 1;
                Token nextToken;
                if (nextIndex >= tokenList.size()) {
                    i -= 1;
                    forceChange = true;
                    nextToken = Token.BLANK;
                } else {
                    nextToken = tokenList.get(nextIndex);
                }
                board.set(x, y, nextToken);
            } else {
                i += 1;
            }
        }
        return board;
    }
}
