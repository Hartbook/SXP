package controller;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import controller.Application;
import controller.SystemCommandExecutor;
import org.json.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControllerTest {

	private static final int restPort = 8081;
	private static final String baseURL = "http://0.0.0.0:" + 
		String.valueOf(restPort) + "/";
		
	private static final DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	Date date = new Date();


	private String token;
	private String userid;
	
	private String itemId;
	private String pbkey;
	
	private void connectWithUnknownUser() {
		String response = getResponseOf("api/users/login?login=franck&password=franck", null, null);
		assertFieldValue(response, "error", "true");
	}
	
	private void subscribe() {
		String response = getResponseOf("api/users/subscribe?login=cindy&password=cindy", null, null);
		token = getFieldValue(response, "token");
		getFieldValue(response, "userid");
	}

	private void logout() {
		List<String> headers = new ArrayList<String>();
		headers.add("Auth-Token: " + token);

		String response = 
			getResponseOf("api/users/logout", headers, null);
	}

	private void connectWithKnownUser() {
		String response = getResponseOf("api/users/login?login=cindy&password=cindy", null, null);
		token = getFieldValue(response, "token");
		userid = getFieldValue(response, "userid");
	}
	
	private void checkEmptyItemList(){	
		List<String> headers = new ArrayList<String>();
		headers.add("Auth-Token: " + token);
		headers.add("Connection: keep-alive");

		String response = getResponseOf("api/items", headers, null);
		assertTrue(response.startsWith("[]"));
	}
	
	private void addItem(){
		List<String> headers = new ArrayList<String>();
		headers.add("Origin: file://");
		headers.add("Content-Type: application/json;charset=UTF-8");		
		headers.add("Accept: application/json, text/plain, */*");
		headers.add("Auth-Token: " + token);

		List<String> datas = new ArrayList<String>();
		datas.add ("{\"title\":\"Patates\",\"description\":\"Mes patates\"}");	
		
		String response = getResponseOf("api/items", headers, datas);			
		
		itemId = getFieldValue(response, "id");
		assertFieldValue(response, "title", "Patates");
		assertFieldValue(response, "description", "Mes patates");
		assertFieldValue(response, "createdAt", format.format(date));
		pbkey = getFieldValue(response, "pbkey");				
		assertFieldValue(response, "username", "cindy");
		assertFieldValue(response, "userid", userid);
	}
	
	private void checkItemList(){
		List<String> headers = new ArrayList<String>();
		headers.add("Auth-Token: " + token);
		

		String response = getResponseOf("api/items", headers, null);
		response = response.replace('[', ' ');
		response = response.replace(']', ' ');
		response = response.trim();
	
		assertFieldValue(response, "id", itemId);
		assertFieldValue(response, "title", "Patates");
		assertFieldValue(response, "description", "Mes patates");
		assertFieldValue(response, "createdAt", format.format(date));
		assertFieldValue(response, "pbkey", pbkey);				
		assertFieldValue(response, "username", "cindy");
		assertFieldValue(response, "userid", userid);
	}
	
	private void checkItem(){
		List<String> headers = new ArrayList<String>();
		headers.add("Auth-Token: " + token);
	
		String response = getResponseOf("api/items/" + itemId, headers, null);	
		
		assertFieldValue(response, "id", itemId);
		assertFieldValue(response, "title", "Patates");
		assertFieldValue(response, "description", "Mes patates");
		assertFieldValue(response, "createdAt", format.format(date));
		assertFieldValue(response, "pbkey", pbkey);				
		assertFieldValue(response, "username", "cindy");
		assertFieldValue(response, "userid", userid);
	}
	
	private void editItem(){
		List<String> headers = new ArrayList<String>();
		headers.add("Auth-Token: " + token);
		headers.add("Origin: file://");
		headers.add("Content-Type: application/json;charset=UTF-8");
		
		List<String> datas = new ArrayList<String>();
		datas.add("{\"id\":\"" + itemId + "\",\"title\":\"Patates\",\"description\":\"Mes belles patates\",\"createdAt\":\"" + format.format(date) + "\",\"pbkey\":\"" + pbkey + "\",\"username\":\"cindy\",\"userid\":\"" + userid + "\",\"$promise\":{},\"$resolved\":true}");
			
		String response = getResponseOf("api/items/" + itemId, headers, datas, "PUT");
		
		assertFieldValue(response, "id", itemId);
		assertFieldValue(response, "title", "Patates");
		assertFieldValue(response, "description", "Mes belles patates");
		assertFieldValue(response, "createdAt", format.format(date));
		assertFieldValue(response, "pbkey", pbkey);				
		assertFieldValue(response, "username", "cindy");
		assertFieldValue(response, "userid", userid);
	}
	
	private void searchItem(){
		String response = getResponseOf("api/search/simple?title=Patates", null, null);
		response = response.replace('[', ' ');
		response = response.replace(']', ' ');
		response = response.trim();						
				
		assertFieldValue(response, "id", itemId);
		assertFieldValue(response, "title", "Patates");
		assertFieldValue(response, "description", "Mes belles patates");
		assertFieldValue(response, "createdAt", format.format(date));
		assertFieldValue(response, "pbkey", pbkey);				
		assertFieldValue(response, "username", "cindy");
		assertFieldValue(response, "userid", userid);				
		
	}
	
	private void checkAccount(){
		List<String> headers = new ArrayList<String>();
		headers.add("Auth-Token: " + token);

		String response = getResponseOf("api/users/" + userid, headers, null);	
		
		assertFieldValue(response, "id", userid);
		assertFieldValue(response, "nick", "cindy");
		String salt = getFieldValue(response, "salt");
		getFieldValue(response,"passwordHash");
		getFieldIntValue(response, "createdAt");
		JSONObject keys = getFieldJSONObjectValue(response, "key");
		assertTrue(keys.has("privateKey"));
		assertTrue(keys.has("publicKey"));
	}
	
	@Test
	public void test() {
		deleteDataBase();
		
		Application application = new Application();

		application.getInstance().runForTests(restPort);

		waitUntilServerIsReady();
		connectWithUnknownUser();
		subscribe();
		logout();
		connectWithKnownUser();
		checkEmptyItemList();
		addItem();
		checkItemList();
		checkItem();
		editItem();
		searchItem();
		checkAccount();
	}

	private String getResponseOf(String request, List<String> headers, List<String> datas) {
		List<String> commands = new ArrayList<String>();
		commands.add("curl");
		commands.add(baseURL + request);
		commands.add("-H");
		commands.add("Accept: application/json");

		if (headers != null)
			for (String header : headers) {
				commands.add("-H");
				commands.add(header);
			}

		if (datas != null)
			for (String data : datas) {
				commands.add("--data-binary");
				commands.add(data);		
			}

		SystemCommandExecutor commandExecutor = 
			new SystemCommandExecutor(commands);

		try {
			commandExecutor.executeCommand();
		} catch (Exception e) {e.printStackTrace(); fail("");};

		StringBuilder stdout = 
			commandExecutor.getStandardOutputFromCommand();
		StringBuilder stderr = 
			commandExecutor.getStandardErrorFromCommand();

		if (stdout.toString().length() == 0)
			return stderr.toString();
		
		return stdout.toString();
	}

	private String getResponseOf(String request, List<String> headers, List<String> datas, String action) {
		List<String> commands = new ArrayList<String>();
		commands.add("curl");
		commands.add(baseURL + request);
		commands.add("-H");
		commands.add("Accept: application/json");

		commands.add("-X " + action);

		
		if (headers != null)
			for (String header : headers) {
				commands.add("-H");
				commands.add(header);
			}

		if (datas != null)
			for (String data : datas) {
				commands.add("--data-binary");
				commands.add(data);		
			}

		SystemCommandExecutor commandExecutor = 
			new SystemCommandExecutor(commands);

		try {
			commandExecutor.executeCommand();
		} catch (Exception e) {e.printStackTrace(); fail("");};

		StringBuilder stdout = 
			commandExecutor.getStandardOutputFromCommand();
		StringBuilder stderr = 
			commandExecutor.getStandardErrorFromCommand();

		if (stdout.toString().length() == 0)
			return stderr.toString();

			

		return stdout.toString();
	}

	private void assertFieldValue(String json, String field, String value) {
		try {
			String fieldValue = new JSONObject(json).getString(field);

			assertTrue(fieldValue.equals(value));
		} catch (Exception e) {
			fail("");
		}
	}

	private String getFieldValue(String json, String field) {
		String fieldValue = new String();

		try {
			fieldValue = new JSONObject(json).getString(field);
		} catch (Exception e) {
			fail("");
		}

		return fieldValue;
	}
	
	private int getFieldIntValue(String json, String field) {

		int result = -1;

		try {
			result = new JSONObject(json).getInt(field);
		} catch (Exception e) {
			fail("");
		}

		return result;
	}
	
	private JSONObject getFieldJSONObjectValue (String json, String field){
		JSONObject fieldValue = new JSONObject();

		try {
			fieldValue = new JSONObject(json).getJSONObject(field);
		} catch (Exception e) {
			fail("");
		}

		return fieldValue;	
	}
	
	private void waitUntilServerIsReady() {
		while (getResponseOf("", null, null).contains("curl"))
			try {Thread.sleep(500);} catch (Exception e) {fail("");};
	}
	
	private void deleteDataBase(){
		List<String> commands = new ArrayList<String>();
		commands.add("rm");
		commands.add("-r");
		commands.add(".db-8081/");
		
		SystemCommandExecutor commandExecutor = 
			new SystemCommandExecutor(commands);

		try {
			commandExecutor.executeCommand();
		} catch (Exception e) {e.printStackTrace(); fail("");};
	}
}

