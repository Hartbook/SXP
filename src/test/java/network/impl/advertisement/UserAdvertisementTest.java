package network.impl.advertisement;
import org.junit.Test;
import static org.junit.Assert.*;
import network.impl.advertisement.UserAdvertisement;

import network.api.advertisement.UserAdvertisementInterface;
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


public class UserAdvertisementTest {

	AdvertisementFactory factory = new AdvertisementFactory();
	@Test
	public void test() {
		UserAdvertisement user = (UserAdvertisement)factory.createUserAdvertisement();
		String name = new String ("user");
		assertTrue(name.equals(user.getName()));  
		
		assertTrue(user.getAdvertisementType() == null);
		
		/*Peer peer = PeerFactory.createDefaultAndStartPeer();

		try
		{
			Thread.sleep(2000); // On laisse le temps au choses de s'init
		}
		catch (InterruptedException e)
		{}

		user.publish(peer);*/
	
		Element elem = new Element("Coucou");
		Document doc = new Document (elem);
		
		user.initialize(doc);
		
		doc = user.getDocument();
		
		user.setSourceURI("Kirigiri");
		assertTrue(user.getSourceURI().equals("Kirigiri"));
		
		//peer.stop(); 
		
	}
}

