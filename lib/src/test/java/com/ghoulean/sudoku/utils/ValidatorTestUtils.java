package com.ghoulean.sudoku.utils;

import java.util.Set;

import com.ghoulean.sudoku.tokens.TokenSetType;
import com.ghoulean.sudoku.validators.AbstractValidator;
import com.ghoulean.sudoku.validators.ColumnValidator;
import com.ghoulean.sudoku.validators.Grid3x3Validator;
import com.ghoulean.sudoku.validators.Grid4x4Validator;
import com.ghoulean.sudoku.validators.RowValidator;
import com.ghoulean.sudoku.validators.TokenSetValidator;

import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
public class ValidatorTestUtils {

    public static Set<AbstractValidator> getDefault9x9Validators(final TokenSetType tokenSetType) {
        return Set.of(new TokenSetValidator(tokenSetType),
                      new ColumnValidator(tokenSetType),
                      new RowValidator(tokenSetType),
                      new Grid3x3Validator(tokenSetType));
    }

    public static Set<AbstractValidator> getDefault16x16Validators(final TokenSetType tokenSetType) {
        return Set.of(new TokenSetValidator(tokenSetType),
                      new ColumnValidator(tokenSetType),
                      new RowValidator(tokenSetType),
                      new Grid4x4Validator(tokenSetType));
    }
}
