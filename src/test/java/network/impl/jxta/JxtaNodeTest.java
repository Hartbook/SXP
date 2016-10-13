package network.impl.jxta;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import net.jxta.exception.PeerGroupException;
import net.jxta.id.IDFactory;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupID;
import net.jxta.platform.NetworkConfigurator;
import net.jxta.platform.NetworkManager;
import net.jxta.protocol.ModuleImplAdvertisement;
import network.api.Node;
import network.utils.IpChecker;

import network.impl.jxta.JxtaNode;



public class JxtaNodeTest {

	private JxtaNode node = new JxtaNode();
	private JxtaNode badNode = new JxtaNode();
	 
	@Test
	public void test() {
		try{
		node.initialize("derby.system.home", "Kirigiri", true);
		} catch (IOException e){
			fail("");
		}
		
		assertTrue(node.isInitialized());
		
		try{
			node.start(9800);
		}catch (RuntimeException e){
			fail("");
		}
		
		try{
			badNode.start(9800);
			fail("");
		}catch (RuntimeException e){}
	
		assertTrue(node.isStarted());
		assertFalse(badNode.isStarted());
		
		try{
			badNode.initialize("derby.system.home", "Makoto", false);
		} catch (IOException e){
			fail("");
		}
		
		assertFalse(badNode.isStarted());
		
		try{
			node.stop();
		}catch (RuntimeException e){
			fail("");
		}
		
		badNode = new JxtaNode();
		
		try{
			badNode.stop();
			fail("");
		}catch (RuntimeException e){}
		
		assertTrue(node.getDefaultPeerGroup() != null);
		
		try{
			node.createGroup("dawa");
		}catch(Exception e){
		fail("");
		}
		
	}
}

