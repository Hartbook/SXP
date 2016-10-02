package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.math.BigInteger;
import protocol.impl.sigma.ElGamalEncrypt;

public class ElGamalEncryptTest {
	private BigInteger u = new BigInteger("1606");
	private BigInteger v = new BigInteger("1995");
	private BigInteger k = new BigInteger("11037");
	private byte[] m = "Et qui, vivant des heures grises".getBytes();
	
	@Test
	public void test() {
		ElGamalEncrypt encrypt = new ElGamalEncrypt(u, v, k, m);

		assertTrue(u.equals(encrypt.getU()));
		assertTrue(v.equals(encrypt.getV()));
		assertTrue(k.equals(encrypt.getK()));
		assertTrue(Arrays.equals(m, encrypt.getM()));

		m[0] = 42;
		encrypt.setM(m);
		assertTrue(Arrays.equals(m, encrypt.getM()));
	}
}

