package rest.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.ArrayList;
import rest.util.JsonUtils;

import java.io.Serializable;

public class JsonUtilsTest {

	private String[] expectedStarts = { 
	"{\"integer\":  \"1\", \"str1\": null, \"str2\": \"Dooz Kawa\", \"integers\": []",
	"{\"integer\":  \"2\", \"str1\": null, \"str2\": \"Dooz Kawa\", \"integers\": []"
	};

	private class ToyBean implements Serializable {

		private int integer;
		private String str1 = null;
		private String str2 = "Dooz Kawa";
		private ArrayList<Integer> integers = new ArrayList<Integer>(4);

		public ToyBean(int nb) {
			integer = nb;
		}

		int getInteger() {
			return integer;
		}
	}

	@Test
	public void test() {
		ArrayList<ToyBean> toyBeans = new ArrayList<ToyBean>(2);
		toyBeans.add(new ToyBean(1));
		toyBeans.add(new ToyBean(2));

		String str = JsonUtils.collectionStringify(toyBeans);

		assertTrue(str.contains("\"integer\": \"1\""));
		assertTrue(str.contains("\"integer\": \"2\""));
		assertTrue(str.contains("\"str1\": null"));
		assertTrue(str.contains("\"str2\": \"Dooz Kawa\""));
		assertTrue(str.contains("\"integers\": []"));
	}
}

