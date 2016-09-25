package crypt.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import crypt.impl.encryption.SerpentEncrypter;

public class SerpentEncrypterTest {
	private static final String plainText = "Lorem ipsum dolor sit amet";
	private static final String password = "P4s$w0rD";

	private void testExceptions() {
		SerpentEncrypter encrypter = new SerpentEncrypter();

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
		SerpentEncrypter encrypter = new SerpentEncrypter();
		encrypter.setKey(password);
		byte[] cypher = encrypter.encrypt(plainText.getBytes());
		String plain = new String(encrypter.decrypt(cypher));
		assertEquals(plainText, plain);

		testExceptions();
	}
}
