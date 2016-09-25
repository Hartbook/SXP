package crypt.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import crypt.factories.ElGamalAsymKeyFactory;
import crypt.impl.encryption.ElGamalEncrypter;
import crypt.impl.key.ElGamalAsymKey;
import model.entity.ElGamalKey;

public class ElGamalEncrypterTest {
	private static final String plainText = "Lorem ipsum dolor sit amet";

	private void testExceptions() {
		ElGamalEncrypter encrypter = new ElGamalEncrypter();

		try {
			encrypter.encrypt(plainText.getBytes());
			fail();
		} catch (RuntimeException e) {}
		try {
			encrypter.decrypt(new byte[1]);
			fail();
		} catch (RuntimeException e) {}
	}
	
	@Test
	public void test() {
		ElGamalKey key = ElGamalAsymKeyFactory.create(false);
		ElGamalEncrypter encrypter = new ElGamalEncrypter();
		encrypter.setKey(key);
		byte[] cypher = encrypter.encrypt(plainText.getBytes());
		String decrypted = new String(encrypter.decrypt(cypher));
		assertEquals(plainText, decrypted);
		assertNotEquals(plainText, cypher);

		testExceptions();
	}
}
