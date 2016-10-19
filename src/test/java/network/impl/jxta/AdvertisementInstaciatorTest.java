package network.impl.jxta;

import org.junit.Test;
import static org.junit.Assert.*;
import network.impl.advertisement.PeerAdvertisement;

import network.impl.jxta.AdvertisementInstaciator;

import net.jxta.document.Advertisement;
import net.jxta.document.Element;
import net.jxta.document.AdvertisementFactory.Instantiator;




public class AdvertisementInstaciatorTest {

	@Test
	public void test() {
		AdvertisementBridge i = new AdvertisementBridge();
		AdvertisementInstaciator instaciator = new AdvertisementInstaciator(i);
		assertTrue(instaciator.getAdvertisementType().equals(i.getAdvType()));
		assertTrue(instaciator.newInstance() != null);
		assertTrue(instaciator.newInstance(null) == null);

	}

}
