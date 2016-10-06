package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;
import java.math.BigInteger;
import protocol.impl.sigma.ResEncrypt;
import protocol.impl.sigma.Fabric;
import crypt.factories.ElGamalAsymKeyFactory;
import model.entity.ElGamalKey;
import protocol.impl.sigma.Trent;
import protocol.impl.sigma.ResponsesCCE;

public class ResponsesCCETest {

	private byte[] msg = "Message".getBytes();

	private Fabric fabric = new Fabric();
	private ResEncrypt encryption(byte[] a, ElGamalKey k) {
		ElGamal elGamal = new ElGamal(k);
		ElGamalEncrypt encrypt = elGamal.encryptForContract(a);
		return new ResEncrypt(encrypt.getU(), encrypt.getV(), a);
	}
	
	@Test
	public void test() {
		ElGamalKey key1 = ElGamalAsymKeyFactory.create(false);
		ElGamalKey key2 = ElGamalAsymKeyFactory.create(false);

		//Sender sender = new Sender(key1);

		ResEncrypt res = encryption(msg, key1);

		BigInteger u = new BigInteger("123454");
		BigInteger v = new BigInteger("12456");		
		ResEncrypt badRes = new ResEncrypt(u,v,msg); 
		
		//ResponsesCCE response = send.SendResponse(res);
		ResponsesCCE response = fabric.SendResponseCCEFabric(res, key1);
	
		assertTrue(response.Verifies(key1, res));
		assertFalse(response.Verifies(key2, res));
		assertFalse(response.Verifies(key1, badRes));

	}

	
}

