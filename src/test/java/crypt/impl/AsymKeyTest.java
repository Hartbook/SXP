package crypt.impl;

import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.*;
import crypt.factories.AsymKeyFactory;
import crypt.api.key.AsymKey;
import crypt.impl.key.ElGamalAsymKey;

public class AsymKeyTest {

	public void testAsymKey(AsymKey key) {
		BigInteger publicKey = new BigInteger("16");
		BigInteger privateKey = new BigInteger("11037");

		key.setPublicKey(publicKey);
		key.setPrivateKey(privateKey);

		assertTrue(publicKey.equals(key.getPublicKey()));
		assertTrue(privateKey.equals(key.getPrivateKey()));
	}
}

