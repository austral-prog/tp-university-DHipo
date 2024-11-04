package com.university.cli;

import java.util.Scanner;
import java.util.Map;
import java.util.List;

import com.university.cli.HandlerCLI;
import com.university.cli.menu.*;

public class CommandHandler {
	private Scanner scanner = new Scanner(System.in);
	private String input = "";

	public CommandHandler() {}

	public String getInput() {
		return input;
	}

	// set the _input variable into a correct _options
	public Menu handleInput (Map<String, Menu> _options) {
		boolean result = false;
		
		List<String> optionsName = _options.keySet().stream().toList();
		
		System.out.println("NOTE: Type \"exit\" to quit the program.");
		
		while(!result) {
			System.out.print("> ");
			input = scanner.nextLine().toLowerCase();
			result = optionsName.contains(input) || input.equals("exit") ? true : false;
			if (!result) System.out.println("Invalid Option! Try again.");
		}
		
		// change the scene
		return _options.get(input);
	}
}
