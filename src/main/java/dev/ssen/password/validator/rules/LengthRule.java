package dev.ssen.password.validator.rules;

import static dev.ssen.password.validator.rules.RuleConstants.NULL_PASSWORD_MESSAGE;
import static dev.ssen.password.validator.rules.RuleConstants.PASSWORD_LENGTH_MESSAGE;
import static dev.ssen.password.validator.rules.RuleType.*;

import java.util.Optional;
public class LengthRule implements Rule {
	
	private RuleType ruleType;
	
	public LengthRule() {
		this.ruleType = NON_MANDATORY;
	}
	
	public LengthRule(RuleType ruleType) {
		this.ruleType = ruleType;
	}
	
	@Override
	public Optional<String> validate(String password) {
		if(password == null || password.isEmpty()) {
			return Optional.of(NULL_PASSWORD_MESSAGE);
		}
		if(password.length()<=8) {
			return Optional.of(PASSWORD_LENGTH_MESSAGE);
		}
		
		return Optional.empty();
	}

	@Override
	public RuleType getRuleType() {
		return ruleType;
	}

}
