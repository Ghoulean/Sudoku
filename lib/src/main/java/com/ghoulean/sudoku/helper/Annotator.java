package com.ghoulean.sudoku.helper;

import com.ghoulean.sudoku.boards.Board;
import com.ghoulean.sudoku.tokens.Token;

import lombok.Getter;
import lombok.NonNull;

import java.util.EnumSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class Annotator {

    @Getter @NonNull private final List<List<Set<Token>>> annotations;

    /**
     * Encodes annotations or "marks" of a Sudoku board. Useful to use while solving
     * @param board Board to mark
     * @param tokenSet Tokenset to use
     */
    public Annotator(final Board board, final Set<Token> tokenSet) {
        annotations = new ArrayList<>();
        for (int i = 0; i < board.getWidth(); i += 1) {
            annotations.add(new ArrayList<>());
            for (int j = 0; j < board.getHeight(); j += 1) {
                if (board.get(i, j) == Token.BLANK) {
                    annotations.get(i).add(EnumSet.copyOf(tokenSet));
                } else {
                    annotations.get(i).add(EnumSet.noneOf(Token.class));
                }
            }
        }
    }

    /**
     * Get annotations at (x, y)
     * @param x x coordinate
     * @param y y coordinate
     * @return Annotations at (x, y)
     */
    public Set<Token> getAnnotations(final int x, final int y) {
        return annotations.get(x).get(y);
    }

    /**
     * Set annotations at (x, y)
     * @param x x coordinate
     * @param y y coordinate
     * @param tokenSet set of tokens to mark
     * @return resulting annotation at (x, y)
     */
    public Set<Token> setAnnotations(final int x, final int y, final Set<Token> tokenSet) {
        return annotations.get(x).set(y, tokenSet);
    }

    /**
     * Clear annotations at (x, y). Equivalent to setAnnotations with an empty set
     * @param x x coordinate
     * @param y y coordinate
     */
    public void removeAnnotations(final int x, final int y) {
        annotations.get(x).get(y).clear();
    }

    /**
     * Check whether the annotation of token is at (x, y)
     * @param x x coordinate
     * @param y y coordinate
     * @param token token to check for
     * @return true if token is annotated at (x, y) and false otherwise
     */
    public boolean getAnnotation(final int x, final int y, final Token token) {
        return annotations.get(x).get(y).contains(token);
    }

    /**
     * Set the annotation of token is at (x, y)
     * @param x x coordinate
     * @param y y coordinate
     * @param token token to set
     * @return result of adding annotation
     */
    public boolean setAnnotation(final int x, final int y, final Token token) {
        return annotations.get(x).get(y).add(token);
    }

    /**
     * Unset the annotation of token is at (x, y)
     * @param x x coordinate
     * @param y y coordinate
     * @param token token to set
     * @return result of removing annotation
     */
    public boolean removeAnnotation(final int x, final int y, final Token token) {
        return annotations.get(x).get(y).remove(token);
    }
}
