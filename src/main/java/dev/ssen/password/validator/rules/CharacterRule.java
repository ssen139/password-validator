package dev.ssen.password.validator.rules;

import java.util.Optional;
import java.util.stream.IntStream;

import dev.ssen.password.validator.predicates.CharacterPredicate;

public class CharacterRule implements Rule {
	
	private CharacterPredicate predicate;

	public CharacterRule(CharacterPredicate predicate) {
		this.predicate = predicate;
	}
	
	@Override
	public Optional<String> validate(String password) {
		try (IntStream charStream = password.chars()) {
			boolean hasMatch = charStream.anyMatch(predicate.getPredicate());

			if (!hasMatch)
				return Optional.of(predicate.getMismatchMessage());
		} catch (NullPointerException e) {
			return Optional.empty();
		}

		return Optional.empty();
	}

}
