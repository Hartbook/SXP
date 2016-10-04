package protocol.impl.sigma;


import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.math.BigInteger;

import protocol.impl.sigma.Hasher;

public class ResEncryptTest {

	private Random rand = new Random();

	@Test
	public void test() {
		byte[] uByte = new byte[1000];
		byte[] vByte = new byte[1000];
		byte[] M = new byte[1000];

		rand.nextBytes(uByte);
		rand.nextBytes(vByte);
		rand.nextBytes(M);

		BigInteger u = new BigInteger(uByte);
		BigInteger v = new BigInteger(vByte);		
		ResEncrypt resEncrypt = new ResEncrypt(u,v,M); 
		
		assertTrue(u.equals(resEncrypt.getU()));
		assertTrue(v.equals(resEncrypt.getV()));
		assertTrue(Arrays.equals(M, resEncrypt.getM()));
		
		rand.nextBytes(uByte);
		rand.nextBytes(vByte);
		rand.nextBytes(M);
		
		u = new BigInteger(uByte);
		v = new BigInteger(vByte);
		
		resEncrypt.setV(v);
		resEncrypt.setU(u);
		resEncrypt.setM(M);
		
		assertTrue(u.equals(resEncrypt.getU()));
		assertTrue(v.equals(resEncrypt.getV()));
		assertTrue(Arrays.equals(M, resEncrypt.getM()));
	}

}
