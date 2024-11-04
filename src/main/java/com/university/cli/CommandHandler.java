package com.university.cli;

import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.university.cli.HandlerCLI;
import com.university.cli.menu.*;

public class CommandHandler {
	private static Scanner scanner = new Scanner(System.in);
	private String input = "";

  private List<String> returns = new ArrayList<>() {{
    add("exit");
    add("return");
  }};

	public CommandHandler() {}

	public String getInput() {
		return input;
	}

  public static void waitUntilKeyPressed()
  {
    System.out.print("Press ENTER to continue...");
    scanner.nextLine();
  }

	// set the _input variable into a correct _options
	public <T> T handleInput (Map<String, T> _options) {
		boolean result = false;
		if (_options == null) return null;
		List<String> optionsName = _options.keySet().stream().toList();
		System.out.println("NOTE: Type \"exit\" to quit the program.");
		
		while(!result) {
			System.out.print("> ");
			input = scanner.nextLine().toLowerCase();
  		result = optionsName.contains(input) || returns.contains(input) ? true : false;
			if (!result) System.out.println("Invalid Option! Try again.");
		}
		
    if (input.equals("return"))
      return null;

		// change the scene
    if (input.equals("exit"))
        HandlerCLI.state = "exit";
		return _options.get(input);
	}
}
