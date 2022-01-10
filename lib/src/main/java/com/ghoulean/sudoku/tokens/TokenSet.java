package com.ghoulean.sudoku.tokens;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class TokenSet {
    private static final Set<Token> BLANK_TOKENSET = Collections.<Token>emptySet();
    private static final Set<Token> DEFAULT9X9_TOKENSET = EnumSet.range(Token.ONE, Token.NINE);
    private static final Set<Token> DEFAULT16X16_TOKENSET = EnumSet.range(Token.ONE, Token.F);

    private static final Map<TokenSetType, Set<Token>> TOKENSET_MAP = Map.ofEntries(
        Map.entry(TokenSetType.EMPTY, BLANK_TOKENSET),
        Map.entry(TokenSetType.DEFAULT9X9, DEFAULT9X9_TOKENSET),
        Map.entry(TokenSetType.DEFAULT16X16, DEFAULT16X16_TOKENSET)
    );

    public static Set<Token> getTokenSet(TokenSetType tokenSetType) {
        return TOKENSET_MAP.get(tokenSetType);
    }

    public static Set<Token> getBlankTokenSet() {
        return BLANK_TOKENSET;
    }

    public static Set<Token> getDefault9x9TokenSet() {
        return DEFAULT9X9_TOKENSET;
    }

    public static Set<Token> getDefault16x16TokenSet() {
        return DEFAULT16X16_TOKENSET;
    }
}
