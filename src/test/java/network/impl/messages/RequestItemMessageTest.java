package network.impl.messages;

import org.junit.Test;
import static org.junit.Assert.*;


import network.impl.messages.RequestItemMessage;
import java.util.Arrays;
import java.lang.*;
import network.api.annotation.MessageElement;
import network.impl.MessagesImpl;

public class RequestItemMessageTest {

	private void testExceptions() {
		RequestItemMessage msg = new RequestItemMessage();
		
		try {
			msg.getMessage("dawa");
			fail("");
		}
		catch (RuntimeException e) {}
	}

	@Test
	public void test() {
		RequestItemMessage msg = new RequestItemMessage();
		msg.setTitle("Franck");
		assertTrue(msg.getTitle().equals("Franck"));
		assertTrue(msg.getMessage("title").equals("Franck"));
		
		msg.setSource("Kirigiri");
		assertTrue(msg.getSource().equals("Kirigiri"));
		assertTrue(msg.getMessage("source").equals("Kirigiri"));
		
		msg.setWho("Cindy");
		assertTrue(msg.getWho().equals("Cindy"));		
		assertTrue(msg.getMessage("WHO").equals("Cindy"));
		
		assertTrue(msg.getMessage("type").equals("request"));
				
		String[] names = msg.getNames();
		for (int i = 0; i < names.length; i++)
			msg.getMessage(names[i]);

		testExceptions();
	}
}
