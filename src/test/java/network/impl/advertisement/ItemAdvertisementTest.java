package network.impl.advertisement;
import org.junit.Test;
import static org.junit.Assert.*;
import network.impl.advertisement.ItemAdvertisement;


import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Element;

import network.api.advertisement.ItemAdvertisementInterface;
import network.api.annotation.AdvertisementAttribute;
import network.api.annotation.ServiceName;
import network.impl.AbstractAdvertisement;

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

public class ItemAdvertisementTest{
	@Test

	public void test(){
		ItemAdvertisement item = new ItemAdvertisement();
		String name = new String ("item");
		assertTrue(name.equals(item.getName()));
		
		assertTrue(item.getAdvertisementType() == null);
		item.setTitle("Vicky");
		assertTrue(item.getTitle().equals("Vicky"));		

		//Peer peer = PeerFactory.createDefaultAndStartPeer();

		try
		{
			Thread.sleep(2000); // On laisse le temps au choses de s'init
		}
		catch (InterruptedException e)
		{}

		//item.publish(peer);
	
		Element elem = new Element("Coucou");
		Document doc = new Document (elem);
		
		item.initialize(doc);
		
		doc = item.getDocument();
		
		item.setSourceURI("Kirigiri");
		assertTrue(item.getSourceURI().equals("Kirigiri"));
		
		//peer.stop();
	}
}
