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
		assertTrue(passwordValidator.validate("Passwords"));
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
		passwordValidator.validate("pass");
		assertThat(passwordValidator.getMessages(), not(hasItem("Password should not be Empty")));
	}
	
	@Test
	public void testNoUppercaseInvalid() {
		assertFalse(passwordValidator.validate("passwords"));
		assertThat(passwordValidator.getMessages(), hasItem("Password should have at least one Uppercase char"));
	}
	
	@Test
	public void testUppercaseValid() {
		assertTrue(passwordValidator.validate("Passwords"));
		assertThat(passwordValidator.getMessages(), not(hasItem("Password should have at least one Uppercase char")));
	}


	@Test
	public void testPasswordLengthValid_NoUppercaseInvalid() {
		assertFalse(passwordValidator.validate("passwords"));
		assertThat(passwordValidator.getMessages(), not(hasItem("Password length should be more than 8 chars")));
	}
}
