package com.university.cli;

import java.util.HashMap;
import java.util.Map;

import com.university.cli.menu.*;
import com.university.repository.CRUDRepository;
import com.university.cli.CommandHandler;

public class HandlerCLI implements CLI{
	// key = option value = function that print the option
	private Map<String, Menu> options = new HashMap<>() {{
		put("student", new StudentMenu());
	}};	

	private CommandHandler cHandler = new CommandHandler();
	public static String state = "";
	// Main menu va a ser la escena principal
	public Menu scene = null;

	public HandlerCLI() {}
	
	public void print() {
		System.out.printf("----- Main -----\n\nOptions:\n");
		for (String key : options.keySet().stream().toList())
			System.out.printf("\t-> %s\n", state.equals(key) ? key + "*" : key);
		System.out.println("\n");
	}
    
	@Override
    public void runCLI(CRUDRepository<?>[] crudInterfaces) {
		if (scene == null) print();
			else scene.print();
			
		if (state.equals("exit")) return;
		// una vez mostrado el menu, menejo el input
		scene = cHandler.handleInput(options);
	}
}
