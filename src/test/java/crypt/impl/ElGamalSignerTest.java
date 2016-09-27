package crypt.impl;

import org.junit.Test;
import static org.junit.Assert.*;
import crypt.factories.ElGamalAsymKeyFactory;
import crypt.impl.key.ElGamalAsymKey;
import crypt.impl.signatures.ElGamalSignature;
import crypt.impl.signatures.ElGamalSigner;
import model.entity.ElGamalKey;

public class ElGamalSignerTest {
	private static final String message1 = "LoremIpsum";
	private static final String message2 = "Lorem Ipsum";

	private void testExceptions() {
		ElGamalSigner signer = new ElGamalSigner();
		ElGamalKey badKey = ElGamalAsymKeyFactory.create(false);
		badKey.setPublicKey(null);
		badKey.setPrivateKey(null);

		try {
			signer.sign(new byte[1]);
			fail();
		} catch (RuntimeException e) {}
		try {
			signer.verify(new byte[1], null);
			fail();
		} catch (RuntimeException e) {}

		signer.setKey(badKey);

		try {
			signer.sign(new byte[1]);
			fail();
		} catch (RuntimeException e) {}
		try {
			signer.verify(new byte[1], null);
			fail();
		} catch (RuntimeException e) {}
	}
	
	@Test
	public void test() {
		ElGamalKey key1 = ElGamalAsymKeyFactory.create(false);
		ElGamalKey key2 = ElGamalAsymKeyFactory.create(false);
		
		ElGamalSigner signer = new ElGamalSigner();
		signer.setKey(key1);
		ElGamalSignature sign = signer.sign(message1.getBytes());
		assertTrue(message1, signer.verify(message1.getBytes(), sign));
		assertFalse(message1, signer.verify(message2.getBytes(), sign));
		signer.setKey(key2);
		assertTrue(key2 == signer.getKey());
		assertFalse(message2, signer.verify(message1.getBytes(), sign));

		testExceptions();
	}
}
