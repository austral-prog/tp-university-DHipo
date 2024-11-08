package com.university.cli;

import java.util.Scanner;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class CommandHandler {
    private static Scanner scanner = new Scanner(System.in);
    private String input = "";

    private List<String> returns = new ArrayList<>() {{
        add("exit");
        add("return");
    }};

    public CommandHandler() {
    }

    public static void waitUntilKeyPressed() {
        System.out.print("Press ENTER to continue...");
        scanner.nextLine();
    }

    // set the _input variable into a correct _options
    public <T> T handleInput(Map<String, T> _options) {
        boolean result = false;
        if (_options == null) return null;
        List<String> optionsName = _options.keySet().stream().map(String::toLowerCase).toList();
        System.out.println("NOTE: Type \"exit\" to quit the program or \n \"return\" to go back in the menu.");

        while (!result) {
            System.out.print("> ");
            input = scanner.nextLine().toLowerCase();
            result = optionsName.contains(input) || returns.contains(input);
            if (!result) System.out.println("Invalid Option! Try again.");
        }

        if (input.equals("return"))
            return null;

        // change the scene
        if (input.equals("exit"))
            HandlerCLI.state = "exit";
        return _options.get(input);
    }

    public String handleInputI(List<Integer> _options) {
        if (_options == null) return null;

        return getString(_options.stream().map(Object::toString).toList());
    }

    private String getString(List<String> _options) {
        String input = "";
        System.out.println("NOTE: Type \"return\" to go back in the menu.\n");
        while (!(_options.contains(input) || returns.contains(input))) {
            System.out.print("-> ");
            input = scanner.nextLine().toLowerCase();
        }
        if (input.equals("return"))
            return null;

        return input;
    }

    public String getLine() {
        return scanner.nextLine();
    }
}
