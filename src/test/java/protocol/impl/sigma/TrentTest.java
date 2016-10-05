package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

import protocol.impl.sigma.ResponsesCCD;
import protocol.impl.sigma.ResEncrypt;
import crypt.factories.ElGamalAsymKeyFactory;
import model.entity.ElGamalKey;
import protocol.impl.sigma.Trent;
import protocol.impl.sigma.ElGamal;

public class TrentTest {
	private byte[] msg = "Message".getBytes();

	private ResEncrypt encryption(byte[] a, ElGamalKey k) {
		ElGamal elGamal = new ElGamal(k);
		ElGamalEncrypt encrypt = elGamal.encryptForContract(a);
		return new ResEncrypt(encrypt.getU(), encrypt.getV(), a);
	}
	
	@Test
	public void test() {
		ElGamalKey key = ElGamalAsymKeyFactory.create(false);

		Trent trent = new Trent(key);

		ResEncrypt res = encryption(msg, key);

		ResponsesCCD response = trent.SendResponse(res);

		assertTrue(trent.getKey() == key);

		ElGamal elGamal = new ElGamal(key);

		byte[] encrypted = elGamal.encryptWithPublicKey(msg);

		assertTrue(Arrays.equals(trent.decryption(encrypted), msg));
	}
}

