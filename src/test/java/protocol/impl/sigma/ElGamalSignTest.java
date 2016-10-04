package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigInteger;
import protocol.impl.sigma.ElGamalSign;

public class ElGamalSignTest {
	private BigInteger r = new BigInteger("11037");
	private BigInteger s = new BigInteger("16");
	
	@Test
	public void test() {
		ElGamalSign sign = new ElGamalSign(r, s);

		assertTrue(r.equals(sign.getR()));
		assertTrue(s.equals(sign.getS()));
		sign.toString();
	}
}

