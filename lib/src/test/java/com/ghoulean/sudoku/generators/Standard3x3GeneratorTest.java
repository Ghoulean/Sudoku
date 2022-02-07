package com.ghoulean.sudoku.generators;

import com.ghoulean.sudoku.Puzzle;
import com.ghoulean.sudoku.solvers.Standard3x3Solver;

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
    void shouldGeneratePuzzleWithExactlyOneSolution() {
        final Puzzle puzzle = standard3x3Generator.generate();
        Assertions.assertEquals(standard3x3Solver.solve(puzzle).size(), 1);
    }
}
