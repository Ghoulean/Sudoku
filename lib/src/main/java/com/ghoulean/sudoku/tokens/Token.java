package com.ghoulean.sudoku.tokens;

public enum Token {
    INVALID("X"),
    BLANK("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F");

    private final String shortName;

    Token(final String shortName) {
       this.shortName = shortName;
    }

    @Override
    public String toString() {
        return shortName;
    }
}
