package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.HashMap;
import protocol.impl.sigma.And;
import protocol.impl.sigma.Or;
import crypt.factories.ElGamalAsymKeyFactory;
import model.entity.ElGamalKey;
import protocol.impl.sigma.Receiver;
import protocol.impl.sigma.ResEncrypt;
import protocol.impl.sigma.ResponsesSchnorr;
import protocol.impl.sigma.Sender;
import protocol.impl.sigma.Trent;

public class OrTest {
	
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

		BigInteger mask = new BigInteger("1234");

		Or or = new Or(receiver, mask, and);

		assertTrue(or.Verifies(resEncrypt));

		rK.clear();
		rK.put(response, receiverKey);
		assertFalse(or.Verifies(resEncrypt));
	}
}

