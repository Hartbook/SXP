package network.impl.jxta;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;

import net.jxta.discovery.DiscoveryEvent;
import net.jxta.discovery.DiscoveryListener;
import net.jxta.discovery.DiscoveryService;
import net.jxta.document.AdvertisementFactory;
import net.jxta.endpoint.ByteArrayMessageElement;
import net.jxta.endpoint.Message;
import net.jxta.endpoint.Message.ElementIterator;
import net.jxta.endpoint.MessageElement;
import net.jxta.id.IDFactory;
import net.jxta.peer.PeerID;
import net.jxta.peergroup.PeerGroup;
import net.jxta.pipe.OutputPipe;
import net.jxta.pipe.PipeID;
import net.jxta.pipe.PipeMsgEvent;
import net.jxta.pipe.PipeMsgListener;
import net.jxta.pipe.PipeService;
import net.jxta.protocol.PipeAdvertisement;
import network.api.Messages;
import network.api.Peer;
import network.api.SearchListener;
import network.api.ServiceListener;
import network.api.advertisement.Advertisement;
import network.api.service.Service;
import network.impl.MessagesGeneric;
import network.impl.jxta.JxtaService;

import org.junit.Test;
import static org.junit.Assert.*;



public class JxtaServiceTest {
	
	JxtaService jxtaService = new JxtaService();
	
	@Test
	public void test() {
		assertTrue(jxtaService.getName() == null);
		
		Message message = new Message();
		
		Messages messages = jxtaService.toMessages(message);
		
		assertTrue(messages.getWho() == null);		
		
	}
}

