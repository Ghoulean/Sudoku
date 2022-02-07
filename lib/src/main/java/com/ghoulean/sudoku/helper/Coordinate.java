package com.ghoulean.sudoku.helper;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Coordinate {
    @Getter private final int x;
    @Getter private final int y;
}
