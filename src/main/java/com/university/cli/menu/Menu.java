package com.university.cli.menu;

import java.util.Map;

import com.university.cli.CommandHandler;

public abstract class Menu {
	protected Map<String, Menu> options;
	protected Menu scene;
	protected CommandHandler cHandler = new CommandHandler();

	public Map<String, Menu> getOptions() {return this.options;}
	
	public static void clearConsole() {
		System.out.print("\033[H\033[2J");  
		System.out.flush(); 
	}

	public abstract void print();
}
