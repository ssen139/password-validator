package dev.ssen.password.validator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.ssen.password.validator.predicates.LowerCasePredicate;
import dev.ssen.password.validator.predicates.UpperCasePredicate;
import dev.ssen.password.validator.rules.CharacterRule;
import dev.ssen.password.validator.rules.LengthRule;

/**
 * 
 * @author ssen
 *
 */
public class PasswordValidator {
	private List<String> messages;
	private RuleValidatorEngine ruleValidatorEngine;

	public PasswordValidator() {
		messages = new ArrayList<String>();
		ruleValidatorEngine = new RuleValidatorEngine(
				Arrays.asList(	new LengthRule(),
								new CharacterRule(new UpperCasePredicate()), 
								new CharacterRule(new LowerCasePredicate()) )
				);
	}

	public boolean validate(String password) {
		ruleValidatorEngine.validate(password);
		setMessages(ruleValidatorEngine.getMessages());
		return ruleValidatorEngine.isValid();
	}

	public List<String> getMessages() {
		return messages;
	}

	private void setMessages(List<String> messages) {
		this.messages = messages;
	}
}
