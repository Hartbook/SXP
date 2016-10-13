package network.impl.advertisement;
import org.junit.Test;
import static org.junit.Assert.*;
import network.impl.advertisement.UserAdvertisement;

public class UserAdvertisementTest {

	
	@Test
	public void test() {
UserAdvertisement user = new UserAdvertisement();
String name = new String ("user");
assertTrue(name.equals(user.getName()));


       
	}
}

