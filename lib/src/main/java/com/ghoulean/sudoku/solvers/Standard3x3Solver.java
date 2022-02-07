package com.ghoulean.sudoku.solvers;

import com.ghoulean.sudoku.Puzzle;
import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.boards.Board9x9;
import com.ghoulean.sudoku.helper.Annotator;
import com.ghoulean.sudoku.helper.Coordinate;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.validators.AbstractValidator;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Standard3x3Solver extends AbstractSolver<Board9x9> {

    private static final int NUM_VALIDATORS = 4;
    private static final int GRID_SIZE = 3;
    private static final DFSBacktrackingSolver DFS_BACKTRACKING_SOLVER = new DFSBacktrackingSolver();

    @Override
    public Set<Board9x9> solve(@NonNull final Puzzle puzzle) {
        if (!verify(puzzle)) {
            throw new IllegalArgumentException("Puzzle must be standard 3x3 Sudoku");
        }
        final Board9x9 board = new Board9x9(puzzle.getStartingBoard());
        final Annotator annotator = new Annotator(board, TokenSet.getDefault9x9TokenSet());
        Set<Coordinate> toCheck = new HashSet<>();
        for (int i = 0; i < Board9x9.WIDTH; i += 1) {
            for (int j = 0; j < Board9x9.HEIGHT; j += 1) {
                toCheck.add(new Coordinate(i, j));
            }
        }
        while (!toCheck.isEmpty()) {
            updateAnnotations(board, annotator, toCheck);
            toCheck = updateBoard(board, annotator);
        }
        if (puzzle.isSolved(board)) {
            return Collections.singleton(board);
        } else {
            final Puzzle simplifiedPuzzle = new Puzzle(board, puzzle.getTokenSetType(), puzzle.getValidators());
            final Set<Board> solutions = DFS_BACKTRACKING_SOLVER.solve(simplifiedPuzzle);
            return convertSetBoard(solutions);
        }
    }

    private void updateAnnotations(final Board board, final Annotator annotator, final Set<Coordinate> toCheck) {
        for (Coordinate coordinate : toCheck) {
            int x = coordinate.getX();
            int y = coordinate.getY();
            Token token = board.get(x, y);
            if (token == Token.BLANK || token == Token.INVALID) {
                continue;
            }
            List<Coordinate> reached = reachedTiles(x, y);
            for (Coordinate coord : reached) {
                annotator.removeAnnotation(coord.getX(), coord.getY(), token);
            }
        }
    }

    private Set<Coordinate> updateBoard(final Board board, final Annotator annotator) {
        Set<Coordinate> newHints = new HashSet<>();
        for (int i = 0; i < Board9x9.WIDTH; i += 1) {
            for (int j = 0; j < Board9x9.HEIGHT; j += 1) {
                if (board.get(i, j) != Token.BLANK && board.get(i, j) != Token.INVALID) {
                    continue;
                }
                Set<Token> annotations = annotator.getAnnotations(i, j);
                if (annotations.size() == 0) {
                    throw new IllegalStateException("Tile found that cannot contain any valid token");
                }
                if (annotations.size() == 1) {
                    Token token = annotations.iterator().next();
                    annotator.removeAnnotations(i, j);
                    board.set(i, j, token);
                    newHints.add(new Coordinate(i, j));
                }
            }
        }
        return newHints;
    }

    private List<Coordinate> reachedTiles(final int x, final int y) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < Board9x9.WIDTH; i += 1) {
            coordinates.add(new Coordinate(i, y));
        }
        for (int j = 0; j < Board9x9.HEIGHT; j += 1) {
            coordinates.add(new Coordinate(x, j));
        }
        for (int i = x - (x % GRID_SIZE); i < x - (x % GRID_SIZE) + GRID_SIZE; i += 1) {
            for (int j = y - (y % GRID_SIZE); j < y - (y % GRID_SIZE) + GRID_SIZE; j += 1) {
                coordinates.add(new Coordinate(i, j));
            }
        }
        return coordinates;
    }

    private boolean verify(@NonNull final Puzzle puzzle) {
        Board board = puzzle.getStartingBoard();
        Set<AbstractValidator> validators = puzzle.getValidators();
        Set<String> validatorNames = validators.stream()
            .map(validator -> validator.getName())
            .collect(Collectors.toSet());
        return (board.getHeight() == Board9x9.HEIGHT)
            && (board.getWidth() == Board9x9.WIDTH)
            && (validatorNames.size() == NUM_VALIDATORS)
            && (validatorNames.contains("Grid3x3Validator"))
            && (validatorNames.contains("ColumnValidator"))
            && (validatorNames.contains("RowValidator"))
            && (validatorNames.contains("TokenSetValidator"));
    }

    private Set<Board9x9> convertSetBoard(final Set<Board> boards) {
        Set<Board9x9> convertedBoards = new HashSet<>();
        for (Board board: boards) {
            convertedBoards.add(new Board9x9(board));
        }
        return convertedBoards;
    }

}
