package model.entity;

import network.api.ItemService;
import org.junit.Test;
import static org.junit.Assert.*;

import model.entity.ElGamalAsymKey;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import crypt.base.AbstractAsymKey;


public class ElGamalAsymKeyTest {
	@Test
	public void test() {
		ElGamalAsymKey key = new ElGamalAsymKey();
		BigInteger publicKey = new BigInteger("1235452"); 
		key.setPublicKey(publicKey);
		assertTrue(key.getPublicKey().equals(publicKey));
		
		BigInteger privateKey = new BigInteger("666"); 
		key.setPrivateKey(privateKey);
		assertTrue(key.getPrivateKey().equals(privateKey));		
		
		try{
			key.getParam("dawa");
			fail("");
		} catch(RuntimeException e) {}
		
		BigInteger g = new BigInteger("888"); 
		key.setG(g);
		assertTrue(key.getParam("g").equals(g));
		assertTrue(key.getG().equals(g));

		BigInteger p = new BigInteger("777"); 
		key.setP(p);
		assertTrue(key.getParam("p").equals(p));
		assertTrue(key.getP().equals(p));
		
	}
}
