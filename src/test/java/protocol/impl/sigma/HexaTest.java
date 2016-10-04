package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import protocol.impl.sigma.Hexa;

public class HexaTest {
	private String text = "CINDY";
	private String hex = "43494E4459";
	private byte[] bytes = {(byte) 0x43, (byte) 0x49,
							(byte) 0x4E, (byte) 0x44, (byte) 0x59};

	private void testExceptions() {
		try {
			Hexa.stringToHex(null);
			fail("");
		} catch (NullPointerException e) {

		}
	}

	@Test
	public void test() {
		Hexa hexa = new Hexa();

		assertTrue(hex.equals(hexa.bytesToHex(bytes).toUpperCase()));
		assertTrue(hex.equals(hexa.bytesToHex_UpperCase(bytes)));
		assertTrue(text.equals(hexa.hexToString(hexa.bytesToHex(bytes))));
		assertTrue(hex.equals(hexa.stringToHex(text).toUpperCase()));
		assertTrue(text.equals(hexa.bytesToString(bytes)));
		assertTrue(Arrays.equals(bytes, hexa.hexToBytes(hex)));

		testExceptions();
	}
}

