package dev.ssen.password.validator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.ssen.password.validator.rules.Rule;

public class RuleValidatorEngine {

	private List<Rule> ruleList;
	private List<String> messages;
	private Boolean valid;
	private Integer validRuleCount;

	public RuleValidatorEngine(List<Rule> ruleList) {
		this.ruleList = ruleList;
		this.messages = new ArrayList<>();
		this.valid = true;
		this.validRuleCount = 0;
	}

	public void validate(String password) {
		if (ruleListPresent()) {
			for (Rule rule : ruleList) {
				Optional<String> optionalMessage = rule.validate(password);
				if (optionalMessage.isPresent()) {
					messages.add(optionalMessage.get());
				}else {
					incrementValidRuleCount();
				}
			}
			
			setValid(isCriteriaMet());
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
		validRuleCount+=1;
	}
	
	private Boolean isCriteriaMet() {
		return (validRuleCount >=3) ;
	}

}
