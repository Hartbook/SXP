package network.impl;

import org.junit.Test;
import static org.junit.Assert.*;


import network.impl.MessagesGeneric;
import java.util.Arrays;

public class MessagesGenericTest {

	@Test
	public void test() {
		MessagesGeneric msgs = new MessagesGeneric();
		msgs.addField("Steins", "Gate");
		msgs.addField("Makise", "Kurisu");
		
		String[] names = msgs.getNames();
		
		assertTrue(names.length == 2);
		
		for (String name : names)
			assertTrue(name.equals("Steins") || name.equals("Makise"));
		
		assertTrue(msgs.getMessage("Makise").equals("Kurisu"));
		
		msgs.setWho("Cindy");
		
		assertTrue(msgs.getWho().equals("Cindy"));

	}
}
