package network.impl.jxta;

import network.api.ItemService;
import org.junit.Test;
import static org.junit.Assert.*;

import network.impl.jxta.JxtaService;

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

public class JxtaServiceTest {

	@Test
	public void test() {
		JxtaService jxtaService = new JxtaService();
		assertTrue(jxtaService.getName() == null);


	}
}
