package com.ghoulean.sudoku.tokens;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import lombok.experimental.UtilityClass;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@UtilityClass
public class TokenSet {
    private static final Set<Token> BLANK_TOKENSET =
        Collections.<Token>emptySet();
    private static final Set<Token> DEFAULT9X9_TOKENSET =
        EnumSet.range(Token.ONE, Token.NINE);
    private static final Set<Token> DEFAULT16X16_TOKENSET =
        EnumSet.range(Token.ONE, Token.F);

    private static final Map<TokenSetType, Set<Token>> TOKENSET_MAP =
        Map.ofEntries(
            Map.entry(TokenSetType.EMPTY, BLANK_TOKENSET),
            Map.entry(TokenSetType.DEFAULT9X9, DEFAULT9X9_TOKENSET),
            Map.entry(TokenSetType.DEFAULT16X16, DEFAULT16X16_TOKENSET)
        );

    /**
     * Returns token set based on name. Throws exception on passing
     * TokenSetType.CUSTOM
     * @param tokenSetType TokenSetType
     * @return set of tokens corresponding to TokenSetType
     */
    public static Set<Token> getTokenSet(final TokenSetType tokenSetType) {
        return TOKENSET_MAP.get(tokenSetType);
    }

    /**
     * Returns empty tokenset
     * @return empty tokenset
     */
    public static Set<Token> getBlankTokenSet() {
        return BLANK_TOKENSET;
    }

    /**
     * Returns the default tokenset for standard 9x9 boards containing 1-9
     * @return 9x9 tokenset
     */
    public static Set<Token> getDefault9x9TokenSet() {
        return DEFAULT9X9_TOKENSET;
    }

    /**
     * Returns the default tokenset for large 16x16 boards containing 1-F
     * @return 16x16 tokenset
     */
    public static Set<Token> getDefault16x16TokenSet() {
        return DEFAULT16X16_TOKENSET;
    }
}
