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
		String response = getResponseOf("api/users/login?login=franck&password=franck");
		assertFieldValue(response, "error", "true");
	}
	
	private void subscribe() {
		String response = getResponseOf("api/users/subscribe?login=cindy&password=cindy");
		token = getFieldValue(response, "token");
		getFieldValue(response, "userid");
	}

	private void logout() {
		String response = 
			getResponseOf("api/users/logout", "Auth-Token: " + token);
	}

	private void connectWithKnownUser() {
		String response = getResponseOf("api/users/login?login=cindy&password=cindy");
		token = getFieldValue(response, "token");
		userid = getFieldValue(response, "userid");
	}
	
	private void seeEmptyItemList(){
		String response = getResponseOf("api/users/items");
		assertTrue(response.equals("[]"));
	}
	
	private void addItem(){
		String response = getResponseOf("api/users/items", "Auth-Token: " + token, "{\"title\":\"Patates\",\"description\":\"Mes patates\"}");
		System.out.println(response);
		itemId = getFieldValue(response, "id");
		assertFieldValue(response, "title", "Patates");
		assertFieldValue(response, "description", "Mes patates");
		assertFieldValue(response, "createdAt", format.format(date));
		pbkey = getFieldValue(response, "pbkey");				
		assertFieldValue(response, "username", "cindy");
		assertFieldValue(response, "userid", userid);	
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
		addItem();
	}

	private String getResponseOf(String request) {
		List<String> commands = new ArrayList<String>();
		commands.add("curl");
		commands.add(baseURL + request);
		commands.add("-H");
		commands.add("Accept: application/json");

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

	private String getResponseOf(String request, String header) {
		List<String> commands = new ArrayList<String>();
		commands.add("curl");
		commands.add(baseURL + request);
		commands.add("-H");
		commands.add("Accept: application/json");
		commands.add("-H");
		commands.add(header);

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
	
	private String getResponseOf(String request, String header, String data) {
		List<String> commands = new ArrayList<String>();
		commands.add("curl");
		commands.add(baseURL + request);
		commands.add("-H");
		commands.add("Accept: application/json");
		commands.add("-H");
		commands.add(header);
		commands.add("--data-binary");
		commands.add(data);		

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

	private void waitUntilServerIsReady() {
		while (getResponseOf("").contains("curl"))
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

