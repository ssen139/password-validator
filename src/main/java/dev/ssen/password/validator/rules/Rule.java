package dev.ssen.password.validator.rules;

import java.util.Optional;

public interface Rule {
	
	public Optional<String> validate(String password);
	public RuleType getRuleType();
}
