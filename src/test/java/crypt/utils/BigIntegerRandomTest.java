package crypt.utils;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Random;
import java.math.BigInteger;

import protocol.impl.sigma.Utils;

public class BigIntegerRandomTest {

	private BigIntegerRandom random = new BigIntegerRandom();
	
	private void testRand(){
			BigInteger bigInt = new BigInteger("4548415248745857");
			BigInteger randBigInt = random.rand(bigInt.bitLength(), bigInt);
			
			assert(randBigInt.compareTo(BigInteger.ONE) >= 0 && randBigInt.compareTo(bigInt) <= 0);	
	}
	
	@Test
	public void test() {
		testRand();
	}
}

