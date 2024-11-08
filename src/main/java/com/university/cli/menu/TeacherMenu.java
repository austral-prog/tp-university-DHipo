package com.university.cli.menu;

import java.util.HashMap;
import java.util.Map;

import com.university.cli.CommandHandler;
import com.university.cli.HandlerCLI;
import com.university.model.Teacher;
import com.university.model.University;
import com.university.services.TeacherService;

public class TeacherMenu extends Menu {
	private final TeacherService sTeacher;

	// key = option value = method to run
	public Map<String, Runnable> options = new HashMap<>() {{
		put("show by id", () -> showTeacherById());
		put("remove", () -> removeTeacher());
		put("add", () -> addTeacher());
		put("show all", () -> showAllStudents());
	}};

	public TeacherMenu(University _university) {
		super(_university);
		sTeacher = new TeacherService(_university);
	}

	public void showAllStudents() {
		HandlerCLI.clearConsole();
		university.printTeachers();
	}

	public void addTeacher() {
		System.out.print("Ingrese nombre del profesor\n -> ");
		Teacher tempTeacher = new Teacher(cHandler.getLine());
		sTeacher.create(tempTeacher);
		System.out.printf("\nTeacher created correctly!\n\tName: %s\n\tId: %d\n", tempTeacher.getName(), tempTeacher.getId());
	}

	public void removeTeacher() {
		if (university.getTeachers().size() <= 0) {
			System.out.println("No hay profesores ha borrar.");
			return;
		}

		System.out.printf("Ingrese id del profesor (%d,%d)\n", 0, university.getTeachers().size());
		String input = cHandler.handleInputI(sTeacher.getTeachersIDs().stream().toList());

		sTeacher.delete(Integer.parseInt(input));
		System.out.println("Teacher removed correctly.");
	}

	public void showTeacherById() {
		if (university.getTeachers().size() <= 0) {
			System.out.println("No hay profesores ha mostrar.");
			return;
		}

		System.out.printf("Ingrese id del profesor (%d,%d)\n", 0, university.getTeachers().size());
		String input = cHandler.handleInputI(sTeacher.getTeachersIDs().stream().toList());

		HandlerCLI.clearConsole();
		if (sTeacher.read(Integer.parseInt(input)) instanceof Teacher)
			System.out.printf("%s", sTeacher.read(Integer.parseInt(input)));
	}

	@Override
	public void print() {
		System.out.printf("----- Teacher -----\n\nOptions:\n");

	    for (String key : options.keySet().stream().toList())
			System.out.println("\t-> " + key.toUpperCase());

		System.out.println();
	}

	@Override
	public void run() {
		HandlerCLI.clearConsole();
		print();
		scene = cHandler.handleInput(options);

	    if (scene == null)
			return;

		HandlerCLI.clearConsole();
		scene.run();
		CommandHandler.waitUntilKeyPressed();
	}
}
