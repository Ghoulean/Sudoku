package com.ghoulean.sudoku.generators;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import com.ghoulean.sudoku.Puzzle;
import com.ghoulean.sudoku.solvers.Standard3x3Solver;
import com.ghoulean.sudoku.validators.AbstractValidator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class Standard3x3GeneratorTest {

    private Standard3x3Generator standard3x3Generator;
    private Standard3x3Solver standard3x3Solver;

    @BeforeEach
    void setUp() {
        standard3x3Generator = new Standard3x3Generator();
        standard3x3Solver = new Standard3x3Solver();
    }

    @Test
    void shouldGenerateValidStartingBoard() {
        assertTimeoutPreemptively(Duration.ofMinutes(1), () -> {
            final Puzzle puzzle = standard3x3Generator.generate();
            for (AbstractValidator v: puzzle.getValidators()) {
                Assertions.assertTrue(v.validate(puzzle.getStartingBoard()));
            }
        });
    }

    @Test
    void shouldGeneratePuzzleWithExactlyOneSolution() {
        assertTimeoutPreemptively(Duration.ofMinutes(1), () -> {
            final Puzzle puzzle = standard3x3Generator.generate();
            Assertions.assertEquals(1, standard3x3Solver.solve(puzzle).size());
        });
    }
}
