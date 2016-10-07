package network.impl.jxta;

import network.api.ItemService;
import org.junit.Test;
import static org.junit.Assert.*;

import network.impl.jxta.JxtaService;



public class JxtaItemServiceTest {
	@Test
	public void test() {
		JxtaItemService JIS = new JxtaItemService();
		assertTrue(JIS.name.equals("items"));
	}
}
