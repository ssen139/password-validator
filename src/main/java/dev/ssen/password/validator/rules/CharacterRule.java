package dev.ssen.password.validator.rules;

import static dev.ssen.password.validator.rules.RuleType.NON_MANDATORY;

import java.util.Optional;
import java.util.stream.IntStream;

import dev.ssen.password.validator.predicates.CharacterPredicate;

public class CharacterRule implements Rule {
	private RuleType ruleType;
	private CharacterPredicate predicate;

	public CharacterRule(CharacterPredicate predicate) {
		this.predicate = predicate;
		this.ruleType = NON_MANDATORY;
	}
	
	public CharacterRule(CharacterPredicate predicate, RuleType ruleType) {
		this.predicate = predicate;
		this.ruleType = ruleType;
	}
	
	@Override
	public Optional<String> validate(String password) {
		try (IntStream charStream = password.chars()) {
			boolean hasMatch = charStream.anyMatch(predicate.getPredicate());

			if (!hasMatch)
				return Optional.of(predicate.getMismatchMessage());
		} catch (NullPointerException e) {
			return Optional.of(predicate.getMismatchMessage());
		}

		return Optional.empty();
	}

	@Override
	public RuleType getRuleType() {
		return ruleType;
	}
	
}
