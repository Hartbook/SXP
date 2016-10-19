package rest.impl;

import java.lang.*;
import java.util.Random;
import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.*;
import rest.factories.AuthentifierFactory;
import rest.api.Authentifier;
import rest.impl.SimpleAuthentifier;

public class SimpleAuthentifierTest {
	private static int nbTestCases = 100;
	private static int maxStringLength = 50;

	private String randomString(int length, Random rand) {
		String str = new String();

		for (int i = 0; i < length; i++)
			str += (char)(rand.nextInt(127));

		System.out.println(str);

		return str;
	}

	@Test
	public void test() {
		AuthentifierFactory factory = new AuthentifierFactory();

		Authentifier authentifier = factory.createAuthentifier("simple");

		Random rand = new Random();

		String[] usernames = new String[nbTestCases];
		String[] passwords = new String[nbTestCases];
		String[] tokens = new String[nbTestCases];

		for (int i = 0; i < nbTestCases; i++)
		{
			usernames[i] = randomString(maxStringLength, rand);
			passwords[i] = randomString(maxStringLength, rand);

			tokens[i] = authentifier.getToken(usernames[i], passwords[i]);
		}

		for (int i = 0; i < nbTestCases; i++)
		{
			String login = authentifier.getLogin(tokens[i]);
			String password = authentifier.getPassword(tokens[i]);

			assertTrue(login.equals(usernames[i]));
			assertTrue(password.equals(passwords[i]));

		}
		
		String login = authentifier.getLogin(tokens[1]);
		authentifier.deleteToken(tokens[1]);
		assertFalse(login.equals(authentifier.getLogin(tokens[1])));
	}
}
