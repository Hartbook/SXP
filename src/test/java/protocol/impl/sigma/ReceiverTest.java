package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.ArrayList;
import protocol.impl.sigma.And;
import crypt.factories.ElGamalAsymKeyFactory;
import model.entity.ElGamalKey;
import protocol.impl.sigma.Receiver;
import protocol.impl.sigma.ResEncrypt;
import protocol.impl.sigma.ResponsesSchnorr;
import protocol.impl.sigma.Sender;
import protocol.impl.sigma.Trent;

public class ReceiverTest {
	
	@Test
	public void test() {
		ElGamalKey receiverKey = ElGamalAsymKeyFactory.create(false);
		ElGamalKey senderKey = ElGamalAsymKeyFactory.create(false);
		Receiver receiver = new Receiver();
		Sender sender = new Sender(senderKey);

		ElGamalKey trentKey = ElGamalAsymKeyFactory.create(false);
		Trent trent = new Trent(trentKey);

		String message = "Message";
		byte[] buffer = message.getBytes();
		ResEncrypt resEncrypt = sender.Encryption(buffer, trent.getKey());

		Responses response = sender.SendResponseSchnorrFabric(sender.getKeys());

		HashMap<Responses, ElGamalKey> rK = new HashMap<Responses, ElGamalKey>();
		rK.put(response, senderKey);

		And and = new And(receiver, rK, resEncrypt, response);

		assertTrue(receiver.Verifies(response, senderKey, resEncrypt));

		assertFalse(receiver.Verifies(false, rK, resEncrypt, response));
		assertTrue(receiver.Verifies(true, rK, resEncrypt, response));
		assertFalse(receiver.Verifies(and, false));
		assertTrue(receiver.Verifies(and, true));
		assertTrue(receiver.Verifies(new BigInteger("0"), resEncrypt, and));

		Masks mask = sender.SendMasksSchnorr();
		BigInteger challenge = sender.SendChallenge(mask, resEncrypt.getM());

		assertTrue(receiver.VerifiesChallenge(challenge, mask, resEncrypt.getM()));

		ArrayList<BigInteger> challenges = new ArrayList<BigInteger>();
		challenges.add(challenge);

		assertFalse(receiver.VerifiesChallenges(resEncrypt.getM(), challenge, challenges));

	}
}

