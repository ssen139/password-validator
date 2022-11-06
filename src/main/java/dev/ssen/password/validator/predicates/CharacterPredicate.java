package dev.ssen.password.validator.predicates;

import java.util.function.IntPredicate;

public interface CharacterPredicate {

	public IntPredicate getPredicate();
	public String getMismatchMessage();
}
