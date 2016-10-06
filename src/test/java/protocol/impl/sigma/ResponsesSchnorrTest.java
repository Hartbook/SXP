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
import protocol.impl.sigma.ResponsesSchnorr;

public class ResponsesSchnorrTest {

	private byte[] msg = "Message".getBytes();

	private Fabric fabric = new Fabric();

	
	@Test
	public void test() {
		ElGamalKey key1 = ElGamalAsymKeyFactory.create(false);
		ElGamalKey key2 = ElGamalAsymKeyFactory.create(false);

		BigInteger u = new BigInteger("123454");
		BigInteger v = new BigInteger("12456");		
		ResEncrypt res = new ResEncrypt(u,v,msg); 
		

		ResponsesSchnorr response = fabric.SendResponseSchnorrFabric(key1);
	
		assertTrue(response.Verifies(key1, res));
		assertFalse(response.Verifies(key2, res));

	}

	
}

