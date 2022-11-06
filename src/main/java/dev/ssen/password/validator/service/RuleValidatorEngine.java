package dev.ssen.password.validator.service;

import static dev.ssen.password.validator.rules.RuleType.MANDATORY;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import dev.ssen.password.validator.rules.Rule;
import dev.ssen.password.validator.util.PropertyReader;

public class RuleValidatorEngine {

	private List<Rule> ruleList;
	private List<String> messages;
	private Boolean valid;
	private AtomicInteger validRuleCount;
	private Boolean mandatoryRuleFailed = false;

	public RuleValidatorEngine(List<Rule> ruleList) {
		this.ruleList = ruleList;
		this.messages = new ArrayList<>();
		this.valid = true;
		this.validRuleCount = new AtomicInteger(0);
	}

	public void validate(String password) {
		if (ruleListPresent()) {
			List<Runnable> tasks = new ArrayList<>();
			for (Rule rule : ruleList) {
				Runnable task = () -> executeRule(rule, password);
				tasks.add(task);
			}

			new RuleExecutorService().executeInParallel(tasks);
			setValid(isCriteriaMet());

		}
	}

	private void executeRule(Rule rule, String password) {
		Optional<String> optionalMessage = rule.validate(password);

		if (optionalMessage.isPresent()) {
			messages.add(optionalMessage.get());
			if (rule.getRuleType().equals(MANDATORY)) {
				setMandatoryRuleFailed();
			}
		} else {
			incrementValidRuleCount();
		}
	}

	private boolean ruleListPresent() {
		return (ruleList != null && !ruleList.isEmpty());
	}

	public List<String> getMessages() {
		return messages;
	}

	public Boolean isValid() {
		return valid;
	}

	private void setValid(Boolean valid) {
		this.valid = valid;
	}

	private void incrementValidRuleCount() {
		validRuleCount.incrementAndGet();
	}

	private Boolean isCriteriaMet() {
		return (validRuleCount.get() >= getValidRuleThreshold() && !mandatoryRuleFailed);
	}

	private void setMandatoryRuleFailed() {
		mandatoryRuleFailed = true;
	}

	private int getValidRuleThreshold() {
		return Integer.valueOf(PropertyReader.getInstance().getProperty("validRuleThreshold").orElse("3"));
	}

}
