package com.university.cli;

import java.util.HashMap;
import java.util.Map;

import com.university.cli.menu.*;
import com.university.services.CRUDRepository;
import com.university.cli.CommandHandler;
import com.university.model.University;

public class HandlerCLI implements CLI{
	
  private University university;
	private CommandHandler cHandler = new CommandHandler();
	public static String state = "";
	// Main menu va a ser la escena principal
	public Menu menu = null;
	// key = option value = function that print the option
	private Map<String, Menu> options = new HashMap<>();
	
  public HandlerCLI(University _university) {
    this.university = _university;
    this.options.put("student", new StudentMenu(this.university));
  }
	
	public void print() {
		System.out.printf("----- Main -----\n\nOptions:\n");
		for (String key : options.keySet().stream().toList())
			System.out.printf("\t-> %s\n", state.equals(key) ? key + "*" : key);
		System.out.println("\n");
	}
    
	@Override
  public void runCLI(CRUDRepository<?>[] crudInterfaces) {
		while (menu != null) {
      menu.run();
      if (menu.getScene() == null) menu = null;
    }
	  Menu.clearConsole();	
		if (state.equals("exit")) return;
		// una vez mostrado el menu, menejo el input
    print();
		menu = cHandler.handleInput(options);
	}
}
