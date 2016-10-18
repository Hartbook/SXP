package model.entity;

import model.entity.LoginToken;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.persistence.Entity;


public class LoginTokenTest {
	@Test
	public void test() {
	
		LoginToken loginToken = new LoginToken();
		loginToken.setToken("Hope");
		assertTrue(loginToken.getToken().equals("Hope"));
		
		loginToken.setUserid("Setton");
		assertTrue(loginToken.getUserid().equals("Setton"));
	}
}
