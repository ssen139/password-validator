package dev.ssen.password.validator.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author ssen
 *
 */
public class PasswordValidatorTest {

	@Test
	public void testPasswordLengthInvalid() {
		PasswordValidator passwordValidator = new PasswordValidator();
		assertFalse(passwordValidator.validate("pass"));
		assertThat(passwordValidator.getMessages(), hasItem("Password length should be more than 8 chars"));
	}

	@Test
	public void testPasswordLengthValid() {
		PasswordValidator passwordValidator = new PasswordValidator();
		assertTrue(passwordValidator.validate("password"));
		assertThat(passwordValidator.getMessages(), not(hasItem("Password length should be more than 8 chars")));
	}

}
