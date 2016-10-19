package model.validator;


import org.junit.Test;
import static org.junit.Assert.*;

import model.validator.ItemValidator;

import crypt.api.signatures.Signer;
import crypt.impl.key.ElGamalAsymKey;
import crypt.factories.SignerFactory;
import crypt.impl.signatures.ElGamalSignature;
import model.entity.Item;
import model.factory.ValidatorFactory;
import javax.validation.ValidationException;

public class ItemValidatorTest {

	ValidatorFactory factory = new ValidatorFactory();
	@Test
	public void test() {
		ItemValidator validator = factory.createItemValidator();
		Item item = new Item();
			
		ItemValidator validator2 = factory.createItemValidator();
		
		assertFalse(validator2.validate());
		
		assertFalse(validator2.validate());
		
		Signer signer = SignerFactory.createElGamalSigner(); 
		
		validator2.setSigner(signer);
		
		assertTrue(validator.getViolations() == null);

	}
}
