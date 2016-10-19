package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;
import java.math.BigInteger;

import protocol.impl.sigma.Utils;

public class UtilsTest {

	private Utils utils = new Utils();
	private String text = "CINDY";
	private String hex = "43494E4459";
	private byte[] bytes = {(byte) 0x43, (byte) 0x49,
							(byte) 0x4E, (byte) 0x44, (byte) 0x59};

	
	private void testRand(){
			BigInteger bigInt = new BigInteger("8541578548458");
			BigInteger randBigInt = utils.rand(bigInt.bitLength(), bigInt);
			
			assert(randBigInt.compareTo(BigInteger.ONE) >= 0 && randBigInt.compareTo(bigInt) <= 0);	
	}
	
	
	private void testToHex(){
	String hex = "43494E4459";
	byte[] bytes = {(byte) 0x43, (byte) 0x49,
					(byte) 0x4E, (byte) 0x44, (byte) 0x59};	
	String result = utils.toHex(bytes);
	assertTrue(result.equals(hex));
	
	}
	
	@Test
	public void test() {
		testRand();
		testToHex();
	}
}

