package network.utils;
import org.junit.Test;
import static org.junit.Assert.*;
import network.impl.advertisement.ItemAdvertisement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.InetAddress;

public class IpCheckerTest{

	
	IpChecker ipChecker = new IpChecker();
	@Test
	public void test() throws Exception{

		System.out.println(ipChecker.getIp());
		assertTrue(InetAddress.getLocalHost().getHostAddress().equals(ipChecker.getIp()));
	}
}
