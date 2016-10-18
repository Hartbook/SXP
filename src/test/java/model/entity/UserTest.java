package model.entity;


import org.junit.Test;
import static org.junit.Assert.*;

import model.entity.User;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.UuidGenerator;


public class UserTest {
	@Test
	public void test() {
		User user = new User();		
		
		user.setId("Setton");
		assertTrue(user.getId().equals("Setton"));		

		user.setNick("Aigis");
		assertTrue(user.getNick().equals("Aigis"));		
		
		Date date = new Date (2016, 06, 9);
		user.setCreatedAt(date);
		assertTrue(user.getCreatedAt().equals(date));	
		
		byte[] salt = {2,6,5,8};
		user.setSalt(salt);
		assertTrue(user.getSalt().equals(salt));
		
		byte[] bytes = {8,9,25,15};
		user.setPasswordHash(bytes);
		assertTrue(user.getPasswordHash().equals(bytes));				
		
		ElGamalKey key = new ElGamalKey();
		user.setKey(key);
		assertTrue(user.getKey().equals(key));				
								
	}
}
