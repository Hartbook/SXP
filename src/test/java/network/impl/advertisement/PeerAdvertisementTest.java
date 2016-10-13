package network.impl.advertisement;
import org.junit.Test;
import static org.junit.Assert.*;
import network.impl.advertisement.PeerAdvertisement;

public class PeerAdvertisementTest {

	
	@Test
	public void test() {
PeerAdvertisement peer = new PeerAdvertisement();
String name = new String ("peer");
assertTrue(name.equals(peer.getName()));

	}

}
