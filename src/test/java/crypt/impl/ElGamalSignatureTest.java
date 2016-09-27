package crypt.impl;

import java.util.Arrays;
import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.*;
import crypt.impl.signatures.ElGamalSignature;

public class ElGamalSignatureTest {
	private BigInteger r = new BigInteger("12");
	private BigInteger s = new BigInteger("34");
	private BigInteger r2 = new BigInteger("122");
	private BigInteger s2 = new BigInteger("342");
	private BigInteger k = new BigInteger("56");
	private byte[] m = {7,8,9};

	@Test
	public void test() {
		ElGamalSignature signature = new ElGamalSignature(r,s);
		assertTrue(r.equals(signature.getR()));
		assertTrue(s.equals(signature.getS()));

		signature = new ElGamalSignature(r,s,k,m);
		assertTrue(r.equals(signature.getR()));
		assertTrue(s.equals(signature.getS()));
		assertTrue(k.equals(signature.getK()));
		assertTrue(Arrays.equals(m, signature.getM()));

		signature.setR(r2);
		signature.setS(s2);
		assertTrue(r2.equals(signature.getR()));
		assertTrue(s2.equals(signature.getS()));
	}
}

