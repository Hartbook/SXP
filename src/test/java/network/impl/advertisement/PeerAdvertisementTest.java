package network.impl.advertisement;
import org.junit.Test;
import static org.junit.Assert.*;
import network.impl.advertisement.PeerAdvertisement;

import network.api.advertisement.PeerAdvertisementInterface;
import network.api.annotation.AdvertisementAttribute;
import network.impl.AbstractAdvertisement;


import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Element;

import net.jxta.document.MimeMediaType;
import network.api.Peer;
import network.api.advertisement.Advertisement;
import network.factories.AdvertisementFactory;
import network.factories.PeerFactory;
import network.impl.jxta.JxtaAdvertisement;
import network.impl.jxta.AdvertisementBridge;
import network.impl.advertisement.ItemAdvertisement;
import java.util.Arrays;
import java.lang.*;

public class PeerAdvertisementTest {

	AdvertisementFactory factory = new AdvertisementFactory();
	@Test
	public void test() {
		PeerAdvertisement peerAdv = (PeerAdvertisement)factory.createPeerAdvertisement();
		String name = new String ("peer");
		assertTrue(name.equals(peerAdv.getName()));

		assertTrue(peerAdv.getAdvertisementType() == null);
		
		/*Peer peer = PeerFactory.createDefaultAndStartPeer();

		try
		{
			Thread.sleep(2000); // On laisse le temps au choses de s'init
		}
		catch (InterruptedException e)
		{}

		peerAdv.publish(peer);*/
	
		Element elem = new Element("Coucou");
		Document doc = new Document (elem);
		
		peerAdv.initialize(doc);
		
		doc = peerAdv.getDocument();
		
		peerAdv.setSourceURI("Kirigiri");
		assertTrue(peerAdv.getSourceURI().equals("Kirigiri"));
		
		//peer.stop();
		
	}

}
