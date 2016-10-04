package protocol.impl.sigma;


import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

import protocol.impl.sigma.Hasher;

public class HasherTest {

	public int maxMessageLength = 1000;
	public int minMessageLength = 1;
	public int nbMessages = 1000;
	public float maxCollisionRatio = 1f / 1000000;
	private Random rand = new Random();
	
	private void testDeterminism(Hasher hasher, int function){
		if (function == 1){
			byte[] message = new byte[maxMessageLength];
			String hash = null;
			rand.nextBytes(message);

			for (int i = 0; i < 20; i++) {
				String currentHash = hasher.SHA256(message);

				assertTrue(hash == null || currentHash.equals(hash));

				hash = currentHash;
			}
		}
	
		else if (function == 2){
			byte[] message = new byte[maxMessageLength];
			byte[] salt =  {1, 2, 3, 4, 5, 6, 7, 8, 9};
			String hash = null;
			rand.nextBytes(message);

			for (int i = 0; i < 20; i++) {
				String currentHash = hasher.SHA256(message, salt);

				assertTrue(hash == null || currentHash.equals(hash));

				hash = currentHash;
			}
		}
		
		else if (function == 3){
			String message = new String();
			String hash = null;
			byte[] byto = new byte[maxMessageLength];
			rand.nextBytes(byto);
			message = byto.toString();

			for (int i = 0; i < 20; i++) {
				String currentHash = hasher.SHA256(message);

				assertTrue(hash == null || currentHash.equals(hash));

				hash = currentHash;
			}
		}
		
		else if (function == 4){
			String message = new String();
			String salt = "Pu pu pu pu";
			String hash = null;
			byte[] byto = new byte[maxMessageLength];
			rand.nextBytes(byto);
			message = byto.toString();

			for (int i = 0; i < 20; i++) {
				String currentHash = hasher.SHA256(message, salt);

				assertTrue(hash == null || currentHash.equals(hash));

				hash = currentHash;
			}
		}
	}	
		
	private void testForCollisions(Hasher hasher, int function){
		if (function == 1){
			byte[][] messages = new byte[nbMessages][];
			String[] hashs = new String[messages.length];

			for (int i = 0; i < messages.length; ++i) {
				int messageLength = rand.nextInt(maxMessageLength) + minMessageLength;
				if (i == 0)
					messageLength = 1;
				else if (i == messages.length - 1)
					messageLength = maxMessageLength;

				messages[i] = new byte[messageLength];
				rand.nextBytes(messages[i]);

				hashs[i] = hasher.SHA256(messages[i]);
			}

			int nbCollisions = 0;

			for (int i = 0; i < hashs.length; i++)
				for (int j = i+1; j < hashs.length; j++)
					if (!Arrays.equals(messages[i], messages[j]))
						if (hashs[i].equals( hashs[j]))
							nbCollisions++;

			assertTrue(nbCollisions <= (maxCollisionRatio * nbMessages));
		}
		
		else if (function == 2){
			byte[][] messages = new byte[nbMessages][];
			byte[] salt = new byte[rand.nextInt(maxMessageLength) + minMessageLength];
			rand.nextBytes(salt);
			String[] hashs = new String[messages.length];

			for (int i = 0; i < messages.length; ++i) {
				int messageLength = rand.nextInt(maxMessageLength) + minMessageLength;
				if (i == 0)
					messageLength = 1;
				else if (i == messages.length - 1)
					messageLength = maxMessageLength;

				messages[i] = new byte[messageLength];
				rand.nextBytes(messages[i]);

				hashs[i] = hasher.SHA256(messages[i], salt);
			}

			int nbCollisions = 0;

			for (int i = 0; i < hashs.length; i++)
				for (int j = i+1; j < hashs.length; j++)
					if (!Arrays.equals(messages[i], messages[j]))
						if (hashs[i].equals( hashs[j]))
							nbCollisions++;

			assertTrue(nbCollisions <= (maxCollisionRatio * nbMessages));
		}
		
		else if (function == 3){
			String[] messages = new String[nbMessages];
			String[] hashs = new String[messages.length];

			for (int i = 0; i < messages.length; ++i) {
				int messageLength = rand.nextInt(maxMessageLength) + minMessageLength;
				if (i == 0)
					messageLength = 1;
				else if (i == messages.length - 1)
					messageLength = maxMessageLength;

				byte[] byto = new byte[messageLength];
				rand.nextBytes(byto);
				messages[i] = byto.toString();

				hashs[i] = hasher.SHA256(messages[i]);
			}
		}
		
		else if (function ==4){
			String[] messages = new String[nbMessages];
			String salt = "Ultimate Hope";
			String[] hashs = new String[messages.length];

			for (int i = 0; i < messages.length; ++i) {
				int messageLength = rand.nextInt(maxMessageLength) + minMessageLength;
				if (i == 0)
					messageLength = 1;
				else if (i == messages.length - 1)
					messageLength = maxMessageLength;

				byte[] byto = new byte[messageLength];
				rand.nextBytes(byto);
				messages[i] = byto.toString();

				hashs[i] = hasher.SHA256(messages[i], salt);
			}

			int nbCollisions = 0;

			for (int i = 0; i < hashs.length; i++)
				for (int j = i+1; j < hashs.length; j++)
					if (!messages[i].equals(messages[j]))
						if (hashs[i].equals( hashs[j]))
							nbCollisions++;

			assertTrue(nbCollisions <= (maxCollisionRatio * nbMessages));
		}
	}
	
	private void testHashLength(Hasher hasher, int function){
		if (function == 1){
			int messageLength = rand.nextInt(maxMessageLength) + minMessageLength;
			byte[] message;
			String hash;

			for (int i = 0; i < 10; i++) {
				message = new byte[messageLength];
				rand.nextBytes(message);
				hash = hasher.SHA256(message);
				assertTrue(hash.length() * 4 == 256);
			}
		}
		
		else if (function == 2){
			int messageLength = rand.nextInt(maxMessageLength) + minMessageLength;
			int saltLength = rand.nextInt(maxMessageLength) + minMessageLength;
			byte[] message;
			byte[] salt;
			String hash;

			for (int i = 0; i < 10; i++) {
				message = new byte[messageLength];
				rand.nextBytes(message);
				salt = new byte[saltLength];
				rand.nextBytes(salt);
				hash = hasher.SHA256(message,salt);
				assertTrue(hash.length() * 4 == 256);
			}
		}
		
		else if (function == 3){
			int messageLength = rand.nextInt(maxMessageLength) + minMessageLength;
			String message;
			String hash;

			for (int i = 0; i < 10; i++) {
				byte[] byto = new byte[messageLength];
				rand.nextBytes(byto);
				message = byto.toString();
				hash = hasher.SHA256(message);
				assertTrue(hash.length() * 4 == 256);
			}
		}
		
		else if (function == 4){
			int messageLength = rand.nextInt(maxMessageLength) + minMessageLength;
			int saltLength = rand.nextInt(maxMessageLength) + minMessageLength;
			String message;
			String salt;
			String hash;

			for (int i = 0; i < 10; i++) {
				byte[] messageByte = new byte[messageLength];
				byte[] saltByte = new byte[saltLength];
				rand.nextBytes(messageByte);
				rand.nextBytes(saltByte);
				message = messageByte.toString();
				salt = saltByte.toString();
				hash = hasher.SHA256(message,salt);
				assertTrue(hash.length() * 4 == 256);
			}
		}
	}
	
	private void testSaltEffect(Hasher hasher, int function) {
		if (function == 2){
			byte[] message = new byte[maxMessageLength];
			byte[] salt1 = new byte[maxMessageLength];
			byte[] salt2 = new byte[maxMessageLength];

			for (int i = 0; i < 40; i++) {
				rand.nextBytes(message);
				rand.nextBytes(salt1);
				rand.nextBytes(salt2);
				String hash1 = hasher.SHA256(message, salt1);
				String hash2 = hasher.SHA256(message, salt2);
				if(Arrays.equals(salt1,salt2))
					assertTrue(hash1.equals(hash2));
				else
					assertFalse(hash1.equals(hash2));
			}
		}
		
		if (function == 4){
			String message = new String();
			String salt1 = new String();
			String salt2 = new String();

			for (int i = 0; i < 40; i++) {
				byte[] messageByte = new byte[maxMessageLength];
				byte[] salt1Byte = new byte[maxMessageLength];
				byte[] salt2Byte = new byte[maxMessageLength];
				rand.nextBytes(messageByte);
				rand.nextBytes(salt1Byte);
				rand.nextBytes(salt2Byte);
				message = messageByte.toString();
				salt1 = salt1Byte.toString();
				salt2 = salt2Byte.toString();
				
				String hash1 = hasher.SHA256(message, salt1);
				String hash2 = hasher.SHA256(message, salt2);
				if(salt1.equals(salt2))
					assertTrue(hash1.equals(hash2));
				else
					assertFalse(hash1.equals(hash2));
			}
		}
		
	}


	
	private void TestFirstSHA256(Hasher hasher) {
		testDeterminism(hasher, 1);
		testForCollisions(hasher,1);
		testHashLength(hasher, 1);
	}
	
	private void TestSecondSHA256(Hasher hasher) {
		testDeterminism(hasher, 2);
		testForCollisions(hasher,2);
		testHashLength(hasher, 2);
		testSaltEffect(hasher, 2);
	}
	
	private void TestThirdSHA256(Hasher hasher) {
		testDeterminism(hasher, 3);
		testForCollisions(hasher,3);
		testHashLength(hasher, 3);
	}
	
	private void TestFourthSHA256(Hasher hasher) {
		testDeterminism(hasher, 4);
		testForCollisions(hasher,4);
		testHashLength(hasher, 4);
		testSaltEffect(hasher, 4);
	}
	
	@Test
	public void test() {
		Hasher hasher = new Hasher();
		TestFirstSHA256(hasher);
		TestSecondSHA256(hasher);
		TestThirdSHA256(hasher);
		TestFourthSHA256(hasher);
		hasher.main(null);
	}

}