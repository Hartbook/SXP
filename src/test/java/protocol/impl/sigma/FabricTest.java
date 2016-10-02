package protocol.impl.sigma;

import org.junit.Test;
import static org.junit.Assert.*;

import crypt.factories.ElGamalAsymKeyFactory;
import model.entity.ElGamalKey;
import protocol.impl.sigma.ResponsesCCE;
import protocol.impl.sigma.ResponsesSchnorr;
import protocol.impl.sigma.Fabric;

public class FabricTest {

	private ResEncrypt encryption(byte[] a, ElGamalKey k) {
		ElGamal elGamal = new ElGamal(k);
		ElGamalEncrypt encrypt = elGamal.encryptForContract(a);
		return new ResEncrypt(encrypt.getU(), encrypt.getV(), a);
	}

	@Test
	public void test() {
		byte[] input = "input".getBytes();
		Fabric fabric = new Fabric();
		ElGamalKey key = ElGamalAsymKeyFactory.create(false);
		ResEncrypt res = encryption(input, key);

		ResponsesSchnorr schnorr = fabric.SendResponseSchnorrFabric(key);
		ResponsesCCE cce = fabric.SendResponseCCEFabric(res, key);

		assertTrue(schnorr.Verifies(key, res));
		assertTrue(cce.Verifies(key, res));
	}
}

