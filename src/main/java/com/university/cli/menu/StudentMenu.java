package com.university.cli.menu;

import com.university.cli.menu.Menu;

import java.util.Map;
import java.util.HashMap;
import com.university.model.University;

public class StudentMenu extends Menu {
	public Map<String, Runnable> options = new HashMap<>() {{
    put("show by id", () -> System.out.println("id"));
    put("remove",     () -> System.out.println("remove"));
    put("add",        () -> System.out.println("add"));
    put("show all",   () -> showAllStudents());
  }};

  public StudentMenu(University _university) {super(_university);}

  public void showAllStudents() {
    university.printStudents();
  }

  @Override
  public void print()
  {
      for (String key : options.keySet().stream().toList())
          System.out.println("\t-> " + key);
  }

	@Override
	public void run() {
      clearConsole();
      print();
      scene = cHandler.handleInput(options);

      if (scene == null) return;

      scene.run();
      cHandler.waitUntilKeyPressed();
	}
}
