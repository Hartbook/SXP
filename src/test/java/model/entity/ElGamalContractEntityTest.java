package model.entity;

import model.entity.ElGamalContractEntity;
import org.junit.Test;
import static org.junit.Assert.*;


import javax.persistence.Entity;

import crypt.impl.key.ElGamalAsymKey;
import crypt.impl.signatures.ElGamalSignature;
import protocol.api.Wish;

import rest.util.JsonUtils;

public class ElGamalContractEntityTest {
	ElGamalContractEntity contract = new ElGamalContractEntity();
	
	@Test
	public void test() {
    for(Wish wish : Wish.values()){
		contract.setWish(wish);
		assertTrue(contract.getWish() == wish);
		}
	
	assertTrue(contract.getParties() == null);
	assertTrue(contract.getSignatures() == null);	
	}
}
