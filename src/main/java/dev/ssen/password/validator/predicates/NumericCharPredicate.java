package dev.ssen.password.validator.predicates;

import static dev.ssen.password.validator.rules.RuleConstants.PASSWORD_NUMERIC_MESSAGE;
import java.util.function.IntPredicate;

public class NumericCharPredicate implements CharacterPredicate {

	@Override
	public IntPredicate getPredicate() {
		return (ch -> Character.isDigit(ch));
	}

	@Override
	public String getMismatchMessage() {
		return PASSWORD_NUMERIC_MESSAGE;
	}

}
