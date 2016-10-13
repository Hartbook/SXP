package network.impl.advertisement;
import org.junit.Test;
import static org.junit.Assert.*;
import network.impl.advertisement.ItemAdvertisement;

public class ItemAdvertisementTest{
	@Test

public void test(){
ItemAdvertisement item = new ItemAdvertisement();
String name = new String ("item");
assertTrue(name.equals(item.getName()));
	}
}
