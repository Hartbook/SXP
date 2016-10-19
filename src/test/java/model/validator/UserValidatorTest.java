package model.validator;


import org.junit.Test;
import static org.junit.Assert.*;

import model.validator.UserValidator;

import crypt.api.signatures.Signer;
import crypt.impl.key.ElGamalAsymKey;
import crypt.factories.SignerFactory;
import crypt.impl.signatures.ElGamalSignature;
import model.entity.User;
import model.factory.ValidatorFactory;
import javax.validation.ValidationException;

public class UserValidatorTest {

	ValidatorFactory factory = new ValidatorFactory();
	@Test
	public void test() {
		UserValidator validator = factory.createUserValidator();
	}
}
