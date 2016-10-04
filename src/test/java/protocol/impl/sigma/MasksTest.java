package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigInteger;
import protocol.impl.sigma.Masks;

public class MasksTest {
	private BigInteger a = new BigInteger("11037");
	private BigInteger aBis = new BigInteger("16");

	@Test
	public void test() {
		Masks masks = new Masks(a, aBis);

		assertTrue(masks.getA().equals(a));
		assertTrue(masks.getaBis().equals(aBis));

		masks.setA(aBis);
		masks.setaBis(a);

		assertTrue(masks.getA().equals(aBis));
		assertTrue(masks.getaBis().equals(a));

		masks.toString();
	}
}

