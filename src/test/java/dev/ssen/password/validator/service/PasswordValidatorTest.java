package dev.ssen.password.validator.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author ssen
 *
 */
public class PasswordValidatorTest {
	
	private PasswordValidator passwordValidator;
	
	@Before
	public void setUp() {
		passwordValidator = new PasswordValidator();
	}

	@Test
	public void testPasswordLengthInvalid() {
		assertFalse(passwordValidator.validate("pass"));
		assertThat(passwordValidator.getMessages(), hasItem("Password length should be more than 8 chars"));
	}

	@Test
	public void testPasswordLengthValid() {
		assertTrue(passwordValidator.validate("Passwords1"));
		assertThat(passwordValidator.getMessages(), not(hasItem("Password length should be more than 8 chars")));
	}
	
	@Test
	public void testNullPasswordInvalid() {
		assertFalse(passwordValidator.validate(null));
		assertThat(passwordValidator.getMessages(), hasItem("Password should not be Empty"));
	}
	
	@Test
	public void testEmptyPasswordInvalid() {
		assertFalse(passwordValidator.validate(""));
		assertThat(passwordValidator.getMessages(), hasItem("Password should not be Empty"));
	}
	
	@Test
	public void testNonNullPasswordValid() {
		assertTrue(passwordValidator.validate("Password1"));
		assertThat(passwordValidator.getMessages(), not(hasItem("Password should not be Empty")));
	}
	
	@Test
	public void testNoUppercaseInvalid() {
		assertFalse(passwordValidator.validate("pass"));
		assertThat(passwordValidator.getMessages(), hasItem("Password should have at least one Uppercase char"));
	}
	
	@Test
	public void testUppercaseValid() {
		assertTrue(passwordValidator.validate("Passwords1"));
		assertThat(passwordValidator.getMessages(), not(hasItem("Password should have at least one Uppercase char")));
	}

	@Test
	public void testPasswordLengthValid_NoUppercaseInvalid() {
		assertFalse(passwordValidator.validate("passwords"));
		assertThat(passwordValidator.getMessages(), not(hasItem("Password length should be more than 8 chars")));
	}
	
	@Test
	public void testNoLowerCaseInvalid() {
		assertFalse(passwordValidator.validate("PASS"));
		assertThat(passwordValidator.getMessages(), hasItem("Password should have at least one Lowercase char"));
	}
	
	@Test
	public void testLowerCaseValid() {
		assertTrue(passwordValidator.validate("PASSWORDs1"));
		assertThat(passwordValidator.getMessages(), not(hasItem("Password should have at least one Lowercase char")));
	}
	
	@Test
	public void testNoDigitInvalid() {
		assertFalse(passwordValidator.validate("PASSs"));
		assertThat(passwordValidator.getMessages(), hasItem("Password should have at least one Numeric char"));
	}
	
	@Test
	public void testDigitValid() {
		assertTrue(passwordValidator.validate("Password1"));
		assertThat(passwordValidator.getMessages(), not(hasItem("Password should have at least one Numeric char")));
	}
	
	@Test
	/*
	 * password should be valid if it fulfills 3 rules
	 */
	public void testThreeConditionsValid() {
		assertTrue(passwordValidator.validate("Pass1"));
	}
}
