package network.impl.jxta;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;
import org.jdom2.Element;

import org.junit.Test;
import static org.junit.Assert.*;

import net.jxta.document.MimeMediaType;
import network.api.Peer;
import network.api.advertisement.Advertisement;
import network.factories.AdvertisementFactory;
import network.factories.PeerFactory;
import network.impl.jxta.JxtaAdvertisement;
import network.impl.jxta.AdvertisementBridge;
import network.impl.advertisement.ItemAdvertisement;
import java.util.Arrays;

public class JxtaAdvertisementTest {
	private Advertisement adv = AdvertisementFactory.createItemAdvertisement();
	private JxtaAdvertisement jxtaAdv = new JxtaAdvertisement(adv);
	
	private void testBridge(){
		AdvertisementBridge JxtaAdvBridge = jxtaAdv.getJxtaAdvertisementBridge();
		assertTrue(Arrays.equals(jxtaAdv.getIndexFields(),JxtaAdvBridge.getIndexFields()));
	}
	
	@Test
	public void test() {
		
		assertTrue(jxtaAdv.getName().equals("item"));
		assertTrue(jxtaAdv.getAdvertisementType().equals("jxta:item"));
		testBridge();
		assertTrue(Arrays.equals(jxtaAdv.getIndexFields(),adv.getIndexFields()));
		
		Peer peer = PeerFactory.createDefaultAndStartPeer();
		jxtaAdv.publish(peer);

		Document doc = new Document ();
		
		jxtaAdv.initialize(doc);
		//assertTrue(Document.equals(jxtaAdv.getDocument()adv.getDocument()));

	}
}

