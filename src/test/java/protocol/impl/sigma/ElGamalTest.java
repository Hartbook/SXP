package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

import model.entity.ElGamalKey;
import crypt.factories.ElGamalAsymKeyFactory;
import protocol.impl.sigma.ElGamal;

public class ElGamalTest {
	private ElGamalKey key = ElGamalAsymKeyFactory.create(false);
	private byte[] message = "I just loved anime.".getBytes();

	private void testExceptions() {
		ElGamalKey badKey = ElGamalAsymKeyFactory.create(false);
		ElGamal elGamal = new ElGamal(badKey);

		try {
			elGamal.verifySignature(message, null);
			fail("");
		}
		catch (NullPointerException e) {
		}

		ElGamalSign sign = elGamal.getMessageSignature(message);
		ElGamalSign badSign = new ElGamalSign(sign.getR(), null);

		try {
			elGamal.verifySignature(message, badSign);
			fail("");
		}
		catch (NullPointerException e) {
		}

		badSign = new ElGamalSign(null, sign.getS());

		try {
			elGamal.verifySignature(message, badSign);
			fail("");
		}
		catch (NullPointerException e) {
		}

		badKey.setPublicKey(null);

		try {
			elGamal.verifySignature(message, sign);
			fail("");
		}
		catch (NullPointerException e) {
		}

		badKey.setPrivateKey(null);
		try {
			elGamal.getMessageSignature(message);
			fail("");
		}
		catch (NullPointerException e) {
		}
	}

	private void testElGamalWithKeySet(ElGamal elGamal) {
		elGamal.encryptForContract(message);

		ElGamalSign sign = elGamal.getMessageSignature(message);
		assertTrue(elGamal.verifySignature(message, sign));

		byte[] encrypted = elGamal.encryptWithPublicKey(message);
		byte[] decrypted = elGamal.decryptWithPrivateKey(encrypted);

		assertTrue(Arrays.equals(decrypted, message));
	}
	
	@Test
	public void test() {
		ElGamal elGamal = new ElGamal();
		elGamal.setKeys(key);
		testElGamalWithKeySet(elGamal);

		elGamal = new ElGamal();
		elGamal.setAsymsKeys(key);
		testElGamalWithKeySet(elGamal);

		elGamal = new ElGamal(key);
		testElGamalWithKeySet(elGamal);

		testExceptions();
	}
}

