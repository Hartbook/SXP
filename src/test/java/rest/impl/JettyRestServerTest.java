package rest.impl;

import java.lang.*;
import java.util.Random;
import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.*;
import rest.api.RestServer;
import rest.factories.RestServerFactory;
import rest.impl.JettyRestServer;
import network.factories.PeerFactory;
import network.api.Peer;

public class JettyRestServerTest {

	RestServerFactory factory = new RestServerFactory();

	@Test
	public void test() {
	/*	int restPort = 8081;

		RestServer restServer = RestServerFactory.createAndStartDefaultRestServer(restPort);
		
		try
		{
			Thread.sleep(2000); // On laisse le temps au choses de s'init
		}
		catch (InterruptedException e)
		{}

		//restServer.stop();*/
	}
}
