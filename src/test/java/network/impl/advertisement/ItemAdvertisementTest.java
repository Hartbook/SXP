package network.impl.advertisement;
import org.junit.Test;
import static org.junit.Assert.*;
import network.impl.advertisement.ItemAdvertisement;


import org.jdom2.*;

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

	
	AdvertisementFactory factory = new AdvertisementFactory();
	@Test
	public void test(){
		ItemAdvertisement item = (ItemAdvertisement)factory.createItemAdvertisement();
		String name = new String ("item");
		assertTrue(name.equals(item.getName()));
		
		assertTrue(item.getAdvertisementType() == null);
		item.setTitle("Vicky");
		assertTrue(item.getTitle().equals("Vicky"));		

		/*Peer peer = PeerFactory.createDefaultAndStartPeer();

		try
		{
			Thread.sleep(2000); // On laisse le temps au choses de s'init
		}
		catch (InterruptedException e)
		{}

		item.publish(peer);*/
	
		Element root = new Element("root");
		Element child = new Element("title");

		root.addContent(child);
		Document doc = new Document(root);
		
		item.initialize(doc);
		
		doc = item.getDocument();
		
		item.setSourceURI("Kirigiri");
		assertTrue(item.getSourceURI().equals("Kirigiri"));
		
		//peer.stop();
	}
}
