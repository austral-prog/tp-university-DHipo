package com.university.cli.menu;

import com.university.cli.menu.Menu;

import java.util.Map;
import java.util.HashMap;
import com.university.model.University;
import com.university.model.Student;
import com.university.services.StudentService;

public class StudentMenu extends Menu {
  private StudentService sService;
	public Map<String, Runnable> options = new HashMap<>() {{
    put("show by id", () -> System.out.println("id"));
    put("remove",     () -> System.out.println("remove"));
    put("add",        () -> addStudent());
    put("show all",   () -> showAllStudents());
  }};

  public StudentMenu(University _university) {
    super(_university);
    this.sService = new StudentService(_university);
  }

  public void addStudent()
  {
    System.out.print("Ingrese nombre del estudiante\n -> ");
    sService.create(new Student(cHandler.getLine()));
  }
  
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
