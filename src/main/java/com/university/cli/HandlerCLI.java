package com.university.cli;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.university.cli.menu.*;
import com.university.services.CRUDRepository;
import com.university.model.University;
import com.university.solution.ExerciseOne;
import com.university.solution.ExerciseThree;
import com.university.solution.ExerciseTwo;
import com.university.services.Service;

public class HandlerCLI implements CLI {

    private University university;
    private CommandHandler cHandler = new CommandHandler();
    public static String state = "";
    // Main menu va a ser la escena principal
    public Menu menu = null;
    // key = option value = function that print the option
    private final Map<String, Menu> options = new HashMap<>();

    public HandlerCLI(University _university) {
        this.university = _university;
        this.options.put("student", new StudentMenu(this.university));
        this.options.put("teacher", new TeacherMenu(this.university));
        this.options.put("course", new CourseMenu(this.university));

        this.options.put("exercise", new SolutionMenu(
                this.university,
                new LinkedHashMap<>() {{
                    put("Exercise One", new ExerciseOne(university));
                    put("Exercise Two", new ExerciseTwo(university));
                    put("Exercise Three", new ExerciseThree(university));
                }}
        ));
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void print() {
        System.out.print("----- Main -----\n\nOptions:\n");
        for (String key : options.keySet().stream().toList())
            System.out.printf("\t-> %s\n", key.toUpperCase());
        System.out.println("\n");
    }

    @Override
    public void runCLI(CRUDRepository<?>[] crudInterfaces) {
        while (menu != null) {
            menu.run();
            if (menu.getScene() == null) menu = null;
        }
        clearConsole();
        if (state.equals("exit")) return;
        for(String key : options.keySet())
        {
            if (key.equals("exercise")) continue;
            options.get(key).getService().updateIDs();
        }
        // una vez mostrado el menu, menejo el input
        print();
        menu = cHandler.handleInput(options);
    }
}
