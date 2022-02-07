package com.ghoulean.sudoku.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

import com.ghoulean.sudoku.Puzzle;
import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.boards.Board9x9;
import com.ghoulean.sudoku.solvers.Standard3x3Solver;
import com.ghoulean.sudoku.tokens.Token;
import com.ghoulean.sudoku.tokens.TokenSet;
import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.validators.AbstractValidator;
import com.ghoulean.sudoku.validators.ColumnValidator;
import com.ghoulean.sudoku.validators.Grid3x3Validator;
import com.ghoulean.sudoku.validators.RowValidator;
import com.ghoulean.sudoku.validators.TokenSetValidator;

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

    private static final int MAX_SEEDING_ATTEMPTS = 99;
    private static final int SEEDING_HINTS = 15;
    private static final int LOWER_HINT_LIMIT = 20;
    private static final int UPPER_HINT_LIMIT = 36;
    private static final int MAX_REMOVAL_ATTEMPTS = 999;
    private static final Standard3x3Solver STANDARD_3X3_SOLVER = new Standard3x3Solver();

    @Override
    public Puzzle generate() {
        Board9x9 board = getSeedBoard();
        int hints = board.getHeight() * board.getWidth();
        int attempts = 0;
        while (!terminateHintRemoval(hints)) {
            attempts += 1;
            if (attempts > MAX_REMOVAL_ATTEMPTS) {
                throw new IllegalStateException("Can't finish generating board");
            }
            hints -= tryRemove(board);
        }
        return new Puzzle(board, TOKEN_SET_TYPE, VALIDATORS);
    }

    private int tryRemove(final Board board) {
        final int x = RandomUtils.nextInt(0, board.getWidth());
        final int y = RandomUtils.nextInt(0, board.getHeight());
        final Token t = board.get(x, y);
        if (t.equals(Token.BLANK)) {
            return 0;
        }
        board.set(x, y, Token.BLANK);
        if (!hasExactlyOneSolution(board)) {
            board.set(x, y, t);
            return 0;
        }
        return 1;
    }

    private boolean terminateHintRemoval(final int currentHints) {
        if (currentHints > UPPER_HINT_LIMIT) {
            return false;
        } else if (currentHints <= LOWER_HINT_LIMIT) {
            return true;
        } else {
            return (RandomUtils.nextInt(LOWER_HINT_LIMIT, UPPER_HINT_LIMIT) >= currentHints);
        }
    }

    private boolean hasExactlyOneSolution(final Board board) {
        return STANDARD_3X3_SOLVER.solve(new Puzzle(board, TOKEN_SET_TYPE, VALIDATORS)).size() == 1;
    }

    private Board9x9 getSeedBoard() {
        Set<Board9x9> boards;
        int seedingAttempts = 0;
        do {
            seedingAttempts += 1;
            if (seedingAttempts > MAX_SEEDING_ATTEMPTS) {
                throw new IllegalStateException("Cannot find a seeding position during generation");
            }
            final Board9x9 initialSeedingBoard = new Board9x9();
            for (int i = 0; i < SEEDING_HINTS; i += 1) {
                randomPlace(initialSeedingBoard);
            }
            boards = STANDARD_3X3_SOLVER.solve(new Puzzle(initialSeedingBoard, TOKEN_SET_TYPE, VALIDATORS));
        } while (boards.isEmpty());
        final Board9x9 board = boards.iterator().next();
        return board;
    }

    private void randomPlace(final Board board) {
        while (true) {
            final int x = RandomUtils.nextInt(0, board.getWidth());
            final int y = RandomUtils.nextInt(0, board.getHeight());
            if (board.get(x, y).equals(Token.BLANK)) {
                final int i = RandomUtils.nextInt(0, TOKEN_LIST.size());
                final Token token = TOKEN_LIST.get(i);
                board.set(x, y, token);
                if (isInViolation(board, VALIDATORS)) {
                    board.set(x, y, Token.BLANK);
                } else {
                    return;
                }
            }
        }
    }

    private boolean isInViolation(final Board board, final Set<AbstractValidator> validators) {
        for (AbstractValidator validator: validators) {
            if (!validator.validate(board)) {
                return true;
            }
        }
        return false;
    }
}