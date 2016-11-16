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

		return stdout.toString();
	}
	
	@Test
	public void test() {
		Application application = new Application();

		application.getInstance().runForTests(restPort);

		String result = getResponseOf("api/users/subscribe?login=dawa&password=touny");

		assertTrue(result.length() > 5);
	}
}
