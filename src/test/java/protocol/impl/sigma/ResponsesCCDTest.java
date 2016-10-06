package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;
import java.math.BigInteger;
import protocol.impl.sigma.ResEncrypt;
import crypt.factories.ElGamalAsymKeyFactory;
import model.entity.ElGamalKey;
import protocol.impl.sigma.Trent;
import protocol.impl.sigma.ResponsesCCD;

public class ResponsesCCDTest {

	private byte[] msg = "Message".getBytes();

	private ResEncrypt encryption(byte[] a, ElGamalKey k) {
		ElGamal elGamal = new ElGamal(k);
		ElGamalEncrypt encrypt = elGamal.encryptForContract(a);
		return new ResEncrypt(encrypt.getU(), encrypt.getV(), a);
	}
	
	@Test
	public void test() {
		ElGamalKey key1 = ElGamalAsymKeyFactory.create(false);
		ElGamalKey key2 = ElGamalAsymKeyFactory.create(false);

		Trent trent = new Trent(key1);

		ResEncrypt res = encryption(msg, key1);

		BigInteger u = new BigInteger("123454");
		BigInteger v = new BigInteger("12456");		
		ResEncrypt badRes = new ResEncrypt(u,v,msg); 
		
		ResponsesCCD response = trent.SendResponse(res);

		assertTrue(response.Verifies(key1, res));
		assertFalse(response.Verifies(key2, res));
		assertFalse(response.Verifies(key1, badRes));

	}

	
}

