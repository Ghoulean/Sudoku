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
    F("F"),
    G("G");

    private final String shortName;

    Token(final String shortName) {
       this.shortName = shortName;
    }

    /**
     * @return short name
     */
    public String getShortName() {
        return this.shortName;
    }

    /**
     * @param shortName Short name
     * @return the token corresponding to the given short name
     */
    public static Token fromShortName(final String shortName) {
        for (Token t : Token.values()) {
            if (t.getShortName().equals(shortName)) {
                return t;
            }
        }
        throw new IllegalArgumentException("No token with short name " + shortName);
    }

    @Override
    public String toString() {
        return shortName;
    }
}
