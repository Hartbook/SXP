package model.entity;

import network.api.ItemService;
import org.junit.Test;
import static org.junit.Assert.*;

import model.entity.Item;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class ItemTest {
	@Test
	public void test() {
		Item item = new Item();		
		assertTrue(item.getId() == null);
		
		item.setTitle("Hope's Peak");
		assertTrue(item.getTitle().equals("Hope's Peak"));		

		item.setDescription("Kibou");
		assertTrue(item.getDescription().equals("Kibou"));		
		
		Date date = new Date(1995,06,16);
		item.setCreatedAt(date);
		assertTrue(item.getCreatedAt().equals(date));
		
		BigInteger publicKey = new BigInteger("11307");
		item.setPbkey(publicKey);
		assertTrue(item.getPbkey().equals(publicKey));

		item.setUsername("Celty");
		assertTrue(item.getUsername().equals("Celty"));		

		item.setUserid("Setton");
		assertTrue(item.getUserid().equals("Setton"));		
	}
}
