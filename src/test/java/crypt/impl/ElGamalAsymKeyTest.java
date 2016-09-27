package crypt.impl;

import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.*;
import crypt.factories.AsymKeyFactory;
import crypt.api.key.AsymKey;
import crypt.impl.key.ElGamalAsymKey;
import crypt.impl.AsymKeyTest;

public class ElGamalAsymKeyTest {
	private AsymKeyTest baseTest = new AsymKeyTest();
	private BigInteger p = new BigInteger("12");
	private BigInteger g = new BigInteger("34");
	private BigInteger publicKey = new BigInteger("56");
	private BigInteger privateKey = new BigInteger("78");

	private void assertValuesAreSet(ElGamalAsymKey key) {
		assertTrue(p.equals(key.getP()));
		assertTrue(g.equals(key.getG()));
		assertTrue(p.equals(key.getParam("p")));
		assertTrue(g.equals(key.getParam("g")));
		assertTrue(publicKey.equals(key.getPublicKey()));
		assertTrue(privateKey.equals(key.getPrivateKey()));
	}

	@Test
	public void test() {
		BigInteger p = new BigInteger("12");
		BigInteger g = new BigInteger("34");
		BigInteger publicKey = new BigInteger("56");
		BigInteger privateKey = new BigInteger("78");

		ElGamalAsymKey key = new ElGamalAsymKey();
		baseTest.testAsymKey(key);

		key.setPublicKey(publicKey);
		key.setPrivateKey(privateKey);
		key.setP(p);
		key.setG(g);
		assertValuesAreSet(key);

		key = new ElGamalAsymKey(p, g, publicKey);
		key.setPrivateKey(privateKey);
		assertValuesAreSet(key);

		key = new ElGamalAsymKey(p, g, publicKey, privateKey);
		assertValuesAreSet(key);
	}
}

