package com.ghoulean.sudoku.generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.ghoulean.sudoku.Puzzle;
import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.boards.Board9x9;
import com.ghoulean.sudoku.helper.Coordinate;
import com.ghoulean.sudoku.solvers.Standard3x3Solver;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.validators.AbstractValidator;
import com.ghoulean.sudoku.validators.ColumnValidator;
import com.ghoulean.sudoku.validators.Grid3x3Validator;
import com.ghoulean.sudoku.validators.RowValidator;
import com.ghoulean.sudoku.validators.TokenSetValidator;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.apache.commons.rng.sampling.ListSampler;

public final class Standard3x3Generator extends AbstractGenerator {

    /** TokenSetType that this generator will always use when generating a puzzle */
    public static final TokenSetType TOKEN_SET_TYPE = TokenSetType.DEFAULT9X9;

    /** Tokenset that this generator will always use when generating a puzzle */
    public static final Set<Token> TOKEN_SET = TokenSet.getDefault9x9TokenSet();

    /** Set of validators that this generator will always use when generating a puzzle */
    public static final Set<AbstractValidator> VALIDATORS = Set.of(
        new TokenSetValidator(TOKEN_SET_TYPE),
        new Grid3x3Validator(TOKEN_SET_TYPE),
        new ColumnValidator(TOKEN_SET_TYPE),
        new RowValidator(TOKEN_SET_TYPE)
    );

    private static final List<Token> TOKEN_LIST = new ArrayList<Token>(TOKEN_SET);

    private static final int GRID_SIZE = 3;
    private static final int LOWER_HINT_LIMIT = 20;
    private static final int UPPER_HINT_LIMIT = 36;
    private static final int MAX_REMOVAL_ATTEMPTS = 81;
    private static final Standard3x3Solver STANDARD_3X3_SOLVER = new Standard3x3Solver();

    private static final String SEED_BOARD =
        "123456789456789123789123456231564897564897231897231564312645978645978312978312645";

    private final List<Coordinate> pseudorandomCoordinateList = populateCoordinateList();
    private int pseudorandomIndex = 0;

    private final UniformRandomProvider rng = RandomSource.MT.create();

    @Override
    public Puzzle generate() {
        Board9x9 board = getSeedBoard();
        int hints = board.getHeight() * board.getWidth();
        ListSampler.shuffle(rng, pseudorandomCoordinateList);
        final int hintLimit = rng.nextInt(UPPER_HINT_LIMIT - LOWER_HINT_LIMIT) + LOWER_HINT_LIMIT;
        int squaresTested = 0;
        while (hints > hintLimit && squaresTested <= MAX_REMOVAL_ATTEMPTS) {
            hints -= tryRemove(board);
            squaresTested += 1;
        }
        return new Puzzle(board, TOKEN_SET_TYPE, VALIDATORS);
    }

    private int tryRemove(final Board board) {
        final Coordinate randomCoordinate = getNextPseudorandomCoordinate();
        final int x = randomCoordinate.getX();
        final int y = randomCoordinate.getY();
        final Token t = board.get(x, y);
        board.set(x, y, Token.BLANK);
        if (!hasExactlyOneSolution(board)) {
            board.set(x, y, t);
            return 0;
        }
        return 1;
    }

    private boolean hasExactlyOneSolution(final Board board) {
        return STANDARD_3X3_SOLVER.solve(new Puzzle(board, TOKEN_SET_TYPE, VALIDATORS)).size() == 1;
    }

    private Board9x9 getSeedBoard() {
        final Board initialSeedingBoard = new Board(Board9x9.WIDTH, Board9x9.HEIGHT, SEED_BOARD);
        scrambleBoard(initialSeedingBoard);
        return new Board9x9(initialSeedingBoard);
    }

    private ArrayList<Coordinate> populateCoordinateList() {
        final ArrayList<Coordinate> list = new ArrayList<>();
        for (int i = 0; i < Board9x9.WIDTH; i += 1) {
            for (int j = 0; j < Board9x9.HEIGHT; j += 1) {
                list.add(new Coordinate(i, j));
            }
        }
        return list;
    }

    private Coordinate getNextPseudorandomCoordinate() {
        pseudorandomIndex = (pseudorandomIndex + 1) % (Board9x9.WIDTH * Board9x9.HEIGHT);
        return pseudorandomCoordinateList.get(pseudorandomIndex);
    }

    private void scrambleBoard(final Board board) {
        shuffleNumbers(board);
        shuffleColumnsInBlock(board, 0);
        shuffleColumnsInBlock(board, GRID_SIZE);
        shuffleColumnsInBlock(board, 2 * GRID_SIZE);
        shuffleRowsInBlock(board, 0);
        shuffleRowsInBlock(board, GRID_SIZE);
        shuffleRowsInBlock(board, 2 * GRID_SIZE);
        shuffleBlockRows(board);
        shuffleBlockColumns(board);
    }

    private void shuffleNumbers(final Board board) {
        ArrayList<Token> permutation = new ArrayList<Token>(TOKEN_SET);
        ListSampler.shuffle(rng, permutation);
        for (int i = 0; i < board.getWidth(); i += 1) {
            for (int j = 0; j < board.getHeight(); j += 1) {
                int index = TOKEN_LIST.indexOf(board.get(i, j));
                board.set(i, j, permutation.get(index));
            }
        }
    }

    private void shuffleColumnsInBlock(final Board board, final int leftColumnIndex) {
        final Board helper = new Board(Board9x9.WIDTH, Board9x9.HEIGHT);
        ArrayList<Integer> columnShuffle = threeShuffle();
        for (int i = leftColumnIndex; i < leftColumnIndex + GRID_SIZE; i += 1) {
            final int newX = columnShuffle.get(i - leftColumnIndex) + leftColumnIndex;
            for (int j = 0; j < Board9x9.HEIGHT; j += 1) {
                helper.set(newX, j, board.get(i, j));
            }
        }
        for (int i = leftColumnIndex; i < leftColumnIndex + GRID_SIZE; i += 1) {
            for (int j = 0; j < Board9x9.HEIGHT; j += 1) {
                board.set(i, j, helper.get(i, j));
            }
        }
    }

    private void shuffleRowsInBlock(final Board board, final int topRowIndex) {
        final Board helper = new Board(Board9x9.WIDTH, Board9x9.HEIGHT);
        ArrayList<Integer> rowShuffle = threeShuffle();
        for (int i = topRowIndex; i < topRowIndex + GRID_SIZE; i += 1) {
            final int newY = rowShuffle.get(i - topRowIndex) + topRowIndex;
            for (int j = 0; j < Board9x9.WIDTH; j += 1) {
                helper.set(j, newY, board.get(j, i));
            }
        }
        for (int i = topRowIndex; i < topRowIndex + GRID_SIZE; i += 1) {
            for (int j = 0; j < Board9x9.WIDTH; j += 1) {
                board.set(j, i, helper.get(j, i));
            }
        }
    }

    private void shuffleBlockColumns(final Board board) {
        final Board helper = new Board(Board9x9.WIDTH, Board9x9.HEIGHT);
        ArrayList<Integer> columnShuffle = threeShuffle();
        for (int i = 0; i < Board9x9.WIDTH; i += 1) {
            final int newX = columnShuffle.get(i / GRID_SIZE) * GRID_SIZE + (i % GRID_SIZE);
            for (int j = 0; j < Board9x9.HEIGHT; j += 1) {
                helper.set(newX, j, board.get(i, j));
            }
        }
        for (int i = 0; i < Board9x9.WIDTH; i += 1) {
            for (int j = 0; j < Board9x9.HEIGHT; j += 1) {
                board.set(i, j, helper.get(i, j));
            }
        }
    }

    private void shuffleBlockRows(final Board board) {
        final Board helper = new Board(Board9x9.WIDTH, Board9x9.HEIGHT);
        ArrayList<Integer> rowShuffle = threeShuffle();
        for (int j = 0; j < Board9x9.HEIGHT; j += 1) {
            final int newY = rowShuffle.get(j / GRID_SIZE) * GRID_SIZE + (j % GRID_SIZE);
            for (int i = 0; i < Board9x9.WIDTH; i += 1) {
                helper.set(i, newY, board.get(i, j));
            }
        }
        for (int i = 0; i < Board9x9.WIDTH; i += 1) {
            for (int j = 0; j < Board9x9.HEIGHT; j += 1) {
                board.set(i, j, helper.get(i, j));
            }
        }
    }

    private ArrayList<Integer> threeShuffle() {
        ArrayList<Integer> permutation = new ArrayList<Integer>(Arrays.asList(0, 1, 2));
        ListSampler.shuffle(rng, permutation);
        return permutation;
    }
}
