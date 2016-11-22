package controller;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import controller.Application;
import controller.SystemCommandExecutor;

public class ControllerTest {

	private static final int restPort = 8081;
	private static final String baseURL = "http://0.0.0.0:" + 
		String.valueOf(restPort) + "/";

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
	
	private void waitUntilServerIsReady() {
		while (getResponseOf("").contains("curl"))
			try {Thread.sleep(500);} catch (Exception e) {fail("");};
	}
	
	private void connectWithUnknownUser() {
		String response = getResponseOf("api/users/login?login=franck&password=franck");
		
		assertTrue(true);
	}
	
	private void subscribe() {
		String response = getResponseOf("api/users/subscribe?login=cindy&password=cindy");
		
		assertTrue(response.length() > 5 && response.startsWith("{"));
	}
	
	@Test
	public void test() {
		Application application = new Application();

		application.getInstance().runForTests(restPort);

		waitUntilServerIsReady();
		connectWithUnknownUser();
		subscribe();
	}
}

