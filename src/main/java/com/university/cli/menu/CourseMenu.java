package com.university.cli.menu;

import com.university.cli.CommandHandler;
import com.university.cli.HandlerCLI;
import com.university.model.Course;
import com.university.model.University;
import com.university.services.CourseService;

import java.util.HashMap;
import java.util.Map;

public class CourseMenu extends Menu {
    private final CourseService sCourse;

    // Map de opciones donde la clave es la opción y el valor es el método a ejecutar
    public Map<String, Runnable> options = new HashMap<>() {{
        put("show by id", () -> showCourseById());
        put("remove", () -> removeCourse());
        put("add", () -> addCourse());
        put("show all", () -> showAllCourses());
    }};

    public CourseMenu(University _university) {
        super(_university);
        sCourse = new CourseService(_university);
    }

    public void showAllCourses() {
        HandlerCLI.clearConsole();
        university.printCourses();
    }

    public void addCourse() {
        System.out.print("Ingrese nombre del curso\n -> ");
        Course tempCourse = new Course(cHandler.getLine());
        sCourse.create(tempCourse);
        System.out.printf("\nCourse created correctly!\n\tName: %s\n\tId: %d\n", tempCourse.getName(), tempCourse.getId());
    }

    public void removeCourse() {
        if (university.getCourses().size() <= 0) {
            System.out.println("No hay cursos ha borrar.");
            return;
        }

        System.out.printf("Ingrese id del curso (%d,%d)\n", 0, university.getCourses().size());
        String input = cHandler.handleInputI(sCourse.getCourseIDs().stream().toList());
        if (input == null || input.isEmpty()) return;

        sCourse.delete(Integer.parseInt(input));
        System.out.println("Course removed correctly.");
    }

    public void showCourseById() {
        if (university.getCourses().size() <= 0) {
            System.out.println("No hay cursos ha mostrar.");
            return;
        }

        System.out.printf("Ingrese id del curso (%d,%d)\n", 0, university.getCourses().size());
        String input = cHandler.handleInputI(sCourse.getCourseIDs().stream().toList());
        if (input == null || input.isEmpty()) return;

        HandlerCLI.clearConsole();
        Course course = sCourse.read(Integer.parseInt(input));
        if (course != null)
            System.out.printf("%s\n", course);
        else
            System.out.println("Curso no encontrado.");
    }

    @Override
    public void print() {
        System.out.print("----- Course Menu -----\n\nOptions:\n");

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
