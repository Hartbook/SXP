package crypt;

import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.*;
import crypt.factories.AsymKeyFactory;
import crypt.api.key.AsymKey;
import crypt.impl.key.ElGamalAsymKey;
import crypt.impl.key.ElGamalAsymKey;
import crypt.ElGamalEngine;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ElGamalParameters;
import org.bouncycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.bouncycastle.crypto.params.ElGamalPublicKeyParameters;
import org.bouncycastle.crypto.DataLengthException;

public class ElGamalEngineTest {

	@Test
	public void test() {
		ElGamalEngine engine = new ElGamalEngine();

		ParametersWithRandom badParameters = new ParametersWithRandom(
		new ElGamalPrivateKeyParameters(new BigInteger("11037"), 
		new ElGamalParameters(new BigInteger("123"), new BigInteger("321"))));

		ParametersWithRandom badParameters2 = new ParametersWithRandom(
		new ElGamalPublicKeyParameters(new BigInteger("11037"), 
		new ElGamalParameters(new BigInteger("123"), new BigInteger("321"))));

		try {
			engine.init(true, badParameters);
			fail("");
		} catch (IllegalArgumentException e) {}

		try {
			engine.init(false, badParameters2);
			fail("");
		} catch (IllegalArgumentException e) {}

		BigInteger p = new BigInteger("13105534554545663354636547373464633747746373463774");

		ParametersWithRandom parametersPub = new ParametersWithRandom(
		new ElGamalPublicKeyParameters(new BigInteger("11037"), 
		new ElGamalParameters(p, new BigInteger("321"))));

		engine.init(true, parametersPub);

		assertTrue(((p.bitLength()-1)/8) == engine.getInputBlockSize());

		ParametersWithRandom parametersPriv = new ParametersWithRandom(
		new ElGamalPrivateKeyParameters(new BigInteger("11037"), 
		new ElGamalParameters(p, new BigInteger("321"))));
	
		engine.init(false, parametersPriv);

		assertTrue(((p.bitLength()-1)/8) == engine.getOutputBlockSize());

		ElGamalEngine badEngine = new ElGamalEngine();

		try {
			badEngine.processBlock("Cindy".getBytes(), 0, 5);
			fail("");
		} catch (IllegalStateException e) {}

		try {
			engine.processBlock("Cindy".getBytes(), 0, 1000);
			fail("");
		} catch (DataLengthException e) {}

		engine.init(true, parametersPub);

		engine.processBlock("Lionel Messi est dopé".getBytes(), 0, 4);
	}
}

