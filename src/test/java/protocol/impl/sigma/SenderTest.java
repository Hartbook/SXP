package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigInteger;
import crypt.factories.ElGamalAsymKeyFactory;
import model.entity.ElGamalKey;
import protocol.impl.sigma.ResponsesCCE;
import protocol.impl.sigma.ResponsesSchnorr;
import protocol.impl.sigma.Sender;

public class SenderTest {

	@Test
	public void test() {
		byte[] input = "input".getBytes();
		ElGamalKey key = ElGamalAsymKeyFactory.create(false);

		Sender sender = new Sender(key);

		ResEncrypt res = sender.Encryption(input, key);

		Masks masks = sender.SendMasksSchnorr();
		BigInteger challenge = sender.SendChallenge(masks, input);

		ResponsesSchnorr schnorr = sender.SendResponseSchnorr(input);
		assertTrue(schnorr.Verifies(key, res));

		schnorr = sender.SendResponseSchnorr(input, challenge);
		assertTrue(schnorr.Verifies(key, res));

		ResponsesCCE cce = sender.SendResponseCCE(input, key);
		assertTrue(cce.Verifies(key, res));

		cce = sender.SendResponseCCE(input, key, challenge);
		assertTrue(cce.Verifies(key, res));

		assertTrue(sender.getKeys() == key);
	}
}

