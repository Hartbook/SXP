package model.entity;


import org.junit.Test;
import static org.junit.Assert.*;

import model.entity.ElGamalSignEntity;

import java.io.Serializable;
import java.math.BigInteger;
import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import crypt.api.signatures.ParamName;
import crypt.base.BaseSignature;

import org.eclipse.persistence.annotations.UuidGenerator;

import java.lang.annotation.*;


public class ElGamalSignEntityTest {
	@Test
	public void test() {
		ElGamalSignEntity sign = new ElGamalSignEntity();		
		
		BigInteger r = new BigInteger("113265");
		sign.setR(r);
		assertTrue(sign.getR().equals(r));		
	
		BigInteger s = new BigInteger("56895");
		sign.setS(s);
		assertTrue(sign.getS().equals(s));	
		
		try
		{
			sign.getParam("BadParam");
			fail("");
		}catch(RuntimeException e){}
		
	}
}
