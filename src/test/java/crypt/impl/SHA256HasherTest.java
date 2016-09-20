package crypt.impl;

import java.util.Random;
import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;
import crypt.api.hashs.Hasher;
import crypt.factories.HasherFactory;
import crypt.impl.hashs.SHA256Hasher;

public class SHA256HasherTest {

	private static int maxMessageLength = 10000;
	private static int minMessageLength = 1;
	private static int nbMessages = 10000;
	private static float maxCollisionRatio = 1f / 1000000;

	@Test
	public void test() {
		testForMessages();
		testForHashables();
	}

	private void testForMessages() {
		Hasher hasher = HasherFactory.createSHA256Hasher();
		byte[] salt = HasherFactory.generateSalt();
		hasher.setSalt(salt);

		Random rand = new Random();

		byte[][] messages = new byte[nbMessages][];
		byte[][] hashs = new byte[messages.length][];

		for (int i = 0; i < messages.length; ++i) {
			int messageLength = rand.nextInt(maxMessageLength) + minMessageLength;
			if (i == 0)
				messageLength = 1;
			else if (i == messages.length - 1)
				messageLength = maxMessageLength;

			messages[i] = new byte[messageLength];
			rand.nextBytes(messages[i]);

			hashs[i] = hasher.getHash(messages[i]);
			assertTrue(hashs[i].length * Byte.SIZE == 256);
		}

		int nbCollisions = 0;

		for (int i = 0; i < hashs.length; i++)
			for (int j = i+1; j < hashs.length; j++)
				if (!Arrays.equals(messages[i], messages[j]))
					if (Arrays.equals(hashs[i], hashs[j]))
						nbCollisions++;


		int messageLength = maxMessageLength;
		byte[] hash = null;
		byte[] message = new byte[messageLength];
		rand.nextBytes(message);

		for (int i = 0; i < 50; i++) {
			byte[] currentHash = hasher.getHash(message);

			assertTrue(hash == null || Arrays.equals(currentHash, hash));

			hash = currentHash;
		}

		assertTrue(nbCollisions <= (maxCollisionRatio * nbMessages));
	}

	private void testForHashables() {
		Hasher hasher = HasherFactory.createSHA256Hasher();
		byte[] salt = HasherFactory.generateSalt();
		hasher.setSalt(salt);

		Random rand = new Random();
	}
}
