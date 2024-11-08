package com.university.cli.menu;

import java.util.HashMap;
import java.util.Map;

import com.university.cli.CommandHandler;
import com.university.cli.HandlerCLI;
import com.university.model.Student;
import com.university.model.University;
import com.university.services.StudentService;

public class StudentMenu extends Menu {
	private StudentService sStudent;

	// Mapa de opciones donde key = opción y value = método a ejecutar
	public Map<String, Runnable> options = new HashMap<>() {{
		put("show by id", () -> showStudentById());
		put("remove", () -> removeStudent());
		put("add", () -> addStudent());
		put("show all", () -> showAllStudents());
	}};

	public StudentMenu(University _university) {
		super(_university);
		sStudent = new StudentService(_university);
	}

	public void showAllStudents() {
		HandlerCLI.clearConsole();
		university.printStudents();
	}

	public void addStudent() {
		System.out.print("Ingrese nombre del estudiante\n -> ");
		Student tempStudent = new Student(cHandler.getLine());
		sStudent.create(tempStudent);
		System.out.printf("\nStudent created correctly!\n\tName: %s\n\tId: %d\n", tempStudent.getName(), tempStudent.getId());
	}

	public void removeStudent() {
		if (university.getStudents().isEmpty()) {
			System.out.println("No hay estudiantes para borrar.");
			return;
		}

		System.out.printf("Ingrese id del estudiante (0, %d)\n", sStudent.getStudentsIDs().size());
		String input = cHandler.handleInputI(sStudent.getStudentsIDs().stream().toList());
		if (input == null || input.isEmpty()) return;

		sStudent.delete(Integer.parseInt(input));
		System.out.println("Student removed correctly.");
	}

	public void showStudentById() {
		if (university.getStudents().isEmpty()) {
			System.out.println("No hay estudiantes para mostrar.");
			return;
		}

		System.out.printf("Ingrese id del estudiante (0, %d)\n", sStudent.getStudentsIDs().size());
		String input = cHandler.handleInputI(sStudent.getStudentsIDs().stream().toList());
		if (input == null || input.isEmpty()) return;

		HandlerCLI.clearConsole();
		Student student = sStudent.read(Integer.parseInt(input));
		if (student != null)
			System.out.printf("%s\n", student);
		else
			System.out.println("Estudiante no encontrado.");
	}

	@Override
	public void print() {
		System.out.print("----- Student -----\n\nOptions:\n");

		for (String key : options.keySet().stream().toList())
			System.out.println("\t-> " + key);

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
