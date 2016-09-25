package crypt.impl;

import java.util.Random;
import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;
import crypt.api.hashs.Hasher;
import crypt.api.hashs.Hashable;
import crypt.factories.HasherFactory;
import crypt.impl.hashs.SHA256Hasher;
import crypt.impl.DefaultHasherTest;

public class SHA256HasherTest {
	private DefaultHasherTest baseTest = new DefaultHasherTest();
	private Random rand = new Random();

	private void testHashLength(Hasher hasher) {
		int messageLength = rand.nextInt(baseTest.maxMessageLength) + baseTest.minMessageLength;
		byte[] message;
		byte[] hash;

		for (int i = 0; i < 10; i++) {
			message = new byte[messageLength];
			rand.nextBytes(message);
			hash = hasher.getHash(message);
			assertTrue(hash.length * Byte.SIZE == 256);
		}
	}

	@Test
	public void test() {
		byte[] salt = baseTest.factory.generateSalt();

		Hasher hasherSalt = new SHA256Hasher(salt);
		hasherSalt = baseTest.factory.createSHA256Hasher();
		hasherSalt.setSalt(salt);

		Hasher hasherNoSalt = baseTest.factory.createSHA256Hasher();

		testHashLength(hasherSalt);
		testHashLength(hasherNoSalt);

		baseTest.testHasher(hasherSalt, hasherNoSalt);
	}
}

