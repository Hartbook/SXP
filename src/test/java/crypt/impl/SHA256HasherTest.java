package crypt.impl;

import java.util.Random;
import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;
import crypt.api.hashs.Hasher;
import crypt.api.hashs.Hashable;
import crypt.factories.HasherFactory;
import crypt.impl.hashs.SHA256Hasher;

public class SHA256HasherTest {

	private class ToyHashable implements Hashable {
		private byte[] hashableData = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	
		@Override
		public byte[] getHashableData() {
			return hashableData;
		}
	}

	private int maxMessageLength = 1000;
	private int minMessageLength = 1;
	private int nbMessages = 1000;
	private float maxCollisionRatio = 1f / 1000000;
	private Random rand = new Random();

	private void testDeterminism(Hasher hasher) {
		byte[] message = new byte[maxMessageLength];
		byte[] hash = null;
		rand.nextBytes(message);

		for (int i = 0; i < 20; i++) {
			byte[] currentHash = hasher.getHash(message);

			assertTrue(hash == null || Arrays.equals(currentHash, hash));

			hash = currentHash;
		}
	}

	private void testSaltEffect(Hasher withSalt, Hasher noSalt) {
		byte[] message = new byte[maxMessageLength];

		for (int i = 0; i < 40; i++) {
			rand.nextBytes(message);
			byte[] hash2 = withSalt.getHash(message);
			byte[] hash1 = noSalt.getHash(message);

			assertFalse(Arrays.equals(hash1, hash2));
		}
	}

	private void testForCollisions(Hasher hasher) {
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
		}

		int nbCollisions = 0;

		for (int i = 0; i < hashs.length; i++)
			for (int j = i+1; j < hashs.length; j++)
				if (!Arrays.equals(messages[i], messages[j]))
					if (Arrays.equals(hashs[i], hashs[j]))
						nbCollisions++;

		assertTrue(nbCollisions <= (maxCollisionRatio * nbMessages));
	}

	private void testHashLength(Hasher hasher) {
		int messageLength = rand.nextInt(maxMessageLength) + minMessageLength;
		byte[] message;
		byte[] hash;

		for (int i = 0; i < 10; i++) {
			message = new byte[messageLength];
			rand.nextBytes(message);
			hash = hasher.getHash(message);
			assertTrue(hash.length * Byte.SIZE == 256);
		}
	}

	private void testHashableHash(Hasher hasher) {
		Hashable hashable = new ToyHashable();
		byte[] hash1 = hasher.getHash(hashable.getHashableData());
		byte[] hash2 = hasher.getHash(hashable);

		assertTrue(Arrays.equals(hash1, hash2));
	}

	@Test
	public void test() {
		byte[] salt = HasherFactory.generateSalt();

		Hasher hasherSalt = HasherFactory.createSHA256Hasher();
		hasherSalt.setSalt(salt);

		Hasher hasherNoSalt = HasherFactory.createSHA256Hasher();

		testDeterminism(hasherSalt);
		testForCollisions(hasherSalt);
		testHashLength(hasherSalt);
		testHashableHash(hasherSalt);

		testDeterminism(hasherNoSalt);
		testForCollisions(hasherNoSalt);
		testHashableHash(hasherSalt);
		testHashLength(hasherNoSalt);

		testSaltEffect(hasherSalt, hasherNoSalt);
	}
}

